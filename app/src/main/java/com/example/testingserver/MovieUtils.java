package com.example.testingserver;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MovieUtils {
    private static final String LOG_TAG = EventUtils.class.getSimpleName();

    private MovieUtils() {
    }

    public static List<MovieClass> fetchData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<MovieClass> newsList = extractNews(jsonResponse);

        return newsList;
    }

    private static URL createUrl(String stringUrl) {
        URL url;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpsURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Authorization", "Bearer " /* clean that trash */);
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "current error" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<MovieClass> extractNews(String eventJSON) {
        if (TextUtils.isEmpty(eventJSON)) {
            return null;
        }

        ArrayList<MovieClass> newsTestArray = new ArrayList<>();

        try {
            JSONObject rootObj = new JSONObject(eventJSON);
            JSONArray data = rootObj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject currentItem = data.getJSONObject(i);
                String id = currentItem.getString("id");
                String title = currentItem.getString("nameEn");
                String titleLoc = currentItem.getString("nameLoc");
                String poster = currentItem.getString("poster");
                String co = currentItem.getString("country_eng");
                String rusTitle = currentItem.getString("nameRus");
                String dirRus = currentItem.getString("director");
                String dir = currentItem.getString("director_eng");
                String scriptRus = currentItem.getString("script_rus");
                String script = currentItem.getString("script_eng");
                String castRus = currentItem.getString("cast_rus");
                String cast = currentItem.getString("cast_rus");
                String camRus = currentItem.getString("cast_eng");
                String cam = currentItem.getString("camera_eng");
                String poducerRus = currentItem.getString("producers_rus");
                String producer = currentItem.getString("producers_eng");
                String ctionRus = currentItem.getString("production_rus");
                String ction = currentItem.getString("production_eng");
                String syn = currentItem.getString("synopsis_eng");
                String countryProd = currentItem.getString("country_prod");
                JSONObject progr = currentItem.getJSONObject("programm");
                String programmName = progr.getString("name");
                String programmNameEn = progr.getString("nameEn");
                String posterPlace = "https://42.arcomp.ru/storage/equipments/big/" + poster;
                MovieClass format = new MovieClass(posterPlace, title, titleLoc, co, rusTitle, programmName, dirRus, dir, scriptRus, script, castRus, cast,
                        camRus, cam, poducerRus, producer, ctionRus, ction, syn, countryProd, id, programmNameEn);
                newsTestArray.add(format);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return newsTestArray;
    }
}
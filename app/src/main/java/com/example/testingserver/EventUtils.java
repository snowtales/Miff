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

public final class EventUtils {
    private static final String LOG_TAG = EventUtils.class.getSimpleName();

    private EventUtils() {
    }

    public static List<EventClass> fetchData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<EventClass> eventList = extractEvents(jsonResponse);

        return eventList;
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

    public static ArrayList<EventClass> extractEvents(String eventJSON) {
        if (TextUtils.isEmpty(eventJSON)) {
            return null;
        }

        ArrayList<EventClass> movieTestArray = new ArrayList<>();

        try {
            JSONObject rootObj = new JSONObject(eventJSON);
            JSONArray data = rootObj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject currentItem = data.getJSONObject(i);
                String dateStart = currentItem.getString("dateStart");
                JSONObject event = currentItem.getJSONObject("event");
                String id = event.getString("film_id");
                String idEvent = currentItem.getString("id_event");//используется чтобы в дальнейшем сформировать выдачу билетов
                String name = event.getString("name");
                String nameEn = event.getString("nameEn");
                String type = event.getString("type");
                JSONObject hall = currentItem.getJSONObject("hall");
                String hallName = hall.getString("name");
                String hallNameEn = hall.getString("name_en");
                JSONObject venue = hall.getJSONObject("venue");
                String adress = venue.getString("address");
                String place = venue.getString("name");
                String placeEn = venue.getString("nameEng");
                EventClass format = new EventClass(name, place, dateStart, hallName, id, idEvent, type, nameEn, hallNameEn, placeEn, adress);

                movieTestArray.add(format);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the data JSON results", e);
        }
        return movieTestArray;
    }
}

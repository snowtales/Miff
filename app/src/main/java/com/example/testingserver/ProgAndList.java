package com.example.testingserver;

import java.util.List;

public class ProgAndList {
    private final String programm;
    private final List<MovieClass> perProgramm;

    public ProgAndList(String progr, List<MovieClass> films) {
        programm = progr;
        perProgramm = films;
    }

    public String getProgramm() {
        return programm;
    }

    public List<MovieClass> getPerProgramm() {
        return perProgramm;
    }

}

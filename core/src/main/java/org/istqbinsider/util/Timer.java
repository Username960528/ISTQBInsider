package org.istqbinsider.util;

public class Timer {
    private long startTime;
    private long duration;

    public void start(int seconds) {
        startTime = System.currentTimeMillis();
        duration = seconds * 1000;
    }

    public boolean isTimeUp() {
        return System.currentTimeMillis() - startTime >= duration;
    }
}
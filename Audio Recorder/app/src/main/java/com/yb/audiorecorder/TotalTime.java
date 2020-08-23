package com.yb.audiorecorder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TotalTime {
    public String getTotalTime(long duration) {
        Date now = new Date();

        long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - duration);
        long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - duration);
        long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - duration);

        if (seconds < 60) {
            return "Just Now.";
        } else if (minutes == 1) {
            return "A Minute Ago.";
        } else if (minutes > 1 && minutes < 60) {
            return minutes + " Minutes Ago.";
        } else if (hours == 1) {
            return "An Hour Ago.";
        } else if (hours > 1 && hours < 24) {
            return hours + " Hours Ago.";
        } else if (days == 1) {
            return "A Day Ago.";
        } else {
            return days + " Days Ago.";
        }
    }
}

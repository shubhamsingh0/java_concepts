package com.learn.javaconcepts;

import java.util.List;

public class Artist extends Singer {

    public Artist() {
    }
    public Artist(String todayPerformance) {
    }

    // overloaded methods
    public String perform(String requestSong) {
        System.out.println("Will sing the song : " + requestSong);
        return requestSong;
    }

    public List<String>  perform(List<String> requestSongs) {
        System.out.println("Will sing the song : " + requestSongs);
        return requestSongs;
    }
    // overriding method of parent class Singer
    @Override
    public void perform() {
        System.out.println("subclass: Will sing as per my wish");
    }
}

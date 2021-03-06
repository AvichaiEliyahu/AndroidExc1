package com.example.exc1;

import android.content.Context;
import android.location.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Top_10 {
    private ArrayList<HighScore> records;
    public static Top_10 instance;

    private Top_10() {
        records = MySP.getInstance().getArray(MySP.KEYS.TOP_10);
    }

    public static Top_10 getInstance() {
        if (instance == null) {
            instance = new Top_10();
        }
        return instance;
    }

    public void checkForRecordAndReplace(HighScore record) {
        if (records.size() < 10) {
            records.add(record);
            order();
        } else {
            for (int i = 0; i < records.size(); i++) {
                if (record.getAttacks() < records.get(i).getAttacks()) {
                    rollTableDown(i, record);
                    break;
                }
            }
        }
        MySP.getInstance().putArray(MySP.KEYS.TOP_10, this.records);
    }

    private void order() {
        Collections.sort(this.records);
    }

    private void rollTableDown(int i, HighScore newRecord) {
        records.add(i, newRecord);
        records.remove(10);
    }

    public ArrayList<HighScore> getRecords() {
        return records;
    }
}

class HighScore implements Comparable {
    private int attacks;
    private String name;
    private MyLocation location;

    public HighScore(int attacks, String name, Double lat, Double lon) {
        this.attacks = attacks;
        this.name = name;
        this.location = null;
        if (lat != null && lon != null) {
            this.location = new MyLocation(lat,lon);
        }

    }

    public MyLocation getLocation() {
        return location;
    }

    public HighScore setLocation(MyLocation location) {
        this.location = location;
        return this;
    }

    class MyLocation {
        private double lat;
        private double lon;

        public MyLocation(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public MyLocation setLat(double lat) {
            this.lat = lat;
            return this;
        }

        public double getLon() {
            return lon;
        }

        public MyLocation setLon(double lon) {
            this.lon = lon;
            return this;
        }
    }

    public int getAttacks() {
        return attacks;
    }

    public HighScore setAttacks(int attacks) {
        this.attacks = attacks;
        return this;
    }

    public String getName() {
        return name;
    }

    public HighScore setName(String name) {
        this.name = name;
        return this;
    }



    @Override
    public String toString() {
        return "HighScore{" +
                "attacks=" + attacks +
                ", name='" + name + '\'' +
                ", lat=" + location.lat +
                ", lon=" + location.lon +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        int compareAttacks = ((HighScore) o).getAttacks();
        /* For Ascending order*/
        return this.attacks - compareAttacks;
    }
}
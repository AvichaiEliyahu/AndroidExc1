package com.example.exc1;

import android.content.Context;
import android.location.Location;

import java.util.ArrayList;
import java.util.Map;

public class Top_10 {
    private ArrayList<HighScore> records;
    private static Top_10 instance;

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
        if (records.size() == 0) {
            records.add(record);
            return;
        } else {
            for (int i = 0; i < records.size(); i++) {
                if (record.getAttacks() > records.get(i).getAttacks()) {
                    rollTableDown(i, record);
                    return;
                }
            }
        }
        MySP.getInstance().putArray(MySP.KEYS.TOP_10,this.records);
    }

    private void rollTableDown(int i, HighScore newRecord) {
        records.add(i, newRecord);
        records.remove(10);
    }


}

class HighScore {
    private int attacks;
    private String name;
    private Location location;

    public HighScore(String name, int attacks, Location location) {
        this.attacks = attacks;
        this.name = name;
        this.location = location;
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

    public Location getLocation() {
        return location;
    }

    public HighScore setLocation(Location location) {
        this.location = location;
        return this;
    }
}
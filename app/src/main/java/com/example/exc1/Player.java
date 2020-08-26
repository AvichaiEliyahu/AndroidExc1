package com.example.exc1;

import android.util.Log;
import android.widget.Button;

public class Player implements IPlayer {
    private String name;
    private int HP;
    private int numOfAttacks = 0;

    public Player() {
    }

    public Player(String name, int HP) {
        this.name = name;
        this.HP = HP;
    }

    public String getName() {
        return name;
    }

    @Override
    public int addToAttackCounter() {
        this.numOfAttacks++;
        return numOfAttacks;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }
    @Override
    public int getHP() {
        return HP;
    }

    public Player setHP(int HP) {
        this.HP = HP;
        return this;
    }

    public boolean attack(int power){
        Log.d("player", "i'm" + name + "my hp = " + HP + "power -" + power);
        int newHP = this.getHP() - power;
        this.setHP(newHP);
        if(newHP>0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", HP=" + HP +
                '}';
    }
}

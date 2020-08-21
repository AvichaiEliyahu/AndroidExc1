package com.example.exc1;

public class Player extends AbstractPlayer {
    private String name;
    private int HP;

    public Player() {
    }

    public Player(String name, int HP) {
        this.name = name;
        this.HP = HP;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public int getHP() {
        return HP;
    }

    public Player setHP(int HP) {
        this.HP = HP;
        return this;
    }

    public boolean attack(int power){
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

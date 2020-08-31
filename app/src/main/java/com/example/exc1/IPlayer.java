package com.example.exc1;

public interface IPlayer {
    public boolean attack(int power);

    public int getHP();
    public String getName();
    public int addToAttackCounter();
    public int getNumOfAttacks();
}

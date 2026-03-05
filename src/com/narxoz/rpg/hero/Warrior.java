package com.narxoz.rpg.hero;

public class Warrior implements Hero {
    private final String name;
    private int health = 120;
    private final int power = 15;

    public Warrior(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void receiveDamage(int amount) {
        this.health = Math.max(0, this.health - amount);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
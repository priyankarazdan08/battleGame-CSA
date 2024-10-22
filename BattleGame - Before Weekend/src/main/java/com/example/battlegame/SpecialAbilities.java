package com.example.battlegame;

public class SpecialAbilities {
    private String name;
    private int attack = 0;
    public SpecialAbilities(String name){
        this.name = name;
        if(name.equals("Fire Breathing")) {
            attack = 50;
        }
        if (name.equals("Water Breathing")) {
            attack = 50;
        }
        if (name.equals("Demonic Whispers")) {
            attack = 100;
        }
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

}

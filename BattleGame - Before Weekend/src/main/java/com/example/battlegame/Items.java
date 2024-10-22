package com.example.battlegame;

import java.util.ArrayList;

public class Items {
    private String name;
    private int defence = 0;
    private int attack = 0;
    private int health = 0;
    private String type = "";
    private int cost;
    private String[] displayItems = new String[] {"Felix Felicis", "Sword Of Gryffindor", "Nimbus 2000", "Invisiblity Cloak", "Dittany", "Moly", "Aconite", "Dragon Heartstring Rowan", "Phoenix Feather Pear", "Horned Serpent Horn Walnut"};
    private String[] itemSrc = new String[] {"src/main/resources/images/felix.png", "src/main/resources/images/sword.png", "src/main/resources/images/numbus2000.png", "src/main/resources/images/The_Cloak_of_Invisibility_from_Harry_Potter.png", "src/main/resources/images/Dittany.png", "src/main/resources/images/Moly.png", "src/main/resources/images/Aconite.png", "src/main/resources/images/dragonHeart.png", "src/main/resources/images/pheanoix.png", "src/main/resources/images/honredserpent.png"};
    public Items(String name){

        this.name = name;
        if (name.equals("Felix Felicis")) {
            health = 100;
            type = "Potion";
            cost = 50;
            attack = 10;
        }
        if (name.equals("Sword Of Gryffindor")) {
            attack = 200;
            defence = 100;
            type = "Weapon";
            cost = 500;
        }
        if (name.equals("Nimbus 2000")) {
            defence = 50;
            type = "Defence";
            cost = 100;
            attack = 10;
        }
        if (name.equals("Invisiblity Cloak")) {
            defence = 200;
            health = 100;
            type = "Defence";
            cost = 800;
            attack = 50;
        }
        if (name.equals("Dittany")) {
            health = 100;
            type = "Herb";
            cost = 200;
            attack = 10;
        }
        if (name.equals("Moly")) {
            defence = 100;
            type = "Herb";
            cost = 200;
            attack = 10;
        }
        if (name.equals("Aconite")) {
            attack = 100;
            type = "Herb";
            cost = 200;
        }
        if (name.equals("Dragon Heartstring Rowan")) {
            attack = 350;
            type = "Wand";
            cost = 900;
        }
        if (name.equals("Phoenix Feather Pear")) {
            attack = 350;
            type = "Wand";
            cost = 900;
        }
        if (name.equals("Horned Serpent Horn Walnut")) {
            attack = 350;
            type = "Wand";
            cost = 900;
        }
    }

    public String getName() {
        return name;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefence() {
        return defence;
    }
    public int getHealth() {
        return health;
    }

    public int getCost() {
        return cost;
    }

    public String setItemImage() {
        for(int i = 0; i < displayItems.length; i++) {
            if(name.equals(displayItems[i])) {
                return itemSrc[i];
            }
        }
        return "";
    }

    public String returnType() {
        return type;
    }

    public String[] getType(Items item) {
        return new String[]{"Type: " + type, "Attack: " + attack, "Defence: " + defence, "Health: " + health, "Cost: " + cost + " XP"};
    }

}

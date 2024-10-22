package com.example.battlegame;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {

    private String name;
    private int hP;
    private int xP = 100;
    private int potionSkill;
    private int herbologySkill;
    private int defenceADarkArtsSkill;
    private ArrayList<ItemsOwned> inventory = new ArrayList<>();
    public Player (String name){
        this.name = name;
    }

    public void loadXP(int xp) {
        xP = xp;
    }
    public void loadHP(int hp) {
        hP = hp;
    }
    public void loadDefence(int defence) {
        defenceADarkArtsSkill = defence;
    }
    public void loadPotion(int potion) {
        potionSkill = potion;
    }
    public void loadHerb(int herb) {
        herbologySkill = herb;
    }

    public String getName() {
        return name;
    }

    public int getPotionSkill() {
        return potionSkill;
    }

    public int getHerbologySkill() {
        return herbologySkill;
    }

    public int getDefenceADarkArtsSkill() {
        return defenceADarkArtsSkill;
    }

    public int getHP() {
        return hP;
    }

    public int getXP() {
        return xP;
    }

    public void setXP(int amt) {
        xP = xP + amt;
    }

    public void setHP(int amt) {
        hP += amt;
    }

    public String setPlayerImage(String name) {
        if(name.equals("Hermione Granger")) {
            return "src/main/resources/images/220px-Hermione_Granger_poster.png";
        } if(name.equals("Harry Potter")) {
            return "src/main/resources/images/HarryPotter5poster.png";
        } if(name.equals("Ron Weasley")) {
            return "src/main/resources/images/Ron_Weasley_poster.png";
        }
        return "";
    }

    public ArrayList<ItemsOwned> getInventory() {
        return inventory;
    }

    public String[] createRandomStats() {
        hP = 1000 - (int) (Math.random() * 6);
        potionSkill = (int) (Math.random()*10 + 1);
        herbologySkill = (int) (Math.random()*10 + 1);
        defenceADarkArtsSkill = (int) (Math.random()*10 + 1);
        return new String[]{"Name: " + name, "HP: " + hP, "Potion Skill: " + potionSkill, "Close Combat Skill: " + herbologySkill, "Defence Against the Dark Arts: "+ defenceADarkArtsSkill};
    }

    public String[] getStats() {
        return new String[] {name, "HP: " + hP, "XP: " + xP, "Potion Skill: " + potionSkill, "Herbology Skill: " + herbologySkill, "Defence Skill: " + defenceADarkArtsSkill};
    }

    public ArrayList<String> battle(Opponent opponent) {
        ArrayList<String> eventLog = new ArrayList<>();
        if(hP > 0 && opponent.getHP() > 0) {
            int useItem = (int) (Math.random()*2 + 1);
            if(!inventory.isEmpty() && useItem == 1) {
                System.out.println("wentIn");
                int randomIndex = (int) (Math.random()* inventory.size());
                ItemsOwned item = inventory.get(randomIndex);
                item.setNumOwned(-1);
                hP += item.getItem().getHealth() + item.getItem().getDefence();
                opponent.setHP(-(item.getItem().getAttack() + defenceADarkArtsSkill*10 + herbologySkill + potionSkill - opponent.getDefenceSkill()*5));
                eventLog.add("You used " + item.getItem().getName());
            }
            if(inventory.isEmpty() || useItem == 2) {
                int rando = ((int)(Math.random()*20 + 2));
                opponent.setHP(-defenceADarkArtsSkill*2*rando);
                eventLog.add("You hit " + opponent.getName() + " and dealt " + defenceADarkArtsSkill*2*rando + " damage");
            }

            int useAbility = (int) (Math.random()*3 + 1);
            if(useAbility == 1) {
                hP = (hP - opponent.getSpecialAbility().getAttack()*2) + defenceADarkArtsSkill * 4;
                eventLog.add(opponent.getName() + " used special skill: " + opponent.getSpecialAbility().getName() + ". You defended " + defenceADarkArtsSkill*100/opponent.getSpecialAbility().getAttack() + "%");
            } if (useAbility == 2) {
                hP = (hP - opponent.getRandomItem().getAttack()*2) + defenceADarkArtsSkill * 4;
                eventLog.add(opponent.getName() + " used item: " + opponent.getRandomItem().getName() + ". You defended " + defenceADarkArtsSkill*100/opponent.getRandomItem().getAttack() + "%");
            } if (useAbility == 3){
                hP = (hP - opponent.getCloseCombatSkill() * 5) + defenceADarkArtsSkill;
                eventLog.add(opponent.getName() + " attacked you. You defended " + defenceADarkArtsSkill*100/(opponent.getCloseCombatSkill()* 5) + "%");
            }
        }
        else {
            eventLog.add("Battle has ended. " + checkBattle(opponent));
        }
        return eventLog;
    }

    public String checkBattle(Opponent opponent) {
        if (hP < 0 && opponent.getHP() > 0) {
            opponent.setLosses(1);
            return "You Lose";
        }
        if (opponent.getHP() < 0 && hP > 0) {
            opponent.setWins(1);
            return "You Win";
        }
        if(hP < 0 && opponent.getHP() < 0) {
            return "You Tied";
        }
        return "CURRENTLY BATTLING";
    }

}
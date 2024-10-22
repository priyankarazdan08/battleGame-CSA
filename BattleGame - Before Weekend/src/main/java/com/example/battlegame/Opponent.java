package com.example.battlegame;

public class Opponent {
    private String name;
    private String[] displayOpps = new String[] {"Welsh Green", "Chinese Fireball", "Hungarian Horntail", "Chimera", "Hippogriff", "Acromantula", "Basilisk"};
    private String[] oppSrc = new String[] {"src/main/resources/images/Common_Welsh_Green_-_animated-1.png", "src/main/resources/images/Chinese_Fireball.png", "src/main/resources/images/horntailwtf.png", "src/main/resources/images/Chimaera.png", "src/main/resources/images/Hippogrif_FBCFWW.png", "src/main/resources/images/Acromantula.png", "src/main/resources/images/Basilisk_-_FBcases.png"};
    private int worth;
    private int hP;
    private int tempHP;
    private int closeCombatSkill;
    private int defenceSkill;
    private SpecialAbilities specialAbility;
    private Items randomItem;
    private int numOfPlays = 0;
    private int wins = 0;
    private int losses = 0;

    public Opponent(String name){
        this.name = name;
    }
    public Opponent(String name, int numOfPlays) {
        this.name = name;
        this.numOfPlays = numOfPlays;
    }

    public String getName() {
        return name;
    }

    public int getCloseCombatSkill() {
        return closeCombatSkill;
    }
    public int getDefenceSkill() {
        return defenceSkill;
    }
    public int getHP() {
        return hP;
    }

    public int getNumOfPlays() {
        return numOfPlays;
    }

    public void setNumOfPlays(int amtToAdd) {
        numOfPlays += amtToAdd;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setWins(int amt) {
        wins += amt;
    }
    public void setLosses(int amt) {
        losses += amt;
    }

    public void setHP(int amtToAdd) {
        hP += amtToAdd;
    }
    public int getWorth() {
        return worth;
    }

    public String setOppImage() {
        for(int i = 0; i < displayOpps.length; i++) {
            if(name.equals(displayOpps[i])) {
                return oppSrc[i];
            }
        }
        return "";
    }

    public SpecialAbilities getSpecialAbility() {
        return specialAbility;
    }

    public Items getRandomItem() {
        return randomItem;
    }


    public String[] setStats(Player player) {
        if(name.equals("Welsh Green")) {
            worth = 5;
            hP = player.getHP()/(10 - worth);
            tempHP = hP;
            closeCombatSkill = (2*(player.getDefenceADarkArtsSkill() + player.getHerbologySkill())/(10-worth));
            defenceSkill = ((player.getPotionSkill() + player.getDefenceADarkArtsSkill())/(10-worth));
            specialAbility = new SpecialAbilities("Water Breathing");
            randomItem = new Items("Invisiblity Cloak");
        }  if(name.equals("Hungarian Horntail")) {
            worth = 9;
            hP = player.getHP()/(10 - worth);
            tempHP = hP;
            closeCombatSkill = (2*(player.getDefenceADarkArtsSkill() + player.getHerbologySkill())/(10-worth));
            defenceSkill = (4*(player.getPotionSkill() + player.getDefenceADarkArtsSkill())/(10-worth));
            specialAbility = new SpecialAbilities("Demonic Whispers");
            randomItem = new Items("Moly");
        } if (name.equals("Chinese Fireball")) {
            worth = 5;
            hP = player.getHP()/(10 - worth);
            tempHP = hP;
            closeCombatSkill = (2*(player.getDefenceADarkArtsSkill() + player.getHerbologySkill())/(10-worth));
            defenceSkill = ((player.getPotionSkill() + player.getDefenceADarkArtsSkill())/(10-worth));
            specialAbility = new SpecialAbilities("Fire Breathing");
            randomItem = new Items("Nimbus 2000");
        } if (name.equals("Chimera")) {
            worth = 7;
            hP = player.getHP()/(10 - worth);
            tempHP = hP;
            closeCombatSkill = (2*(player.getDefenceADarkArtsSkill() + player.getHerbologySkill())/(10-worth));
            defenceSkill = ((player.getPotionSkill() + player.getDefenceADarkArtsSkill())/(10-worth));
            specialAbility = new SpecialAbilities("Fire Breathing");
            randomItem = new Items("Invisiblity Cloak");
        } if(name.equals("Hippogriff")) {
            worth = 4;
            hP = player.getHP()/(10 - worth);
            tempHP = hP;
            closeCombatSkill = (2*(player.getDefenceADarkArtsSkill() + player.getHerbologySkill())/(10-worth));
            defenceSkill = ((player.getPotionSkill() + player.getDefenceADarkArtsSkill())/(10-worth));
            specialAbility = new SpecialAbilities("Water Breathing");
            randomItem = new Items("Nimbus 2000");
        } if (name.equals("Acromantula")) {
            worth = 6;
            hP = player.getHP()/(10 - worth);
            tempHP = hP;
            closeCombatSkill = (2*(player.getDefenceADarkArtsSkill() + player.getHerbologySkill())/(10-worth));
            defenceSkill = ((player.getPotionSkill() + player.getDefenceADarkArtsSkill())/(10-worth));
            specialAbility = new SpecialAbilities("Water Breathing");
            randomItem = new Items("Nimbus 2000");

        } if(name.equals("Basilisk")) {
            worth = 8;
            hP = player.getHP()/(10 - worth);
            tempHP = hP;
            closeCombatSkill = (2*(player.getDefenceADarkArtsSkill() + player.getHerbologySkill())/(10-worth));
            defenceSkill = ((player.getPotionSkill() + player.getDefenceADarkArtsSkill())/(10-worth));
            specialAbility = new SpecialAbilities("Demonic Whispers");
            randomItem = new Items("Sword Of Gryffindor");
        }
        return new String[]{"HP: " + hP, "Close Combat Skill: " + closeCombatSkill, "Defense Skill: "+ defenceSkill, "Special Ability: " + specialAbility.getName(),"Number Of Plays: " + numOfPlays};
    }

    public double oppHealth() {
        return (double) hP/tempHP;
    }

}

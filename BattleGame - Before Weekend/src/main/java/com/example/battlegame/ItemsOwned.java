package com.example.battlegame;

public class ItemsOwned {

    private Items item;
    private int numOwned;

    public ItemsOwned (Items item, int numOwned) {
        this.item = item;
        this.numOwned = numOwned;
    }

    public Items getItem() {
        return item;
    }

    public int setNumOwned(int numOwned) {
        this.numOwned += numOwned;
        return this.numOwned;
    }


    public int getNumOwned() {
        return numOwned;
    }
}

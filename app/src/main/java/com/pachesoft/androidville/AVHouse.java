package com.pachesoft.androidville;

public class AVHouse {
    public int id;
    public String name;
    public AVAddress address;
    public boolean selected;

    public AVHouse(int id, String name, AVAddress address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.selected = false;
    }
}

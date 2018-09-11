package com.pachesoft.androidville;

public class AVHouse {
    public int id;
    public String name;
    public AVAddress address;

    public AVHouse(int id, String name, AVAddress address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}

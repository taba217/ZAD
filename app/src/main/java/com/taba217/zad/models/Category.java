package com.taba217.zad.models;


public class Category {
    public String name, type, status;
    //   @PrimaryKey(autoGenerate = true)
    int id;

    Category() {

    }

    public Category(String cat_name, String cat_type, String status, int cat_id) {
        this.name = cat_name;
        this.type = cat_type;
        this.status = status;
        this.id = cat_id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
}


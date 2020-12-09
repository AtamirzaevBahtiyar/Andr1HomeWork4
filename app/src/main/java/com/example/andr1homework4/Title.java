package com.example.andr1homework4;

import java.io.Serializable;

public class Title implements Serializable {

    public String name;
    public String data;

    public Title(String name) {
        this.name = name;
        //this.lastName = lastName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

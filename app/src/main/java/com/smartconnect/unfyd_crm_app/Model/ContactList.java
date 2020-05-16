package com.smartconnect.unfyd_crm_app.Model;

public class ContactList {
    private  String name;
    private  String number;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ContactList(String name, String number) {
        this.name = name;
        this.number=number;


    }
}

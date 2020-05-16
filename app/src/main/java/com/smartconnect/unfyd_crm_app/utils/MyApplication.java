package com.smartconnect.unfyd_crm_app.utils;

import android.app.Application;

import com.smartconnect.unfyd_crm_app.Model.ContactList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MyApplication  extends Application {
    private String globalVariableOne;
    private ArrayList<ContactList> globalArrayList;

    public String getGlobalVariableOne() {
        return globalVariableOne;
    }

    public void setGlobalVariableOne(String globalVariableOne) {
        this.globalVariableOne = globalVariableOne;
    }

    public ArrayList<ContactList> getGlobalArrayList() {
        return globalArrayList;
    }

    public void setGlobalArrayList(ArrayList<ContactList> globalArrayList) {
        this.globalArrayList = globalArrayList;
    }
}
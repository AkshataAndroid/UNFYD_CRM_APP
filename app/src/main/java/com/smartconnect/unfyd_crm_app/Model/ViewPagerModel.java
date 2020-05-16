package com.smartconnect.unfyd_crm_app.Model;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerModel {


    ArrayList arrayList;
    String title;
    String total;

    public ViewPagerModel(ArrayList listView, String title, String total) {
        this.arrayList = listView;
        this.title = title;
        this.total = total;
    }

    public ArrayList getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

package com.smartconnect.unfyd_crm_app.Model;

public class TaskCurrentModel {
    private  String description;
    private  String date;
    private  String name;
    private  String subject;

    public TaskCurrentModel(String description, String date, String name, String subject) {
        this.description = description;
        this.date = date;
        this.name = name;
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }





}

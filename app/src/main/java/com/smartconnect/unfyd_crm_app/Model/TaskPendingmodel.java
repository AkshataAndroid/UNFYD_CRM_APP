package com.smartconnect.unfyd_crm_app.Model;

public class TaskPendingmodel {
    private  String descriptionpending;
    private  String datepending;
    private  String namepending;
    private  String subjectpending;

    public TaskPendingmodel(String descriptionpending, String datepending, String namepending, String subjectpending) {
        this.descriptionpending = descriptionpending;
        this.datepending = datepending;
        this.namepending = namepending;
        this.subjectpending = subjectpending;
    }

    public String getDescriptionpending() {
        return descriptionpending;
    }

    public void setDescriptionpending(String descriptionpending) {
        this.descriptionpending = descriptionpending;
    }

    public String getDatepending() {
        return datepending;
    }

    public void setDatepending(String datepending) {
        this.datepending = datepending;
    }

    public String getNamepending() {
        return namepending;
    }

    public void setNamepending(String namepending) {
        this.namepending = namepending;
    }

    public String getSubjectpending() {
        return subjectpending;
    }

    public void setSubjectpending(String subjectpending) {
        this.subjectpending = subjectpending;
    }
}

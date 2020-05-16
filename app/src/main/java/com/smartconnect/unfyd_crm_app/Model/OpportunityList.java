package com.smartconnect.unfyd_crm_app.Model;

public class OpportunityList {
    private  String contactname;
    private  String flag;
    private  String opportunity;
    private  String reminderdate;
    private  String nextstep;
  //  private  String mobilenumber;


    public OpportunityList(String contactname, String flag, String opportunity, String reminderdate, String nextstep) {
        this.contactname = contactname;
        this.flag = flag;
        this.opportunity = opportunity;
        this.reminderdate = reminderdate;
        this.nextstep = nextstep;
       // this.mobilenumber = mobilenumber;
    }


    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(String opportunity) {
        this.opportunity = opportunity;
    }

    public String getReminderdate() {
        return reminderdate;
    }

    public void setReminderdate(String reminderdate) {
        this.reminderdate = reminderdate;
    }

    public String getNextstep() {
        return nextstep;
    }

    public void setNextstep(String nextstep) {
        this.nextstep = nextstep;
    }

//    public String getMobilenumber() {
//        return mobilenumber;
//    }
//
//    public void setMobilenumber(String mobilenumber) {
//        this.mobilenumber = mobilenumber;
//    }





}

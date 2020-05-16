package com.smartconnect.unfyd_crm_app.Model;

public class User {

    private  String roleName,email,userName,
       roleID,capacity,mobile,userID;

    public User(String roleName, String email, String roleId, String capacity, String mobile, String userid, String userName) {
        this.roleName = roleName;
        this.email = email;
        this.roleID = roleId;
        this.capacity=capacity;
        this.mobile=mobile;
        this.userID=userid;
        this.userName=userName;

    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }







}

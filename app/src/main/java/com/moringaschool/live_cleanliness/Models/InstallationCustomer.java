package com.moringaschool.live_cleanliness.Models;

import android.content.Intent;

import java.util.Date;

public class InstallationCustomer {
    String name;
    int phone;
String email;
    String location;
    String time;
    String service;

    public InstallationCustomer(String name, int phone, String email, String location, String time, String service) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.time = time;
        this.service = service;
    }

    public InstallationCustomer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}

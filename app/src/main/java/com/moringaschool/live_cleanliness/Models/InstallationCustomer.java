package com.moringaschool.live_cleanliness.Models;

import java.util.Date;

public class InstallationCustomer {
String Location;
String description;
String whatItIs;
Date timeToBeDone;
String id;

    public InstallationCustomer(String location, String description, String whatItIs, Date timeToBeDone,String id) {
        Location = location;
        this.description = description;
        this.whatItIs = whatItIs;
        this.timeToBeDone = timeToBeDone;
        this.id=id;
    }

    public InstallationCustomer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhatItIs() {
        return whatItIs;
    }

    public void setWhatItIs(String whatItIs) {
        this.whatItIs = whatItIs;
    }

    public Date getTimeToBeDone() {
        return timeToBeDone;
    }

    public void setTimeToBeDone(Date timeToBeDone) {
        this.timeToBeDone = timeToBeDone;
    }
}

package com.example.societyguru.model;

import com.example.societyguru.enums.SocietyStatus;

public class SocietyModel {
    String sname;
    String area;
    String city;
    String state;
    String country;
    String pincode;
    String chairmancontact;
    String chairmanemail;
    String status = SocietyStatus.ACTIVE.name();
    String societyId;
    String searchname;
    String entrystatus;

    public enum SocietyEnum{
        SOCIETY,
        societyId,
        chairmanEmail,
        chairmanContact,
        sname,
        area,
        city,
        state,
        country,
        pinCode,
        status,
        members,
        entryStatus
    }

    public SocietyModel(String sname, String area, String city, String state, String country, String pincode, String chairmancontact, String chairmanemail, String status) {
        this.sname = sname;
        this.area = area;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.chairmancontact = chairmancontact;
        this.chairmanemail = chairmanemail;
        this.status = status;
        this.societyId = societyId;
        this.searchname = searchname;
        this.entrystatus = entrystatus;
    }

    public SocietyModel(){

    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getChairmancontact() {
        return chairmancontact;
    }

    public void setChairmancontact(String chairmancontact) {
        this.chairmancontact = chairmancontact;
    }

    public String getChairmanemail() {
        return chairmanemail;
    }

    public void setChairmanemail(String chairmanemail) {
        this.chairmanemail = chairmanemail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSocietyId() {
        return societyId;
    }

    public void setSocietyId(String societyId) {
        this.societyId = societyId;
    }

    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    public String getEntrystatus() {
        return entrystatus;
    }

    public void setEntrystatus(String entrystatus) {
        this.entrystatus = entrystatus;
    }
}

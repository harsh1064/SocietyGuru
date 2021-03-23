package com.example.societyguru.model;

import com.example.societyguru.enums.UserStatus;
import com.example.societyguru.enums.UserType;
import com.google.firebase.firestore.DocumentSnapshot;

import javax.xml.transform.sax.TemplatesHandler;

public class UserModel {
    String fname;
    String lname;
    String email;
    String mobile;
    String password;
    String flathousenumber;
    String searchname;
    String userType = UserType.CHAIRMAN.name();
    String societyId;
    String status;
   // String usertype = UserType.SOCIETY_MEMBER.name();

    public enum UserEnum{
        USER,
        fName,
        lName,
        email,
        mobile,
        password,
        userType,
        searchName,
        societyId,
        status,
        token,
        flatHouseNumber;

        public DocumentSnapshot compareTo(UserType chairman) {
            return null;
        }
    }


    public UserModel(String fname, String lname, String email, String mobile, String password, String flathousenumber, String searchname, String userType, String societyId, String status) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.flathousenumber = flathousenumber;
        this.searchname = searchname;
        this.userType = userType;
        this.societyId = societyId;
        this.status = status;
    }


    public UserModel(){

    }

    public UserModel(String edtchairmanfname, String edtchairmanlname, String edtchairmanemail, String edtchairmanmobile, String edtchairmanflathouseno, String edtchairmanpass, String userType) {
    this.fname = edtchairmanfname;
    this.lname = edtchairmanlname;
    this.email = edtchairmanemail;
    this.mobile = edtchairmanmobile;
    this.flathousenumber = edtchairmanflathouseno;
    this.password = edtchairmanpass;
    this.userType = userType;

    }

    public UserModel(String edtfname, String edtlname, String edtemail, String edtmobile, String edtflathouseno, String edtpass) {

    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFlathousenumber() {
        return flathousenumber;
    }

    public void setFlathousenumber(String flathousenumber) {
        this.flathousenumber = flathousenumber;
    }

    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSocietyId() {
        return societyId;
    }

    public void setSocietyId(String societyId) {
        this.societyId = societyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

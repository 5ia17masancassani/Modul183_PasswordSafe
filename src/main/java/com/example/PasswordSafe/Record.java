package com.example.PasswordSafe;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Record {

    private PasswordService passwordService = new PasswordService();

    private  int id;
    private  String website;
    private  String username;
    private  String password;
    private  String remark;

    public Record() {
        this.id = 0;
        this.website = "";
        this.username = "";
        this.password = "";
        this.remark = "";
    }

    public Record(int id, String website, String username, String password, String remark) {
        this.id = id;
        this.website = website;
        this.username = username;
        this.password = password;
        this.remark = remark;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return passwordService.decryptString(password);
    }

    public String getNormalPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

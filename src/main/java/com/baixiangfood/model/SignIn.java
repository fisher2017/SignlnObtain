package com.baixiangfood.model;

import java.util.Date;
import java.util.Objects;

public class SignIn {
    private Long id;

    private String openid;

    private Date time;

    private String position;

    private String bssid;

    private String ssid;

    private String day;

    private String positionresult;

    private String department;

    private String username;

    private String photoid;

    private String remark;

    private String approveid;

    private String approveuseropenid;

    private Date approvetime;

    private String approvestatus;

    private String approvetype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid == null ? null : bssid.trim();
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid == null ? null : ssid.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public String getPositionresult() {
        return positionresult;
    }

    public void setPositionresult(String positionresult) {
        this.positionresult = positionresult == null ? null : positionresult.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid == null ? null : photoid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getApproveid() {
        return approveid;
    }

    public void setApproveid(String approveid) {
        this.approveid = approveid == null ? null : approveid.trim();
    }

    public String getApproveuseropenid() {
        return approveuseropenid;
    }

    public void setApproveuseropenid(String approveuseropenid) {
        this.approveuseropenid = approveuseropenid == null ? null : approveuseropenid.trim();
    }

    public Date getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(Date approvetime) {
        this.approvetime = approvetime;
    }

    public String getApprovestatus() {
        return approvestatus;
    }

    public void setApprovestatus(String approvestatus) {
        this.approvestatus = approvestatus == null ? null : approvestatus.trim();
    }

    public String getApprovetype() {
        return approvetype;
    }

    public void setApprovetype(String approvetype) {
        this.approvetype = approvetype == null ? null : approvetype.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignIn signIn = (SignIn) o;
        return Objects.equals(openid, signIn.openid) &&
                Objects.equals(time, signIn.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(openid, time);
    }
}
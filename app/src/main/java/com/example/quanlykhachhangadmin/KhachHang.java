package com.example.quanlykhachhangadmin;

import java.io.Serializable;

public class KhachHang implements Serializable {
    private String idTeamView;
    private String Name;
    private String Address;
    private String PhoneNumber;
    private String Email;
    private String PassTeamView;

    public KhachHang() {
    }

    public KhachHang(String name, String address, String phoneNumber, String email, String passTeamView) {
        Name = name;
        Address = address;
        PhoneNumber = phoneNumber;
        Email = email;
        PassTeamView = passTeamView;
    }
    public KhachHang(String idTeamView, String name, String address, String phoneNumber, String email, String passTeamView) {
        this.idTeamView = idTeamView;
        Name = name;
        Address = address;
        PhoneNumber = phoneNumber;
        Email = email;
        PassTeamView = passTeamView;
    }

    public String getIdTeamView() {
        return idTeamView;
    }

    public void setIdTeamView(String idTeamView) {
        this.idTeamView = idTeamView;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassTeamView() {
        return PassTeamView;
    }

    public void setPassTeamView(String passTeamView) {
        PassTeamView = passTeamView;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "idTeamView='" + idTeamView + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Email='" + Email + '\'' +
                ", PassTeamView='" + PassTeamView + '\'' +
                '}';
    }
}

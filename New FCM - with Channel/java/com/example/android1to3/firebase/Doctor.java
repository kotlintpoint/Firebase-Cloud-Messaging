package com.example.android1to3.firebase;

public class Doctor  {

private String FirstName,LastName,Moblie;



    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMoblie() {
        return Moblie;
    }

    public void setMoblie(String moblie) {
        Moblie = moblie;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Moblie='" + Moblie + '\'' +
                '}';
    }
}

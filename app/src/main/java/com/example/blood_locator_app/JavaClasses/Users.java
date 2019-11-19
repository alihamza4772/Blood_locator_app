package com.example.blood_locator_app.JavaClasses;

public class Users {
    private String Name;
    private String Bloodgroup;
    private String Phoneno;
    private String Pass;
    private String City;



    public Users()
    {

    }
    public Users(String name, String bloodgroup, String phoneno, String pass,String city)
    {
        this.Name = name;
        this.Bloodgroup = bloodgroup;
        this.Phoneno = phoneno;
        this.Pass = pass;
        this.City = city;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBloodgroup() {
        return Bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        Bloodgroup = bloodgroup;
    }

    public String getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(String phoneno) {
        Phoneno = phoneno;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}

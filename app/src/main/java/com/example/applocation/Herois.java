package com.example.applocation;

import org.json.JSONException;
import org.json.JSONObject;

public class Herois {

    private int idHiro;
    private String name;
    private String fullName;
    private String placeOfBirth;

    public Herois(){   }
    public Herois(String name, String fullName, String placeOfBirth) {
        this.name = name;
        this.fullName = fullName;
        this.placeOfBirth = placeOfBirth;
    }

    public Herois(int idHiro, String name, String fullName, String placeOfBirth) {
        this.idHiro = idHiro;
        this.name = name;
        this.fullName = fullName;
        this.placeOfBirth = placeOfBirth;
    }

    //public  Herois(){}
    //public Herois(int idHiro, String name, String fullName, String placeOfBirth) {
    //this.setidHiro(idHiro);
    //  this.setname(name);
    //  this.setfullName(fullName);
    //  this.setplaceOfBirth(placeOfBirth);
    //}

    //public Herois(String name, String fullName, String placeOfBirth) {
    //    this.setname(name);
    //  this.setfullName(fullName);
    //  this.setplaceOfBirth(placeOfBirth);
    //}

    //public Herois(int idHiro, String name, String fullName, String placeOfBirth) {
    //  this.idHiro = idHiro;
    //  this.name = name;
    //  this.fullName = fullName;
    //  this.placeOfBirth = placeOfBirth;
    //}

    //public Herois(String name, String fullName, String placeOfBirth) {
    //  this.name = name;
    //  this.fullName = fullName;
    //  this.placeOfBirth = placeOfBirth;
    //}

    //Getter
    public int getIdHiro() { return idHiro; }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    //Setter
    public void setIdHiro(int id) { this.idHiro = idHiro; }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("idHiro", this.idHiro);
            obj.put("name", this.name);
            obj.put("fullName", this.fullName);
            obj.put("placeOfBirth", this.placeOfBirth);
        } catch (JSONException e) {
            //trace("DefaultListItem.toString JSONException: "+e.getMessage());
        }
        return obj;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenproject.exceltopdf;

/**
 *
 * @author Gifhary
 */
public class Format {

    private String number;
    private String name;
    private String fideID;
    private String fed;
    private String rtg;
    private String clubCty;

    public Format() {

    }

    public Format(String number, String name, String fideID, String fed, String rtg, String clubCty) {
        this.number = number;
        this.name = name;
        this.fideID = fideID;
        this.fed = fed;
        this.rtg = rtg;
        this.clubCty = clubCty;

    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setFideID(String fideID){
        this.fideID = fideID;
    }
    
    public String getFideID(){
        return fideID;
    }
    
    public void setFed (String fed){
        this.fed = fed;
    }
    
    public String getFed(){
        return fed;
    }
    
    public void setRtg (String rtg){
        this.rtg = rtg;
    }
    
    public String getRtg(){
        return rtg;
    }
    
    public void setClubCty (String clubCty){
        this.clubCty = clubCty;
    }
    
    public String getClubCty(){
        return clubCty;
    }
}

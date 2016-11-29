/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Iwan
 */
public class Kajak {
    
    private int kajakid;
    private String name;
    private String model;
    private String description;
    private String color;
    private float length;

    public Kajak(int kajakid, String name, String model, String description, String color, float length) {
        this.kajakid = kajakid;
        this.name = name;
        this.model = model;
        this.description = description;
        this.color = color;
        this.length = length;
    }

    public Kajak(int kajakid, String name) {
        this.kajakid = kajakid;
        this.name = name;        
    }

    public int getKajakId() {
        return kajakid;
    }

    public void setKajakid(int kajakid) {
        this.kajakid = kajakid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.g905.bars;

import java.time.Instant;
import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.json.JSONObject;

/**
 *
 * @author g905
 */
public class Document {
    
    private String name;
    private String number = "";
    private long created_at;
    private long updated_at;
    private Date date;
    private final BooleanProperty active = new SimpleBooleanProperty();
    
    public Document() {
        setActive(false);
    }
    
    public void fromJson(JSONObject jsonDoc) {
        number = "null".equals(jsonDoc.get("number").toString()) ? "" : jsonDoc.get("number").toString();
        name = jsonDoc.getString("name");
        created_at = jsonDoc.getLong("created_at");
        updated_at = jsonDoc.getLong("updated_at");
        date = new Date(created_at);
        setActive(updated_at + (60 * 60 * 24 * 60) > Instant.now().getEpochSecond()); //60s * 60m * 24h * 60d 
    }

    public BooleanProperty activeProperty() {
        return active;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public final boolean isActive() {
        return activeProperty().get();
    }

    public final void setActive(boolean active) {
        activeProperty().set(active);
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}

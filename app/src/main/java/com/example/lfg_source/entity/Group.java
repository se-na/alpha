package com.example.lfg_source.entity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Group {
    @JsonProperty("groupId")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("tags")
    private ArrayList<String> tags;
    @JsonProperty("active")
    private boolean active;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void changeAttributes(
            String description,
            boolean active,
            String groupName,
            ArrayList<String> tags){
        this.active = active;
        this.description = description;
        this.name =groupName;
        this.tags = tags;
    }
}

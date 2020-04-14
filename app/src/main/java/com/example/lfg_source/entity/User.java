package com.example.lfg_source.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("userId")
    private Integer id;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("tags")
    private String[] tags;
    @JsonProperty("active")
    private boolean active;
    

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String name){
        this.lastName = name;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}

package com.example.swipe_prototype;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("firstName")
    private String firstName;


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getfirstName(){
        return firstName;
    }

    public void setfirstName(String firstName){
        this.firstName = firstName;
    }

}
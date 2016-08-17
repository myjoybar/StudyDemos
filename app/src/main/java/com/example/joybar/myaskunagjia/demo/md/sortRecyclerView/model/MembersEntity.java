package com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.model;

/**
 * Created by joybar on 5/13/16.
 */
public class MembersEntity {

    public MembersEntity(String id, String username, String profession, String sortLetters) {
        this.id = id;
        this.username = username;
        this.profession = profession;
        this.sortLetters = sortLetters;
    }

    public MembersEntity(String id, String username, String profession) {
        this.id = id;
        this.username = username;
        this.profession = profession;
    }

    private String id;

    private String username;

    private String profession;

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    private String sortLetters;

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getProfession() {
        return profession;
    }
}

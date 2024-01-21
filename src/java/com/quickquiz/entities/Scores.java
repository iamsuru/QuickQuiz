/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickquiz.entities;

/**
 *
 * @author Suru's PC
 */
public class Scores {

    private String username;
    private int previous_score;
    private int highest_score;

    public Scores() {

    }

    public Scores(String username, int previous_score, int highest_score) {
        this.username = username;
        this.previous_score = previous_score;
        this.highest_score = highest_score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPrevious_score() {
        return previous_score;
    }

    public void setPrevious_score(int previous_score) {
        this.previous_score = previous_score;
    }

    public int getHighest_score() {
        return highest_score;
    }

    public void setHighest_score(int highest_score) {
        this.highest_score = highest_score;
    }
}

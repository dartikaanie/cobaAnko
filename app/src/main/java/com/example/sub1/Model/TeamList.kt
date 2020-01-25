package com.example.sub1.Model

import com.google.gson.annotations.SerializedName

class TeamList {

    @SerializedName("teams")
    lateinit var teams: List<TeamsItem>

    constructor(teams: List<TeamsItem>) {
        this.teams = teams
    }


    override fun toString(): String {
        return "Team{" +
                "teams = '" + teams + '\''.toString() +
                "}"
    }
}
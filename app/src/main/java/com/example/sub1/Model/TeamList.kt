package com.example.sub1.Model

import com.google.gson.annotations.SerializedName

class TeamList {

    @SerializedName("teams")
    var teams: List<TeamsItem>? = null

    override fun toString(): String {
        return "Team{" +
                "teams = '" + teams + '\''.toString() +
                "}"
    }
}
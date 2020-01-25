package com.example.sub1.Model

import com.google.gson.annotations.SerializedName

class MatchResponse {

    @SerializedName("event")
    lateinit var event: List<EventsItem>

    override fun toString(): String {
        return "MatchResponse{" +
                "event = '" + event + '\''.toString() +
                "}"
    }
}
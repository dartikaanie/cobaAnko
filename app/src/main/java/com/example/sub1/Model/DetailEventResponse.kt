package com.example.sub1.Model

import com.google.gson.annotations.SerializedName

class DetailEventResponse {

    @SerializedName("events")
    var events: List<DetailEvent>? = null

    override fun toString(): String {
        return "DetailEventResponse{" +
                "events = '" + events + '\''.toString() +
                "}"
    }
}
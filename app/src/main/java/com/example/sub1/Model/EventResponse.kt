package com.example.sub1.Model

import com.google.gson.annotations.SerializedName

class EventResponse {
    constructor(events: List<EventsItem>?)

    @SerializedName("events")
    var events: List<EventsItem>? = null

    override fun toString(): String {
        return "EventResponse{" +
                "events = '" + events + '\''.toString() +
                "}"
    }
}
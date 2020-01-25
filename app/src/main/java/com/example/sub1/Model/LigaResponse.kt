package com.example.sub1.Model

import com.google.gson.annotations.SerializedName

class LigaResponse {

    @SerializedName("leagues")
    var ligaList: List<Liga>? = null


    override fun toString(): String {
        return "LigaResponse{" +
                "leagues = '" + ligaList + '\''.toString() +
                "}"
    }
}
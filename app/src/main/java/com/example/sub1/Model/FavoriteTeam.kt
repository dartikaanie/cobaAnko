package com.example.sub1.Model

data class FavoriteTeam(
    val idTeam: String?,
    val strTeam: String? = null,
    val intFormedYear: String? = null,
    val strTeamLogo: String? = null,
    val strLeague: String? = null,
    val strDescriptionEN: String? = null
    ) {


    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val TEAM_ID: String = "TEAM_ID"
        const val STR_TEAM: String = "STR_TEAM"
        const val INT_FORMED_YEAR: String = "INT_FORMED_YEAR"
        const val STR_TEAM_LOGO: String = "STR_TEAM_LOGO"
        const val STR_LEAGUE: String = "STR_LEAGUE"
        const val STR_DESCRIPTIONEN: String = "STR_DESCRIPTIONEN"
    }
}
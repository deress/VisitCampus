package com.dicoding.visitcampus.util

import androidx.sqlite.db.SimpleSQLiteQuery

object SearchUtils {
    fun getSearchQuery(keyword: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM univ ")
        if (keyword != "") {
            simpleQuery.append("WHERE univName LIKE '%$keyword%'")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}
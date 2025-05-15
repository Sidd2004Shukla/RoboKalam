package com.example.robokalamassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolios")
data class Portfolio(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val college: String,
    val skills: List<String>,
    val projectTitle: String,
    val projectDescription: String
) 
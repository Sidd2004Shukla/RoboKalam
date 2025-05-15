package com.example.robokalamassignment.data.db

import androidx.room.*
import com.example.robokalamassignment.data.model.Portfolio
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {
    @Query("SELECT * FROM portfolios")
    fun getAllPortfolios(): Flow<List<Portfolio>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortfolio(portfolio: Portfolio)

    @Delete
    suspend fun deletePortfolio(portfolio: Portfolio)

    @Query("DELETE FROM portfolios")
    suspend fun deleteAllPortfolios()
} 
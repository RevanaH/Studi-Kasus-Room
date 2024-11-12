/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Interface ItemDao diberikan anotasi @Dao yang menandakana bahwa
 * ItemDao merupakan sebuah antarmuka Data Access Object (DAO) yang
 * menghubungkan interface untuk mengakses dan mengelola data dalam database.
 */
@Dao
interface ItemDao {
    /**
     * Untuk funsgi insert, diberikan anotasi @Insert yang berfungsi untuk
     * memasukan data baru kedalam database. Dalam anotasi Insert terdapat parameter
     * onConflict dengan OnConflictStrategy.IGNORE, sehingga apabila terjadi
     * conflict atau tabrakan dengan data dalam database maka fungsi insert akan mengabaikannya.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)
    /**
     * Untuk funsgi update, diberikan anotasi @Update yang berfungsi untuk
     * memperbarui data yang sudah ada dalam database dengan data yang baru.
     */
    @Update
    suspend fun update(item: Item)
    /**
     * Untuk funsgi delete, diberikan anotasi @Delete yang berfungsi untuk
     * menghapus data yang sudah ada dalam database.
     */
    @Delete
    suspend fun delete(item: Item)
    /**
     * Digunakan anotasi @Query untuk menajalankan sebuah perintah query SQL yang lebih rumit
     * dari perinath seperti insert, update dan delete.
     *
     * Untuk funsgi getItem, diberikan anotasi @Query untuk membuat perintah query SQL yang kustom
     * dengan memilih seluruh data dalam tabel items berdasarkan id.
     */
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>
    /**
     * Untuk funsgi getAllItems, diberikan anotasi @Query untuk membuat perintah query SQL yang kustom
     * dengan memilih seluruh data dalam tabel items dan diurutkan berdasarkan nama secara menaik.
     */
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}

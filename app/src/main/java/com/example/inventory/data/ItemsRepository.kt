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

import kotlinx.coroutines.flow.Flow

/**
 * interface ItemsRepository berfungsi untuk menjalankan funsgi
 * dari ItemDao sehingga funsgi dapat dijalakan dalam aplikasi.
 */
interface ItemsRepository {
    /**
     * Fungsi ini akan mendapatkan seluruh data dari Item dalam database.
     * Flow digunakan untuk melakukan proses secara real-time
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Fungsi ini akan menampilkan data dari Item dalam database berdasarkan
     * id dari data tersebut.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * funsgi ini akan memasukan data Item kedalam database.
     */
    suspend fun insertItem(item: Item)

    /**
     * Fungsi ini akan mengapus data Item dalam database.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Funsgi ini akan mengubah data Item yang ada dqalam database dengan data baru.
     *
     */
    suspend fun updateItem(item: Item)
}

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
 * Fungsi ini merupakan implementasi antarmuka untuk Itemsrepository.
 * Kelas OfflineItemsRepository akan mengoverride fungsi yang ada dalam ItemsRepository
 * dan memangilkan fungsi yang ada di dalam ItemDao sehingga funsgi dapat diimplementasikan
 * dalam aplikasi.
 */
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    /**
     * Fungsi ini akan mengoverride funsgi getAllItemsStream yang ada dalam ItemsRespository
     * dan akan mengambil seluruh data Item dari itemDao untuk menjalakan fungsi getAllItems
     * yang akan menampilkan seluruh data
     */
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()
    /**
     * Fungsi ini akan mengoverride funsgi getItemStream yang ada dalam ItemsRespository
     * dan akan mengambil seluruh data Item dan id dari Item dari itemDao untuk
     * menjalakan funsgi getItem berdasarkan id data.
     */
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)
    /**
     * Fungsi ini akan mengoverride funsgi InsertItem yang ada dalam ItemsRespository
     * dan akan mengambil seluruh data Item dari itemDao untuk menjalakan fungsi insert
     * yang akan menambah data sesuai dengan Item
     */
    override suspend fun insertItem(item: Item) = itemDao.insert(item)
    /**
     * Fungsi ini akan mengoverride funsgi deleteItem yang ada dalam ItemsRespository
     * dan akan mengambil seluruh data Item dari itemDao untuk menjalakan funsgi delete
     * yang akan mengapus data dalam database sesuai dengan Item
     */
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)
    /**
     * Fungsi ini akan mengoverride funsgi updateItem  yang ada dalam ItemsRespository
     * dan akan mengambil seluruh data Item dari itemDao untuk menjalakan funsgi update
     * untuk mengubah data dalam database sesuai dengan Item.
     */
    override suspend fun updateItem(item: Item) = itemDao.update(item)
}

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

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Kode mendeklrasasikan anotasi @Entity yang menandakan
 * sebuah kelas sebagai kelas Entity yang akan disimpan dalam database.
 * anotasi tableName untuk mendefinisikan tabel yang bernama items.
 * Kemudian kelas data Item akan digunakan untuk menyimpan data.
 * Data yang disimpan sesuai dengan atribut yang telah didefinisikan
 * dalam kelas Item degan setiap baris mnejadi kolom tabel.
 */
@Entity(tableName = "items")
data class Item(
    /**
     * Untuk setiap tabel, diperlukan sebuah primary key.
     * alam tabel ini id dijadikan sebuah primary key dengan menggunakan
     * anotasi @PrimaryKey dan dalam anotasi terdapat  parameter "autoGenerate = true"
     * sehingga primary key akan dibuat secara otiomatis setiap kali data ditambahkan.
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
/**
 * Sehingga dalam tabel items ini memiliki kolom id sebagai primary key,
 * kolom name, kolom price dan kolom quantity.
 */
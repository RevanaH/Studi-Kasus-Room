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

package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import java.text.NumberFormat

/**
 * Kelas ItemEntryViewModel dibuat untuk melakukan validasi terhadap input dan
 * menangani data Item terhadap database.
 */
class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    /**
     * Kemudian variabel itemUiState berfungsi untuk mendeteksi perubahan dan
     * menampilkan data Item baru
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Funsgi updateUiState ini akan memperbarui itemUiState dengan menjalankan validasi input
     * dengan validateInput. Validasi tersebut akan menentukan status isEntryValid yang ada dalam itemUiState.
     */
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    /**
     * Fungsi suspended saveItem ini digunakan untuk melakukan penyimpanan data Item kedalam database
     * melalui itemsRespository. Akan dicek apabila input yang dierikan valid, kemudian data akan disimpan menjadi Item
     * dengan menggunakan funsgi toItem
     */
    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }
    /**
     * Fungsi ini akan memvalidasi input yang dimasukan oleh pengguna dengan memeriksa bahwa
     * name, price dan quantity tidak kosong dan memiliki nilai atau input yang sesuai.
     */
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

/**
 * dataclass ini berfungsi untuk mewakili status UI untuk Item, Kelas ini akan
 * menyimpan Item yang dimasukan beserta dengan apakah Item tersebut valid.
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

/**
 * dataclass ini menyimpan isi dari Item yang dimasukan oleh pengguna, mulai dari name, price dan quantity
 * yang dimasukan oleh pengguna.
 */
data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

/**
 * Fungsi ini akan mengkonversi Item yang dimasukan oleh pengguna menjadi Item yang dapat disimpan kedalam
 * database. Akan di cek terlebih dahulu apakah input price merupakan Double atau apakah isinya null
 * dan di cek apakah isi dari Quantity merupakan interger atau null.
 */
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

/**
 * Fungsi ini akan mengubah input pirce yang dimasukna menjadi sesuai dengan
 * format mata uang, contohnya pada aplikasi ini akan diubah menjadi memiliki
 * simbol $ di depan harga.
 */
fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

/**
 * Fungsi ini akan mengkonversi Item menjadi ItemUIState sehingga status UI dapat diinisialisasikan.
 */
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Fungsi ini akan mengkonversi Item menadji ItemDetails yang akan diguanakan
 * sebagai tampilan untuk data pada UI
 */
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)

package fr.isen.chieze.myrestaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Data(
    val data : List<Menu>
): Serializable
data class Menu (
    @SerializedName("name_fr")
    val name_fr: String,
    @SerializedName("name_en")
    val name_en : String,
    @SerializedName("items")
    val items : List<Items>
): Serializable

data class Items (
    @SerializedName("name_fr")
    val name_fr : String,
    @SerializedName("images")
    val images : List<String>,
    @SerializedName("ingredients")
    val ingredients : List<Ingredients>,
    @SerializedName("prices")
    val prices : ArrayList<Prices>
) : Serializable

data class Prices(
    @SerializedName("price")
    val price: String
) : Serializable

data class Ingredients(
    @SerializedName("name_fr")
    val name_fr: String
) : Serializable

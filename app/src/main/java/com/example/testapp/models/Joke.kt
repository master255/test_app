package com.example.testapp.models

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("id") var id: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("setup") var setup: String? = null,
    @SerializedName("punchline") var punchline: String? = null
)

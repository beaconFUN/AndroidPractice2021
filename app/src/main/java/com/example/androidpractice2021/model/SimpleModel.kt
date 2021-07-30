package com.example.androidpractice2021.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * REST APIから受け取ったJSONをPOJOに変換するためのモデルです。
 * @author Squatarola 2021
 */
data class SimpleModel(
    @JsonProperty(value = "text") val text: String
)
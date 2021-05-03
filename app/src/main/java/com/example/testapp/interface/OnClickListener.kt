package com.example.testapp.`interface`

import androidx.cardview.widget.CardView
import com.example.testapp.models.Joke

interface OnClickListener {
    fun onClick(cardView: CardView, joke: Joke)
}
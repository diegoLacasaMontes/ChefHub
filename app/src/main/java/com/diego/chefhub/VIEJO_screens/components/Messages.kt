package com.diego.chefhub.VIEJO_screens.components

import android.content.Context
import android.widget.Toast

fun showMessage(
    context: Context,
    message: String
) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
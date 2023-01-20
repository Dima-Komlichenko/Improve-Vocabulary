package com.example.improvevocabulary.utlis

import android.app.Service
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object KeyboardHider {
    fun hideKeyboard(editText1: EditText, editText2: EditText, context: Context?) {
        val imm: InputMethodManager =
            context?.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText1.windowToken, 0);
        imm.hideSoftInputFromWindow(editText2.windowToken, 0);
        editText1.clearFocus()
        editText2.clearFocus()
    }
}
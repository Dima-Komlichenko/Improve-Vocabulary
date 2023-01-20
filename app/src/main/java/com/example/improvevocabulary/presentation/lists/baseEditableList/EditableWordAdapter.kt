package com.example.improvevocabulary.presentation.lists.baseEditableList

import android.app.Service
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Language
import com.example.domain.utils.DataValidator
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.EditableWordItemBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.lists.baseList.WordAdapter
import com.example.improvevocabulary.utlis.DataConverter
import com.example.improvevocabulary.utlis.KeyboardHider
import com.example.improvevocabulary.utlis.TextToSpeech
import com.google.android.material.snackbar.Snackbar

open class EditableWordAdapter(private val tts: TextToSpeech, val languageFrom: Language, val languageOf: Language,
) : WordAdapter(tts) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditableWordHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.editable_word_item, parent, false)
        context = parent.context
        return EditableWordHolder(view, tts)
    }

    open inner class EditableWordHolder(
        override var item: View,
        tts: TextToSpeech,
    ) :
        WordHolder(item, tts) {

        private lateinit var bindingEditable: EditableWordItemBinding
        var textWatchers: ArrayList<TextWatcher> = arrayListOf()

        override fun bind(word: WordPair) {

            bindingEditable = EditableWordItemBinding.bind(item)

            if (textWatchers.isNotEmpty()) {
                bindingEditable.etWord.removeTextChangedListener(textWatchers[0])
                bindingEditable.tvTranslate.removeTextChangedListener(textWatchers[1])
                textWatchers.clear()
            }

            super.bind(word)

            bindingEditable.etWord.setText(word.word)
            bindingEditable.tvTranslate.setText(word.translate)

            bindingEditable.etWord.hint =
                DataConverter.capitalize(languageOf.value.toString())

            bindingEditable.tvTranslate.hint =
                DataConverter.capitalize(languageFrom.value.toString())

            textChangingHandlerToShowCardDetails(bindingEditable.etWord, wordPair.word)
            textChangingHandlerToShowCardDetails(bindingEditable.tvTranslate, wordPair.translate)

            bindingEditable.btnSave.setOnClickListener {
                btnSaveHandler()
            }
        }

        protected fun btnSaveHandler(): Boolean = with(bindingEditable) {
            KeyboardHider.hideKeyboard(bindingEditable.etWord, bindingEditable.tvTranslate, context)

            if (tvTranslate.text.toString() == "" || etWord.text.toString() == "") {
                Snackbar.make(
                    item.parent as RecyclerView,
                    context!!.resources.getString(R.string.empty_field),
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                )
                    .show()
                return false
            }

            if (!DataValidator.isDataValid(etWord.text.toString(), languageOf) ||
                !DataValidator.isDataValid(tvTranslate.text.toString(), languageFrom)
            ) {
                Snackbar.make(
                    item.parent as RecyclerView,
                    context!!.resources.getString(R.string.data_is_not_valid),
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                )
                    .show()
                return false
            }

            etWord.setText(DataConverter.validateSpaces(etWord.text.toString()))
            tvTranslate.setText(DataConverter.validateSpaces(tvTranslate.text.toString()))

            editWord(wordPair, etWord.text.toString(), tvTranslate.text.toString())
            tvWord.text = wordPair.word
            Snackbar.make(
                item.parent as RecyclerView,
                "The word " + " \"" + wordPair.word + "\" "
                        + " is saved",
                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            )
                .show()
            btnSave.visibility = View.GONE

            ConstraintSet().apply {
                clone(clWordView)
                clear(btnSound.id, ConstraintSet.START)
                clear(btnSound.id, ConstraintSet.END)
                connect(btnSound.id, ConstraintSet.START, etWord.id, ConstraintSet.END)
                connect(btnSound.id, ConstraintSet.END, clWordView.id, ConstraintSet.END)
            }.applyTo(clWordView)

            animateView(btnSound, 65F, 0F, 0F, 0F)

            return true
        }

        override fun showCardDetails() = with(bindingEditable) {
            super.showCardDetails()
            tvWord.visibility = View.GONE
            etWord.visibility = View.VISIBLE

            tvTranslate.setOnClickListener {} // delete listener from base adapter so that don't close card on click
        }

        override fun setConstraintsToShowCardDetails() = with(bindingEditable) {
            super.setConstraintsToShowCardDetails()
            ConstraintSet().apply {
                clone(clWordView)
                //etWord
                clear(etWord.id, ConstraintSet.TOP)
                clear(etWord.id, ConstraintSet.BOTTOM)
                connect(etWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP, 52)
                connect(etWord.id, ConstraintSet.BOTTOM, dividingLine.id, ConstraintSet.TOP)
            }.applyTo(clWordView)
        }

        override fun hideCardDetails() = with(bindingEditable) {
            super.hideCardDetails()
            btnSave.visibility = View.GONE
            tvWord.visibility = View.VISIBLE
            etWord.visibility = View.GONE
            KeyboardHider.hideKeyboard(bindingEditable.etWord, bindingEditable.tvTranslate, context)
        }


        protected fun textChangingHandlerToShowCardDetails(editText: EditText, word: String) =
            with(bindingEditable) {
                val textWatcher = object : TextWatcher {

                    private var wasTextChanged: Boolean = false
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        if (wordPair.word == etWord.text.toString() && wordPair.translate == tvTranslate.text.toString()) {
                            if (wasTextChanged) return

                            animateView(btnSound, 65F, 0F, 0F, 0F)

                            btnSave.visibility = View.GONE

                            ConstraintSet().apply {
                                clone(clWordView)
                                clear(btnSound.id, ConstraintSet.START)
                                connect(btnSound.id, ConstraintSet.LEFT, dividingLine.id, ConstraintSet.RIGHT)
                            }.applyTo(clWordView)
                            wasTextChanged = false
                        }
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (btnSave.visibility == View.VISIBLE) return

                        if (wordPair.word == etWord.text.toString() && wordPair.translate == tvTranslate.text.toString()) return

                        if (editText.text.toString() == word) return
                        animateView(btnSound, -65F, 0F, 0F, 0F)


                        btnSave.visibility = View.VISIBLE
                        ConstraintSet().apply {
                            clone(clWordView)
                            clear(btnSound.id, ConstraintSet.START)
                            clear(btnSound.id, ConstraintSet.END)
                            connect(btnSound.id, ConstraintSet.LEFT, btnSave.id, ConstraintSet.RIGHT, 12)
                            connect(btnSound.id, ConstraintSet.RIGHT, clWordView.id, ConstraintSet.RIGHT, 12)
                        }.applyTo(clWordView)

                        wasTextChanged = true
                    }
                }
                textWatchers.add(textWatcher)
                editText.addTextChangedListener(textWatcher)
            }

    }
}
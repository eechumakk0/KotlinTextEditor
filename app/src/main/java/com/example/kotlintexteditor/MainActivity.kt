package com.example.kotlintexteditor

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var etEditor: EditText
    private lateinit var btnBold: Button
    private lateinit var btnItalic: Button
    private lateinit var btnUnderline: Button
    private lateinit var btnClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация элементов
        etEditor = findViewById(R.id.etEditor)
        btnBold = findViewById(R.id.btnBold)
        btnItalic = findViewById(R.id.btnItalic)
        btnUnderline = findViewById(R.id.btnUnderline)
        btnClear = findViewById(R.id.btnClear)

        // Обработчики кнопок
        btnBold.setOnClickListener { applyStyle(StyleSpan(Typeface.BOLD)) }
        btnItalic.setOnClickListener { applyStyle(StyleSpan(Typeface.ITALIC)) }
        btnUnderline.setOnClickListener { applyStyle(UnderlineSpan()) }

        btnClear.setOnClickListener {
            etEditor.text.clear()
        }
    }

    private fun applyStyle(span: Any) {
        val start = etEditor.selectionStart
        val end = etEditor.selectionEnd

        if (start == end) {
            // Если нет выделения, применяем ко всему тексту
            val text = SpannableString(etEditor.text)
            text.setSpan(span, 0, text.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            etEditor.setText(text)
        } else {
            // Применяем к выделенному тексту
            val text = SpannableString(etEditor.text)
            text.setSpan(span, start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            etEditor.setText(text)
            etEditor.setSelection(start, end) // Восстанавливаем выделение
        }
    }
}
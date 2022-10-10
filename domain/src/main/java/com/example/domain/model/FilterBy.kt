package com.example.domain.model

enum class PressedSortButton {
    ALPHABETICALLY, NON_ALPHABETICALLY, NEWER, OLDER
}

data class FilterBy(var value: PressedSortButton)
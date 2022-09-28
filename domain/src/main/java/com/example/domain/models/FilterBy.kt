package com.example.domain.models

enum class PressedSortButton {
    ALPHABETICALLY, NON_ALPHABETICALLY, NEWER, OLDER
}

data class FilterBy(var value: PressedSortButton)
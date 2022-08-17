package com.example.domain.models

enum class PressedSortButton {
    NO_ONE, ALPHABETICALLY, NON_ALPHABETICALLY, NEWER, OLDER
}

class FilterBy(var value: PressedSortButton)
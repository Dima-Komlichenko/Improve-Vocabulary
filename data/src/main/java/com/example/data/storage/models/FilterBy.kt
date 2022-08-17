package com.example.data.storage.models

enum class PressedSortButton {
    NO_ONE, ALPHABETICALLY, NON_ALPHABETICALLY, NEWER, OLDER
}

class FilterBy(var value: PressedSortButton)
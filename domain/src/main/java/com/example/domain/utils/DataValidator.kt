package com.example.domain.utils

import com.example.domain.model.Language
import com.example.domain.model.Languages


class DataValidator {

    companion object {
        fun isDataValid(data: String, language: Language): Boolean {
            var pattern = ""

            pattern = when (language.value) {
                Languages.ENGLISH -> "[abcdefghijklmnoprqstuvwxyz ,()]+"
                Languages.ARABIC -> "[أ ب ج د ﻩ و ز ح ط ي ك ل م ن س ع ف ص ق ر ش ت ث خ ذ ض ظ غ,()]+"
                Languages.CZECH -> "[aábcčdďeéěfghiíjklmnňoópqrřsštťuúůvwxyýzž ,()]+"
                Languages.GERMAN -> "[ abcdefghijklmnopqrstuvwxyz ,()]+"
                Languages.SPANISH -> "[abcdefghijklmnnñopqrstuvwxyz ,()]+"
                Languages.FRENCH -> "[abcdefghkijklmnopqrstuvwxyzéèêëàâîïôùûüÿæœç ,()]+"
                Languages.HINDI -> "[अआएईऍऎऐइओऑऒऊऔउबभचछडढफफ़गघग़हजझकखख़लळऌऴॡमनङञणऩॐपक़रऋॠऱसशषटतठदथधड़ढ़वयय़ज़ ,()]+"
                Languages.ITALIAN -> "[abcdefghijklmnopqrstuvwxyz ,()]+"
                Languages.HEBREW -> "[סֻכָּרסוּסכָּלאֳנִיָהדוֹדדֹבגִנָהגִילזֵרזֶהאֱמֶתהֲהַהָתּתשׂשׁשרקץצףפפּעסןנםמלךְךּךככּיטחזוהדגבבּא ,()]+"
                Languages.JAPANESE -> "[あべウェげでえよぜぜいやからまんおぺれさたうふかつちゃしゃしゃイウイイえゆやアイウエオカキクケコサシスセソタチツテトナ二ヌネノハヒフヘホマミムメモヤユヨラリルレロワヲンあいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん ,()]+"
                Languages.KAZAKH -> "[аәбвгғдеёжзийкқлмнңоөпрстуұүфхһцчшщъыіьэюя ,()]+"
                Languages.KOREAN -> "[ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎㅏㅑㅓㅕㅗㅛㅜㅠㅡㅣㄲㄸㅃㅉㅆㅢㅚㅐㅟㅔㅒㅖㅘㅝㅙㅞ ,()]+"
                Languages.POLISH -> "[aąbcćdeęfghijklłmnńoópqrsśtuvwxyzźż ,()]+"
                Languages.PORTUGUESE -> "[abcdefghijlmnopqrstuvwxzáâãàçéêíóôõú ,()]+"
                Languages.RUSSIAN -> "[абвгдеёжзийклмнопрстуфхцчшщъыьэюя ,()]+"
                Languages.TURKISH -> "[abcçdefgğhıijklmnoöprsştuüvyz ,()]+"
                Languages.UKRAINIAN -> "[ абвгйцуккенгґдеєжзиіїйклмнопрстуфхцчшщьюя ,()]+"
                Languages.CHINESE -> "[手田水口廿卜山戈人心日尸木火土竹十大中Ｚ難金女月弓 ,()]+"
            }

            return Regex(pattern, RegexOption.IGNORE_CASE)
                .matches(data)
        }
    }
}
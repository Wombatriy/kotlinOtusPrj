package models

enum class WalkerWorkMode {
    PROD,
    TEST, // работа с imdb с временным кэшом
    STUB // просто возврат заглушек
}
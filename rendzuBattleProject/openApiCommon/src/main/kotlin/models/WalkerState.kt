package models

enum class WalkerState {
    NONE,
    RUNNING, // идет обработка
    FAILING, // возникла проблема
    FINISHED // успешно завершена
}
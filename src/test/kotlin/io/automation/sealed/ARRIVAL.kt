package io.automation.sealed

sealed class ARRIVAL(val arrivalTime: String) {
    object MORNING : ARRIVAL("collapsed_arrival_morning")
    object AFTERNOON : ARRIVAL("collapsed_arrival_noon")
    object EVENING : ARRIVAL("collapsed_arrival_evening")
    object NIGHT : ARRIVAL("collapsed_arrival_night")
}
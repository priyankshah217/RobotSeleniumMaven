package io.automation.sealed

sealed class DEPARTURE(val departureTime: String) {
    object MORNING : DEPARTURE("collapsed_departure_morning")
    object AFTERNOON : DEPARTURE("collapsed_departure_noon")
    object EVENING : DEPARTURE("collapsed_departure_evening")
    object NIGHT : DEPARTURE("collapsed_departure_night")
}

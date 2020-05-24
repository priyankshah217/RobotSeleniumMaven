package io.automation.sealed

sealed class FLIGHT(val flightTimings: String) {
    object NONSTOP : FLIGHT("collapsed_stop_nonStop")
    object ONESTOP : FLIGHT("collapsed_stop_oneStop")
}

package io.automation

import com.google.common.truth.Truth.assertThat
import io.automation.base.BaseTest
import io.automation.enums.AIRLINE
import io.automation.enums.ARRIVAL
import io.automation.enums.DEPARTURE
import io.automation.enums.FLIGHT
import io.automation.pages.HomePage
import io.automation.pages.searchingFlights
import org.junit.jupiter.api.Test

class MakeMytripTest : BaseTest() {

    @Test
    fun `I want to do search flights based on valid criteria -- Traditional Page Chaining`() {
        val homePage = HomePage()
        homePage.from("BOM")
                .to("DEL")
                .onAnyDate()
        val searchResultPage = homePage.search()
        searchResultPage.by(FLIGHT.NONSTOP.flightTiming,
                DEPARTURE.NIGHT.departureTime,
                ARRIVAL.NIGHT.arrivalTime,
                AIRLINE.INDIGO.airlineName)
        assertThat(searchResultPage.getAllFlights()?.size).isEqualTo(4)
    }

    @Test
    fun `I want to do search flights based on valid criteria -- Robot Pattern`() {
        searchingFlights {
            from("BOM")
            to("DEL")
            onAnyDate()
        } filter {
            by(FLIGHT.NONSTOP.flightTiming,
                    DEPARTURE.NIGHT.departureTime,
                    ARRIVAL.NIGHT.arrivalTime,
                    AIRLINE.INDIGO.airlineName)
            assertThat(getAllFlights()?.size).isEqualTo(4)
        }
    }

    @Test
    fun `I should not get flights for invalid search criteria`() {
        searchingFlights {
            from("BOM")
            to("DEL")
            onAnyDate()
        } filter {
            by(FLIGHT.ONESTOP.flightTiming,
                    DEPARTURE.MORNING.departureTime,
                    ARRIVAL.MORNING.arrivalTime,
                    AIRLINE.INDIGO.airlineName)
            assertThat(getAllFlights()?.size).isEqualTo(0)
        }
    }

}

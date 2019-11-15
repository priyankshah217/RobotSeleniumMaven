package io.automation

import com.google.common.truth.Truth.assertThat
import io.automation.base.BaseTest
import io.automation.enums.AIRLINE
import io.automation.enums.ARRRIVAL
import io.automation.enums.DEPARTURE
import io.automation.enums.FLIGHT
import io.automation.pages.HomePage
import io.automation.pages.searchFlight
import org.junit.jupiter.api.Test

class MakeMytripTest : BaseTest() {

    @Test
    fun `I want to do search flights based on valid criteria -- Traditional Page Chaining`() {
        val homePage = HomePage()
        homePage.from("BOM")
        homePage.to("DEL")
        homePage.onAnyDate()
        val searchResultPage = homePage.search()
        searchResultPage.filterBy(FLIGHT.NONSTOP.flightTiming,
                DEPARTURE.NIGHT.departureTime,
                ARRRIVAL.NIGHT.arrivalTime,
                AIRLINE.INDIGO.airlineName)
        assertThat(searchResultPage.getAllFlights()?.size).isEqualTo(4)
    }

    @Test
    fun `I want to do search flights based on valid criteria -- Robot Pattern`() {
        searchFlight {
            from("BOM")
            to("DEL")
            onAnyDate()
        } search {
            filterBy(FLIGHT.NONSTOP.flightTiming,
                    DEPARTURE.NIGHT.departureTime,
                    ARRRIVAL.NIGHT.arrivalTime,
                    AIRLINE.INDIGO.airlineName)
            assertThat(getAllFlights()?.size).isEqualTo(4)
        }
    }

    @Test
    fun `I should not get flights for invalid search criteria`() {
        searchFlight {
            from("BOM")
            to("DEL")
            onAnyDate()
        } search {
            filterBy(FLIGHT.ONESTOP.flightTiming,
                    DEPARTURE.MORNING.departureTime,
                    ARRRIVAL.MORNING.arrivalTime,
                    AIRLINE.INDIGO.airlineName)
            assertThat(getAllFlights()?.size).isEqualTo(0)
        }
    }

}

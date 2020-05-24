package io.automation

import com.google.common.truth.Truth.assertThat
import io.automation.base.BaseTest
import io.automation.pages.HomePage
import io.automation.pages.searchingFlights
import io.automation.sealed.AIRLINE
import io.automation.sealed.ARRIVAL
import io.automation.sealed.DEPARTURE
import io.automation.sealed.FLIGHT
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@Execution(ExecutionMode.CONCURRENT)
class MakeMytripTest : BaseTest() {

    @Test
    fun `I want to do search flights based on valid criteria -- Traditional Page Chaining`() {
        val homePage = HomePage()
        homePage.from("BOM")
            .to("DEL")
            .onAnyDate()
        val searchResultPage = homePage.search()
        searchResultPage.by(
            FLIGHT.NONSTOP.flightTimings,
            DEPARTURE.NIGHT.departureTime,
            ARRIVAL.NIGHT.arrivalTime,
            AIRLINE.INDIGO.airlineName
        )
        assertThat(searchResultPage.getAllFlights().size).isEqualTo(2)
    }

    @Test
    fun `I want to do search flights based on valid criteria -- Robot Pattern`() {

        searchingFlights {
            from("BOM")
            to("DEL")
            onAnyDate()
        } filter {
            by(
                FLIGHT.NONSTOP.flightTimings,
                DEPARTURE.NIGHT.departureTime,
                ARRIVAL.NIGHT.arrivalTime,
                AIRLINE.INDIGO.airlineName
            )
            assertThat(getAllFlights().size).isEqualTo(2)
        }
    }

    @Test
    fun `I should not get flights for invalid search criteria`() {
        searchingFlights {
            from("BOM")
            to("DEL")
            onAnyDate()
        } filter {
            by(
                FLIGHT.ONESTOP.flightTimings,
                DEPARTURE.MORNING.departureTime,
                ARRIVAL.MORNING.arrivalTime,
                AIRLINE.INDIGO.airlineName
            )
            assertThat(getAllFlights().size).isEqualTo(0)
        }
    }

}

package io.automation.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import screens.BasePage

fun onSearchResultPage(function: SearchResultPage.() -> Unit) = SearchResultPage().apply(function)
class SearchResultPage : BasePage() {
    private val searchResultLocator = By.cssSelector(".fli-list.one-way")

    fun getAllFlights(): MutableList<WebElement>? {
        return getListOfElements(searchResultLocator)
    }

    fun filterFlightsOn(flightType: String, departureOn: String, arrivalOn: String, airlines: String) {

        clickOn(By.xpath("//span[@data-filtertext='$flightType']"))

        clickOn(By.xpath("//span[@data-filtertext='$departureOn']"))

        clickOn(By.xpath("//span[@data-filtertext='$arrivalOn']"))

        clickOn(By.xpath("//span[@data-filtertext='$airlines']"))
    }
}
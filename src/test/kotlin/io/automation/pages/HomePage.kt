package io.automation.pages

import org.openqa.selenium.By
import screens.BasePage

fun onMakeMytripHomePage(function: HomePage.() -> Unit) = HomePage().apply(function)

class HomePage : BasePage() {
    private val fromCityLocator = By.id("fromCity")
    private val locationDropDownLocator = By.cssSelector("#react-autowhatever-1 > div > ul")
    private val searchButtonLocator = By.cssSelector(".primaryBtn")
    private val dateLocator = By.cssSelector(".DayPicker-Month:nth-child(2) .DayPicker-Week:nth-child(4) > " +
            ".DayPicker-Day:nth-child(4) > .dateInnerCell")

    fun selectFromLocationFromList(sourceStation: String, destinationStation: String) {
        val sourceLocator = By.xpath("//div[text()='$sourceStation']")
        val destinationLocator = By.xpath("//div[text()='$destinationStation']")
        clickOn(fromCityLocator)
        clickOn(getElement(locationDropDownLocator)?.findElement(sourceLocator))
        clickOn(getElement(locationDropDownLocator)?.findElement(destinationLocator))
        clickOn(getElement(dateLocator))
    }

    infix fun searchFlights(function: SearchResultPage.() -> Unit): SearchResultPage {
        clickOn(searchButtonLocator)
        return SearchResultPage().apply(function)
    }
}
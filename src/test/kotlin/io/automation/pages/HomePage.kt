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

    fun selectDate() {
        clickOn(getElement(dateLocator))
    }

    fun selectSourceOfFlight(sourceStation: String) {
        val sourceLocator = By.xpath("//div[text()='$sourceStation']")
        clickOn(fromCityLocator)
        clickOn(getElement(locationDropDownLocator)?.findElement(sourceLocator))
    }

    fun selectDestination(destinationStation: String) {
        val destinationLocator = By.xpath("//div[text()='$destinationStation']")
        clickOn(getElement(locationDropDownLocator)?.findElement(destinationLocator))
    }

    infix fun search(function: SearchResultPage.() -> Unit): SearchResultPage {
        clickOn(searchButtonLocator)
        return SearchResultPage().apply(function)
    }
}
package io.automation.pages

import org.openqa.selenium.By
import screens.BasePage

fun searchingFlights(function: HomePage.() -> Unit) = HomePage().apply(function)

class HomePage : BasePage() {
    private val fromCityLocator = By.id("fromCity")
    private val locationDropDownLocator = By.cssSelector("#react-autowhatever-1 > div > ul")
    private val searchButtonLocator = By.cssSelector(".primaryBtn")
    private val dateLocator = By.cssSelector(".DayPicker-Month:nth-child(2) .DayPicker-Week:nth-child(4) > " +
            ".DayPicker-Day:nth-child(4) > .dateInnerCell")

    fun onAnyDate(): HomePage {
        clickOn(getElement(dateLocator))
        return this
    }

    fun from(sourceStation: String): HomePage {
        val sourceLocator = By.xpath("//div[text()='$sourceStation']")
        clickOn(fromCityLocator)
        clickOn(getElement(locationDropDownLocator)?.findElement(sourceLocator))
        return this
    }

    fun to(destinationStation: String): HomePage {
        val destinationLocator = By.xpath("//div[text()='$destinationStation']")
        clickOn(getElement(locationDropDownLocator)?.findElement(destinationLocator))
        return this
    }

    fun search(): SearchResultPage {
        clickOn(searchButtonLocator)
        return SearchResultPage()
    }

    infix fun filter(function: SearchResultPage.() -> Unit): SearchResultPage {
        clickOn(searchButtonLocator)
        return SearchResultPage().apply(function)
    }
}
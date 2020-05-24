package io.automation.base

import io.automation.utils.DriverManager
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


abstract class BasePage {

    inline infix fun <reified T> T.and(function: T.() -> Unit) = this.apply(function)

    fun clickOn(webElement: WebElement) {
        getElement(webElement).click()
    }

    private fun getElement(webElement: WebElement): WebElement {
        val wait = getWebDriverWait()
        return wait.until(ExpectedConditions.elementToBeClickable(webElement))
    }

    fun clickOn(by: By) {
        getElement(by).click()
    }

    fun enterText(by: By, value: String) {
        val webElement = getElement(by)
        webElement.clear()
        webElement.sendKeys(value)
    }

    fun getElement(by: By): WebElement {
        val wait = getWebDriverWait()
        return wait.until { it.findElement(by) }
    }

    fun getListOfElements(by: By): MutableList<WebElement> {
        val wait = getWebDriverWait()
        return wait.until { it.findElements(by) }
    }

    private fun getWebDriverWait(): WebDriverWait {
        val driver: WebDriver? = DriverManager.getDriver()
        return WebDriverWait(driver, Duration.ofSeconds(30))
    }

    fun elementIsPresent(by: By): Boolean {
        return getElement(by) == null
    }

    fun elementIsNotPresent(by: By): Boolean {
        return elementIsPresent(by)
    }

}

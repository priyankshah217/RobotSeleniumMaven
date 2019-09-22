package io.automation.utils


import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver


object DriverManager {
    @JvmStatic
    private var driver: WebDriver? = null

    fun getDriver(): WebDriver? {
        return when {
            driver == null -> {
                driver = ChromeDriver()
                driver
            }
            (driver as ChromeDriver).sessionId == null -> {
                driver = ChromeDriver()
                driver
            }
            else -> driver
        }
    }
}
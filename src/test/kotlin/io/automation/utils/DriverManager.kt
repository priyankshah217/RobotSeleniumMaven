package io.automation.utils


import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.RemoteWebDriver


object DriverManager {
    @JvmStatic
    private var driver: WebDriver? = null

    fun getDriver(): WebDriver? {
        var browserName: String?

        browserName = System.getProperty("browser.name") ?: null
        if (browserName == null || browserName == "") {
            val properties = PropertyReader.readPropertiesFromFile("automation-config.properties")
            browserName = properties.getProperty("browser.name").toLowerCase()
        }
        return when {
            driver == null -> {
                driver = getDriverType(browserName)
                driver
            }
            (driver as RemoteWebDriver).sessionId == null -> {
                driver = getDriverType(browserName)
                driver
            }
            else -> driver
        }
    }

    private fun getDriverType(browserName: String?): RemoteWebDriver? {
        val driver: RemoteWebDriver?
        when {
            browserName != "" -> driver = when (browserName) {
                "firefox", "ff" -> FirefoxDriver()
                "chrome" -> ChromeDriver()
                else -> throw RuntimeException("Invalid browser type")
            }
            else -> throw RuntimeException("Invalid browser type")
        }
        return driver
    }
}
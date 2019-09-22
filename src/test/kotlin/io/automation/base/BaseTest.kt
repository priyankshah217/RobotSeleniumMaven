package io.automation.base

import io.automation.utils.DriverManager
import io.automation.utils.PropertyReader
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseTest {

    @BeforeEach
    fun setUp() {
        val properties =
                PropertyReader.readPropertiesFromFile("automation-config.properties")
        DriverManager.getDriver()?.get(properties.getProperty("app.url"))
    }

    @AfterEach
    fun tearDown() {
        DriverManager.getDriver()?.quit()
    }
}

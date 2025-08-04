package ru.netology.testing.uiautomator

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.junit.Assert.assertEquals
import org.junit.Test
import org.openqa.selenium.By as SeleniumBy
import java.net.URL
import java.time.Duration

class AppiumTextTests {

    @Test
    fun testEmptyInputIgnoredAppium() {
        val options = UiAutomator2Options()
            .setPlatformName("Android")
            .setDeviceName("Android Emulator")
            .setAppPackage("ru.netology.testing.uiautomator")
            .setAppActivity("ru.netology.testing.uiautomator.MainActivity")
            .setNewCommandTimeout(Duration.ofSeconds(60))

        val driver = AndroidDriver(URL("http://127.0.0.1:4723"), options)

        val originalText = driver.findElement(SeleniumBy.id("ru.netology.testing.uiautomator:id/textToBeChanged")).text

        val inputField = driver.findElement(SeleniumBy.id("ru.netology.testing.uiautomator:id/userInput"))
        inputField.sendKeys("   ")

        driver.findElement(SeleniumBy.id("ru.netology.testing.uiautomator:id/buttonChange")).click()

        val updatedText = driver.findElement(SeleniumBy.id("ru.netology.testing.uiautomator:id/textToBeChanged")).text
        assertEquals(originalText, updatedText)

        driver.quit()
    }

    @Test
    fun testTextInNewActivityAppium() {
        val options = UiAutomator2Options()
            .setPlatformName("Android")
            .setDeviceName("Android Emulator")
            .setAppPackage("ru.netology.testing.uiautomator")
            .setAppActivity("ru.netology.testing.uiautomator.MainActivity")
            .setNewCommandTimeout(Duration.ofSeconds(60))

        val driver = AndroidDriver(URL("http://127.0.0.1:4723"), options)

        val inputText = "Netology UI Test"

        val inputField = driver.findElement(SeleniumBy.id("ru.netology.testing.uiautomator:id/userInput"))
        inputField.sendKeys(inputText)

        driver.findElement(SeleniumBy.id("ru.netology.testing.uiautomator:id/buttonActivity")).click()

        Thread.sleep(2000)

        val resultText = driver.findElement(SeleniumBy.id("ru.netology.testing.uiautomator:id/text")).text
        assertEquals(inputText, resultText)

        driver.quit()
    }
}
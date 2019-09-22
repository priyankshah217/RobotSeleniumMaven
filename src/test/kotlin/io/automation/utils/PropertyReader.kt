package io.automation.utils

import java.io.IOException
import java.io.InputStream
import java.util.*


@Suppress("JAVA_CLASS_ON_COMPANION")
class PropertyReader {
    companion object {
        @Throws(IOException::class)
        fun readPropertiesFromFile(propertiesFile: String): Properties {
            var stream: InputStream? = null
            try {
                stream = javaClass.classLoader.getResourceAsStream(propertiesFile)
                val properties = Properties()
                properties.load(stream)
                return properties
            } finally {
                if (stream != null) {
                    try {
                        stream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }
}
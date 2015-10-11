package de.maxvogler.kit_language_courses

import com.beust.jcommander.JCommander
import com.google.gson.GsonBuilder
import de.maxvogler.kit_language_courses.models.Configuration

public fun main(args: Array<String>) {
    // Load configuration
    val config = Configuration()
    JCommander(config, *args)

    // Scrape courses
    val courses = Scraper().loadCourses()

    // Display courses
    if (config.displayJson) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        println(gson.toJson(courses))
    } else {
        printTable(courses.map { it.properties.values().toTypedArray() })
    }
}

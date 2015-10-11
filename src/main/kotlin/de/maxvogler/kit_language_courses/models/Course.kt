package de.maxvogler.kit_language_courses.models

import kotlin.properties.get

data class Course(val properties: Map<String, Any>) {

    val language: String by properties
    val name: String by properties
    val lecturer: String by properties
    val building: String by properties
    val room: String by properties
    val weekday: String by properties
    val time: String by properties
    val price: String by properties
    val ects: String by properties

}

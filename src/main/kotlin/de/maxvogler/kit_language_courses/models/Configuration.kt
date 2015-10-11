package de.maxvogler.kit_language_courses.models

import com.beust.jcommander.Parameter

data class Configuration(
        @Parameter(names = arrayOf("-json", "-j"))
        var displayJson: Boolean = false
)

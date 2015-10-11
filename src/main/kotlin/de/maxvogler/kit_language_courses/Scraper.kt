package de.maxvogler.kit_language_courses

import de.maxvogler.kit_language_courses.models.Course
import de.maxvogler.kit_language_courses.models.Language
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.net.URL
import java.util.stream.Collectors

class Scraper {

    private val baseUrl = URL("https://www.spz.kit.edu/")

    private val languagesUrl = URL(baseUrl, "/angebot.php")

    /**
     * A Map that associates headings used in tables (e.g. "Kurs") with [Course] properties (e.g. [Course.name].
     */
    private val searchStrings = mapOf(
            "Kurs" to "name",
            "Dozent" to "lecturer",
            "Geb" to "building",
            "Raum" to "room",
            "Tag" to "weekday",
            "Zeit" to "time",
            "Preis" to "price",
            "ECTS" to "ects"
    )

    public fun loadLanguages(): List<Language> {
        return load(languagesUrl).select("#content table a").map { parseLanguage(it) }
    }


    public fun loadCourses(): List<Course> {
        return loadLanguages()
                .toParallelStream()
                .flatMap { loadCourses(it).toStream() }
                .collect(Collectors.toList<Course>())
    }

    public fun loadCourses(language: Language): List<Course> {
        val doc = load(language.url)
        val order = parseColumnOrder(doc.select("#content table tr").first())
        return doc.select("#content table tr").filter { isCourseRow(it) }.map { parseCourse(language, order, it) }

    }

    private fun parseColumnOrder(row: Element): Map<Int, String> {
        val headings = row.children().mapIndexed { i, element -> i to element.text() }

        // Look for common headings used in the table (e.g. "Kurs" and associate the index of the <td>
        // with a property of [de.maxvogler.kit_language_courses.de.maxvogler.kit_language_courses.models.Course].
        return searchStrings.mapKeys { search ->
            headings.first { it.second.contains(search.key, true) }.first
        }
    }

    private fun isCourseRow(row: Element): Boolean {
        val children = row.children()
        val text = row.text()

        return children.size() == searchStrings.size()
                // skip rows which contain multiple headings (e.g. "Kurs", "Dozent", …)
                && searchStrings.keySet().filter { text.contains(it, ignoreCase = true) }.size() <= 2
                // Skip empty rows
                && children.none { it.text().isBlank() }
    }

    private fun parseCourse(language: Language, indexToPropertyName: Map<Int, String>, row: Element): Course {
        val texts = row.children()
                .map { it.text().trim() }
                // Map the index of every <td> to the corresponding property name, using the Map from [parseColumnOrder]
                .mapIndexed { i, text -> indexToPropertyName[i]!! to text }
                .toMutableSet()

        texts.add("language" to language.name)

        return Course(texts.toMap())
    }

    private fun parseLanguage(cell: Element): Language {
        return Language(cell.text().capitalize(), URL(baseUrl, cell.attr("href")))
    }

    private fun load(url: URL): Document {
        return Jsoup.connect(url.toString()).get()
    }

}

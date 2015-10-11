package de.maxvogler.kit_language_courses

import de.vandermeer.asciitable.v2.V2_AsciiTable
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer
import de.vandermeer.asciitable.v2.render.WidthLongestLine
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes
import java.util.stream.Stream

public fun <K> measure(name: String, func: () -> K): K {
    var start = System.currentTimeMillis()
    val value = func()

    println("$name took ${System.currentTimeMillis() - start}ms")
    return value
}

@Suppress("UNCHECKED_CAST", "PLATFORM_CLASS_MAPPED_TO_KOTLIN")
public fun <T> Collection<T>.toParallelStream(): Stream<T> {
    return (this as java.util.Collection<T>).parallelStream()
}

@Suppress("UNCHECKED_CAST", "PLATFORM_CLASS_MAPPED_TO_KOTLIN")
public fun <T> Collection<T>.toStream(): Stream<T> {
    return (this as java.util.Collection<T>).stream()
}

public fun printTable(rows: List<Array<Any>>) {
    val table = V2_AsciiTable()
    val renderer = V2_AsciiTableRenderer()
            .setTheme(V2_E_TableThemes.UTF_LIGHT.get())
            .setWidth(WidthLongestLine())

    table.addStrongRule()
    rows.forEach { table.addRow(*it) }
    table.addStrongRule()
    println(renderer.render(table))
}

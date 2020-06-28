package util

import java.io.File

object ResourceUtils
{
    fun getResource(path: String): Array<File>?
    {
        return File(this::class.java.getResource(path).toURI()).listFiles()
    }

    fun readProperties(file: File): Map<String, String>
    {
        return file.readLines()
            .map { it.split("=").map { it.trim() } }
            .associate { it[0] to it[1] }
    }
}
package ru.lion.netmedia.utils

/*
работает, но улучшим позже чтобы   */
fun shortenNumber(number: Int): String = shortenNumber(number.toLong())

fun shortenNumber(number: Long): String {
    return when {
        number < 1000 -> "$number"
        number < 10_000 -> formatWithSuffix(number,1_000L,"K")

        number < 1_000_000 -> "${number / 1000}K"


        else -> formatWithSuffix(number,1_000_000L,"M")
    }
}
private fun formatWithSuffix(number: Long, divisor: Long, suffix: String): String {
    val formatted = number / divisor.toDouble()
    return if (formatted % 1 == 0.0) "${formatted.toInt()}$suffix" else String.format("%.1f$suffix", formatted)
}
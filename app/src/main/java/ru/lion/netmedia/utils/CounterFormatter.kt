package ru.lion.netmedia.utils

import java.util.Locale

fun updateCounterDisplay(count: Int): String {
    return when {
        count >= 1_000_000 -> String.format(Locale.US,"%.1fM", count / 1_000_000.0)
        count >= 10_000 -> String.format(Locale.US,"%dK", count / 1_000)
        count >= 1_000 -> String.format(Locale.US,"%.1fK",count/ 1_000.0)
        else -> count.toString()
    }
}
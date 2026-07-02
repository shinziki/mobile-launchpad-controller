package com.example.pocketlaunchpad

object LaunchpadMapping {
    fun gridNote(row: Int, col: Int): Int = row * 16 + col

    fun sceneNote(row: Int): Int = row * 16 + 8

    val topRowCC = intArrayOf(104, 105, 106, 107, 108, 109, 110, 111)
}
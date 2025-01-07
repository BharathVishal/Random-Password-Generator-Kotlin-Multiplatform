package org.bharathvishal.randompasswordgenerator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
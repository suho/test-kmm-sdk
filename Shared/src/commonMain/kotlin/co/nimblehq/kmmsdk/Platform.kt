package co.nimblehq.kmmsdk

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
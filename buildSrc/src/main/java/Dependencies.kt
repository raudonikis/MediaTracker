object Versions {
    const val navigation = "2.2.2"
    const val appCompat = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val kotlin = "1.3.0"
    const val junit = "4.12"
    const val junitExt = "1.1.1"
    const val espresso = "3.2.0"
    const val dagger = "2.28"
    const val timber = "4.7.1"
    const val liveEvent = "1.2.0"
}

object Sdk {
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
    const val applicationId = "com.raudonikis.movietracker"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.kotlin}"
}

object Support {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val liveEvent = "com.github.hadilq.liveevent:liveevent:${Versions.liveEvent}"
}

object Testing {
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object Navigation {
    const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object Dagger {
    const val main = "com.google.dagger:dagger:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object Timber {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}
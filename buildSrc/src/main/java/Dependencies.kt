import org.gradle.api.JavaVersion

object Modules {
    const val app = ":app"
    const val core = ":Core"
    const val model = ":Model"
    const val repository = ":Repository"
    const val utils = ":Utils"

    //Features
    const val historyScreen = ":HistoryScreen"
}

object Config {
    const val application_id = "com.example.proftranslatorfixgit"
    const val compile_sdk = 31
    const val min_sdk = 24
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Versions {
    //Design
    const val appcompat = "1.4.1"
    const val material = "1.5.0"
    const val constraintLayout = "2.1.3"

    //Kotlin
    const val core = "1.7.0"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.5.1"
    const val coroutinesAndroid = "1.5.1"
    const val legacySupport = "1.0.0"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "3.12.1"
    const val adapterCoroutines = "0.9.2"

    //Koin
    const val koinAndroid = "3.1.2"
    const val koinCore = "3.1.2"
    const val koinAndroidCompat = "3.1.2"

    //Picasso
    const val picasso = "2.71828"

    //Glide
    const val glide = "4.11.0"
    const val glideCompiler = "4.11.0"

    //Coil
    const val coil = "0.11.0"

    //Room
    const val roomKtx = "2.4.0"
    const val runtime = "2.4.0"
    const val roomCompiler = "2.4.0"

    //Test
    const val jUnit = "4.13.2"
    const val runner = "1.4.0"
    const val espressoCore = "3.4.0"
    const val androidJunit = "1.1.3"
    const val fragment = "1.4.1"
    const val recycler = "3.4.0"
    const val coreTesting = "2.1.0"
    const val coroutinesTest = "1.4.3"
    const val robolectricTest = "4.5.1"
    const val coreTest = "1.3.0"
    const val truthTest = "1.3.0"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val koin_android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
    const val koin_core = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koin_android_compat = "io.insert-koin:koin-android-compat:${Versions.koinAndroidCompat}"
}

object Picasso {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val fragmentTest = "androidx.fragment:fragment-testing:${Versions.fragment}"
    const val recyclerTest = "androidx.test.espresso:espresso-contrib:${Versions.recycler}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val robolectricTest = "org.robolectric:robolectric:${Versions.robolectricTest}"
    const val coreTest = "androidx.test:core:${Versions.coreTest}"
    const val truthTest = "androidx.test.ext:truth:${Versions.truthTest}"
}

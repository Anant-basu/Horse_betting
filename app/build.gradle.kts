plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.horseriding"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.horseriding"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    //room database
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.6.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //bottom navigation
    implementation("com.google.android.material:material:1.10.0")

    //Gif drawable
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.28")

    //Image slider
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    //graph view
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

}
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.apollographql.apollo3").version("3.6.0")
}

android {
    namespace = "com.backdoor.walcartandroidtest"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.backdoor.walcartandroidtest"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation("com.apollographql.apollo3:apollo-runtime:3.6.0")
    implementation("org.jetbrains:annotations:13.0")
    testImplementation("org.jetbrains:annotations:13.0")

    implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.squareup.retrofit2:retrofit:2.0.2")
    implementation("com.squareup.retrofit2:converter-gson:2.0.2")

}
apollo {
    packageName.set("com.example")
}
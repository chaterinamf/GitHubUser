import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    id("kotlin-android")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.dicoding.githubuser.core"
    compileSdk = 34
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "GITHUB_API_KEY",
                gradleLocalProperties(rootDir, providers).getProperty("GITHUB_API_KEY")
            )
            buildConfigField(
                "String",
                "BASE_URL", gradleLocalProperties(rootDir, providers).getProperty("BASE_URL")
            )
        }
        debug {
            buildConfigField(
                "String",
                "GITHUB_API_KEY",
                gradleLocalProperties(rootDir, providers).getProperty("GITHUB_API_KEY")
            )
            buildConfigField(
                "String",
                "BASE_URL", gradleLocalProperties(rootDir, providers).getProperty("BASE_URL")
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    sharedDependencies(versionCatalogs.named("libs"))

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.gson)

    api(libs.lifecycle.livedata.ktx)
}
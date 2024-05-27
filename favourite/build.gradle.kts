plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.android.kotlin)
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.ksp)
}
android {
    namespace = "com.dicoding.githubuser.favourite"
    compileSdk = 34
    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
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
    implementation(project(":app"))
    implementation(project(":core"))
    sharedDependencies(versionCatalogs.named("libs"))
}
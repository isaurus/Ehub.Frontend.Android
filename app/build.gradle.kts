plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt.android)

    // Gradle plugin de los servicios de Google
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.isaac.ehub"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.isaac.ehub"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    // Para SafeArgs (¿necesario?)
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    // AppCompat
    implementation(libs.appcompat)
    implementation(libs.androidx.legacy.support.v4)
    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Constraint
    implementation(libs.constraintlayout)
    // Material
    implementation(libs.material)
    // Fragment
    implementation(libs.fragment)
    // Activity
    implementation(libs.activity)
    // LifeCycle (LiveData)
    implementation(libs.androidx.lifecycle.livedata)
    // Lifecycle (Runtime)
    implementation(libs.androidx.lifecycle.lifecycle.runtime)
    // LifeCycle(ViewModel)
    implementation(libs.androidx.lifecycle.viewmodel.android)
    // DaggerHilt
    implementation(libs.hilt.android)
    annotationProcessor(libs.hilt.compiler)
    // Firebase (BoM)
    implementation(platform(libs.firebase.bom))
    // Firebase (Google Analytics)
    implementation(libs.firebase.analytics)
    // Fire base (auth)
    implementation(libs.firebase.auth)
    // Navigation Component
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // Lottie
    //implementation(libs.lottie)

}
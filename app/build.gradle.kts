import com.android.build.gradle.AppExtension
import java.io.FileNotFoundException
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.lear.compose"
    compileSdk = libs.versions.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "com.lear.compose"
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()
        versionCode = libs.versions.app.version.code.get().toInt()
        versionName = libs.versions.app.version.name.get()

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        val keystoreProperties = Properties()
        try {
            keystoreProperties.load(File(rootDir, "keystore.properties").inputStream())
            create("release") {
                storeFile = file("kp.keystore.jks")
                storePassword = keystoreProperties["KSTOREPWD"] as String
                keyAlias = keystoreProperties["KEYSTORE_ALIAS"] as String
                keyPassword = keystoreProperties["KEYPWD"] as String
            }
        } catch (e: FileNotFoundException) {
            println("Warning: keystore.properties file not found.")
        }
    }
    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = try {
                signingConfigs.getByName("release")
            } catch (e: UnknownDomainObjectException) {
                println("SigningConfig with not found. Skipping...")
                null
            }
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
    implementation("androidx.compose.material3:material3")

    implementation(platform("androidx.compose:compose-bom:2024.09.03"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.2")


    //==================== Dependency Injection ====================
    val hilt_version = "2.50"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    ksp("com.google.dagger:hilt-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //==================== Networking ====================
    val retrofit_version = "2.9.0"
    val okhttp_version = "4.12.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit_version")
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp_version")

    //==================== Database ====================
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    //==================== Deserializer ====================
    val moshi_version = "1.14.0"
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshi_version")
    implementation("com.squareup.moshi:moshi:$moshi_version")
    implementation("com.squareup.moshi:moshi-kotlin:$moshi_version")
    implementation("com.squareup.moshi:moshi-adapters:$moshi_version")

    //==================== Logging ====================
    implementation("com.jakewharton.timber:timber:5.0.1")

    //==================== Image Loading ====================
    implementation("io.coil-kt:coil-compose:2.7.0")

    //==================== Navigation ====================
    implementation("androidx.navigation:navigation-compose:2.8.2")

    //==================== Memory Leak Detection ====================
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.9.1")
}


//This task is used for .AAB file renaming to following format:
//{application_id}-v{version_name}-build{version_code}-{product_flavor}-{build_type}.aab

tasks.register("renameBundle") {
    doLast {
        val androidExtension = project.extensions.findByType(AppExtension::class.java)

        androidExtension?.also { android ->

            println("Variant variants: ${android.applicationVariants}")

            android.applicationVariants.forEach { variant ->
                println("===============================")
                println("Variant name: ${variant.name}")
                println("Version name: ${variant.versionName}")
                println("Version code: ${variant.versionCode}")
                println("Flavor name: ${variant.productFlavors.map { it.name }.joinToString()}")
                println("Build directory: ${layout.buildDirectory.get()}")

                val flavorName = variant.productFlavors.map { it.name }.joinToString()
                val variantName = variant.name
                val buildType = variant.buildType.name
                val versionName = variant.versionName
                val versionCode = variant.versionCode

                val bundleDir = "${layout.buildDirectory.get()}/outputs/bundle/${variantName}"

                val oldFileName = "app-${flavorName}-${buildType}.aab"
                val newFileName =
                    "${variant.applicationId}-" + "v$versionName-" + "build$versionCode-" + "$flavorName-" + "$buildType.aab"

                //remove an old file if exist
                delete("$bundleDir/$newFileName")

                val originalFile = file("$bundleDir/$oldFileName")

                if (!originalFile.exists()) {
                    println("Original '${originalFile.absolutePath}' file not found")
                } else {
                    println("newFileName: $newFileName")
                    val newFile = file("$bundleDir/$newFileName")
                    originalFile.renameTo(newFile)
                    println("Renamed '$oldFileName' file to $newFileName")
                }
            }

        }
            ?: println("Android extension not found. This task is only valid for Android application projects.")
    }
}

tasks.matching { task -> task.name.contains("release", ignoreCase = true) }.configureEach {
    finalizedBy("renameBundle")
}
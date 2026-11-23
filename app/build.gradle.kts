import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.kotlin.serialization)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")

if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { input ->
        localProperties.load(input)
    }
}

val signingProperties = Properties()
val signingPropertiesFile = rootProject.file("keystore.properties")

if (signingPropertiesFile.exists()) {
    signingPropertiesFile.inputStream().use { input ->
        signingProperties.load(input)
    }
}

fun releaseConfigValue(name: String): String =
    providers.gradleProperty(name).orNull
        ?: localProperties.getProperty(name)
        ?: ""

fun signingConfigValue(name: String): String =
    providers.gradleProperty(name).orNull
        ?: signingProperties.getProperty(name)
        ?: ""

fun quotedBuildConfigValue(value: String): String =
    "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\""

val releaseStoreFilePath = signingConfigValue("RELEASE_STORE_FILE")
val releaseStorePassword = signingConfigValue("RELEASE_STORE_PASSWORD")
val releaseKeyAlias = signingConfigValue("RELEASE_KEY_ALIAS")
val releaseKeyPassword = signingConfigValue("RELEASE_KEY_PASSWORD")
val releaseSigningConfigured = listOf(
    releaseStoreFilePath,
    releaseStorePassword,
    releaseKeyAlias,
    releaseKeyPassword
).all(String::isNotBlank)

val requestedReleaseArtifact = gradle.startParameter.taskNames.any {
    it.contains("Release", ignoreCase = true)
}

if (requestedReleaseArtifact && !releaseSigningConfigured) {
    throw org.gradle.api.GradleException(
        "Para generar un artefacto release, configurá RELEASE_STORE_FILE, " +
            "RELEASE_STORE_PASSWORD, RELEASE_KEY_ALIAS y RELEASE_KEY_PASSWORD " +
            "en keystore.properties o como propiedades de Gradle."
    )
}

android {
    namespace = "com.example.llave360"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.aldren.llave360"
        minSdk = 24
        targetSdk = 36
        versionCode = 44
        versionName = "4.4.0"

        buildConfigField(
            "String",
            "SUPABASE_URL",
            quotedBuildConfigValue(releaseConfigValue("SUPABASE_URL"))
        )
        buildConfigField(
            "String",
            "SUPABASE_KEY",
            quotedBuildConfigValue(releaseConfigValue("SUPABASE_KEY"))
        )
    }

    signingConfigs {
        create("release") {
            if (releaseSigningConfigured) {
                storeFile = rootProject.file(releaseStoreFilePath)
                storePassword = releaseStorePassword
                keyAlias = releaseKeyAlias
                keyPassword = releaseKeyPassword
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            if (releaseSigningConfigured) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
      compose = true
      aidl = false
      buildConfig = true
      shaders = false
    }

    packaging {
      resources {
        excludes += "/META-INF/{AL2.0,LGPL2.1}"
      }
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  androidTestImplementation(composeBom)

  // Core Android dependencies
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)

  // Arch Components
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.viewmodel.compose)

  // Compose
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.material3)
  implementation("androidx.compose.material:material-icons-core")
  // Imágenes remotas  
  implementation("io.coil-kt.coil3:coil-compose:3.3.0")
  implementation("io.coil-kt.coil3:coil-network-okhttp:3.3.0")
  // Tooling
  debugImplementation(libs.androidx.compose.ui.tooling)
  // Instrumented tests
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  debugImplementation(libs.androidx.compose.ui.test.manifest)

  // Local tests: jUnit, coroutines, Android runner
  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)

  // Instrumented tests: JUnit rules and runners
  androidTestImplementation(libs.androidx.test.core)
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.androidx.test.runner)
  androidTestImplementation(libs.androidx.test.espresso.core)

  // Navigation
  implementation(libs.androidx.navigation3.ui)
  implementation(libs.androidx.navigation3.runtime)
  implementation(libs.androidx.lifecycle.viewmodel.navigation3)
}

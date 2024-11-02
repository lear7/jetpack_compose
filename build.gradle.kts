@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.hilt) apply false
    alias(libs.plugins.android.ksp) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
}

tasks.register("clean", Delete::class){
    delete(layout.buildDirectory.get())
}

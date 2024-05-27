import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

private fun DependencyHandler.debugImplementation(dependency: Any): Dependency? =
    add("debugImplementation", dependency)

private fun DependencyHandler.implementation(dependency: Any): Dependency? =
    add("implementation", dependency)

private fun DependencyHandler.testImplementation(dependency: Any): Dependency? =
    add("testImplementation", dependency)

private fun DependencyHandler.androidTestImplementation(dependency: Any): Dependency? =
    add("androidTestImplementation", dependency)

private fun DependencyHandler.ksp(dependency: Any): Dependency? =
    add("ksp", dependency)

fun DependencyHandler.sharedDependencies(libs: VersionCatalog) {
    debugImplementation(libs.findLibrary("leakcanary-android").get())

    implementation(libs.findLibrary("core-ktx").get())
    implementation(libs.findLibrary("appcompat").get())
    implementation(libs.findLibrary("material").get())
    implementation(libs.findLibrary("constraintlayout").get())
    implementation(libs.findLibrary("activity-ktx").get())
    implementation(libs.findLibrary("fragment-ktx").get())
    implementation(libs.findLibrary("glide").get())

    // Navigation

    implementation(libs.findLibrary("hilt-android").get())
    ksp(libs.findLibrary("hilt-android-compiler").get())

    testImplementation(libs.findLibrary("junit").get())
    testImplementation(libs.findLibrary("mockk").get())
    testImplementation(libs.findLibrary("core-testing").get())
    testImplementation(libs.findLibrary("kotlinx-coroutines-test").get())
    testImplementation(libs.findLibrary("hilt-testing").get())
    androidTestImplementation(libs.findLibrary("ext-junit").get())
    androidTestImplementation(libs.findLibrary("espresso-core").get())
}
plugins {
    `kotlin-dsl`
}

dependencies {
    // Version Catalog Update Plugin - for automated updates
    implementation("nl.littlerobots.vcu:plugin:1.0.1")
    
    // Maven Publish Plugin - simplified publishing
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.35.0")
}

kotlin {
    jvmToolchain(21)
}

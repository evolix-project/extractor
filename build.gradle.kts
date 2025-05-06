plugins {
    kotlin("jvm") version "2.0.21"
}

group = "com.evolix"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    
    // HTTP client
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    
    // HTML parser
    implementation("org.jsoup:jsoup:1.17.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
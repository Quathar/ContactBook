plugins {
    id("java")
    id("io.freefair.lombok") version "8.0.1"
    id("org.hibernate.orm")  version "6.2.3.Final"
//    kotlin("plugin.lombok")  version "1.8.24"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "org.hibernate.orm")

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        // Lombok
//        compileOnly("org.projectlombok:lombok:1.18.28")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        // Google Guice :: Dependency Injection
        // https://mvnrepository.com/artifact/com.google.inject/guice
        implementation("com.google.inject:guice:5.1.0")

        // Test
        testCompileOnly("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok")
    }

}

repositories {
    google()
    mavenCentral()
}

group = "com.quathar"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")
    implementation("com.formdev:flatlaf:3.0")
    implementation("com.toedter:jcalendar:1.4")

    // Test
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}

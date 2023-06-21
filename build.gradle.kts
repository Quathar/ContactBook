plugins {
    id("java")
    id("io.freefair.lombok") version "8.0.1"
    id("org.hibernate.orm")  version "6.2.3.Final"
}

group = "com.quathar"

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

    tasks.test {
        useJUnitPlatform()
    }

}
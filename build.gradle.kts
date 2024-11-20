plugins {
    java
    id("io.freefair.lombok") version "8.0.1"
    id("org.hibernate.orm")  version "6.2.3.Final"
}

group = "com.quathar.contactbook"

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "org.hibernate.orm")

    repositories {
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
        // testCompileOnly("org.projectlombok:lombok")
        // testAnnotationProcessor("org.projectlombok:lombok")

        // JUnit
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    }

    tasks.test {
        useJUnitPlatform()
    }

}

project(":contactbook-data") {
    dependencies {
        // [ H2 Driver ]
        implementation("com.h2database:h2:2.2.220")
        testImplementation("com.h2database:h2:2.2.220")

        // [ GSON ]
        // https://mvnrepository.com/artifact/com.google.code.gson/gson
        implementation("com.google.code.gson:gson:2.10.1")

        // [ Hibernate ORM ]
    }
}

project(":contactbook-application") {
    group = "com.quathar.contactbook"

    dependencies {
        implementation(project(":contactbook-data"))

        // [ UI Themes ]
        implementation("com.formdev:flatlaf:3.0")

        // [ JCalendar component ]
        implementation("com.toedter:jcalendar:1.4")
    }
}

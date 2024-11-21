plugins {
    java
    alias(libs.plugins.lombok) apply false
    alias(libs.plugins.hibernate.orm) apply false
}

group = "com.quathar.contactbook"
version = "1.0.0"

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "org.hibernate.orm")

    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }

    dependencies {
        // Common dependencies
        compileOnly(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)
        implementation(rootProject.libs.guice)

        testImplementation(rootProject.libs.junit.api)
        testRuntimeOnly(rootProject.libs.junit.engine)
    }
}

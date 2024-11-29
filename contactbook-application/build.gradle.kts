plugins {
    application
}

application {
    mainClass.set("com.quathar.contactbook.Application")
}

dependencies {
    implementation(project(":contactbook-data"))
    implementation(rootProject.libs.flatlaf)
    implementation(rootProject.libs.jcalendar)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.quathar.contactbook.Application"
    }

    from({
        configurations.runtimeClasspath
            .get()
            .map { if (it.isDirectory) it else zipTree(it) }
    })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

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

tasks.withType<JavaExec> {
    systemProperty("project.rootDir", rootDir.absolutePath)
}

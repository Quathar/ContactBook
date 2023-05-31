group = "com.quathar"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":data"))

    // JetBrains Themes
    implementation("com.formdev:flatlaf:3.0")

    // JCalendar component
    implementation("com.toedter:jcalendar:1.4")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

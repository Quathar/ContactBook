group = "com.quathar"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":data"))

    // TEst
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")
    implementation("org.hibernate:hibernate-core:5.6.15.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    // JetBrains Themes
    implementation("com.formdev:flatlaf:3.0")

    // JCalendar component
    implementation("com.toedter:jcalendar:1.4")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

group = "com.quathar.contactbook"
version = "0.0.1-SNAPSHOT"

dependencies {
    // SQLite Driver
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")

    // Lombok
//    compileOnly("org.projectlombok:lombok")
//    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")

    // Hibernate
    implementation("org.hibernate:hibernate-core:5.6.15.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
}

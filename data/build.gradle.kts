group = "com.quathar.contactbook"
version = "0.0.1-SNAPSHOT"

dependencies {
    // H2 Driver
    implementation("com.h2database:h2:2.1.214")
    testImplementation("com.h2database:h2:2.1.214")

    // Google Gson :: JSON to Java and vice versa
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Hibernate
//    implementation("org.hibernate:hibernate-core:5.6.15.Final")
//    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
}

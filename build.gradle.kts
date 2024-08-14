plugins {
    java
    idea
    `maven-publish`
    id("com.google.osdetector") version "1.7.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.springframework.boot") version "3.3.2"
    id("com.vaadin") version "24.4.8"
}

val ngrokVersion = "1.1.0"
val vaadinVersion by extra("24.4.8")
val springCloudVersion by extra("2023.0.3")

group = "io.github.tiennm99"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.projectreactor:reactor-test")
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")

    implementation("com.ngrok:ngrok-java:${ngrokVersion}")
    implementation("com.ngrok:ngrok-java-native:${ngrokVersion}:${osdetector.classifier}")
    implementation("com.vaadin:vaadin-spring-boot-starter")
    implementation("org.apache.poi:poi-ooxml:5.3.0")
    implementation("org.springframework.boot:spring-boot-starter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("com.vaadin:vaadin-bom:$vaadinVersion")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/${System.getenv("GITHUB_REPOSITORY")}")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_21.toString()
    targetCompatibility = JavaVersion.VERSION_21.toString()
}

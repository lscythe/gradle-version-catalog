plugins {
    id("com.vanniktech.maven.publish")
    `maven-publish`
}

val version = providers.environmentVariable("VERSION")
    .orElse(providers.gradleProperty("VERSION"))
    .orElse("0.0.0-SNAPSHOT")

mavenPublishing {
    publishToMavenCentral(automaticRelease = true, validateDeployment = true)
    signAllPublications()
    coordinates(
        groupId = providers.gradleProperty("GROUP").get(),
        artifactId = project.name,
        version = version.get()
    )

    pom {
        name.set(project.name)
        description.set(project.description ?: "Lscythe Version Catalog")
        url.set("https://github.com/lscythe/gradle-version-catalog")
        inceptionYear.set("2025")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("lscythe")
                name.set("Lscythe")
                url.set("https://github.com/lscythe")
            }
        }

        scm {
            url.set("https://github.com/lscythe/gradle-version-catalog")
            connection.set("scm:git:git://github.com/lscythe/gradle-version-catalog.git")
            developerConnection.set("scm:git:ssh://git@github.com/lscythe/gradle-version-catalog.git")
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/lscythe/gradle-version-catalogs")
            credentials {
                username = providers.environmentVariable("GITHUB_ACTOR").orNull
                password = providers.environmentVariable("GITHUB_TOKEN").orNull
            }
        }
    }
}


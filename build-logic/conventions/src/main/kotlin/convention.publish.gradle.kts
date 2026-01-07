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
        description.set(project.description ?: providers.gradleProperty("POM_DESCRIPTION").getOrElse("Lscythe Version Catalog"))
        url.set(providers.gradleProperty("POM_URL").getOrElse(""))
        inceptionYear.set(providers.gradleProperty("POM_INCEPTION_YEAR").getOrElse("2025"))

        licenses {
            license {
                name.set(providers.gradleProperty("POM_LICENSE_NAME").getOrElse("MIT License"))
                url.set(providers.gradleProperty("POM_LICENSE_URL").getOrElse("https://opensource.org/licenses/MIT"))
            }
        }

        developers {
            developer {
                id.set(providers.gradleProperty("POM_DEVELOPER_ID").getOrElse("lscythe"))
                name.set(providers.gradleProperty("POM_DEVELOPER_NAME").getOrElse("Lscythe"))
                url.set(providers.gradleProperty("POM_DEVELOPER_URL").getOrElse("https://github.com/lscythe"))
            }
        }

        scm {
            url.set(providers.gradleProperty("POM_SCM_URL").getOrElse(""))
            connection.set(providers.gradleProperty("POM_SCM_CONNECTION").getOrElse(""))
            developerConnection.set(providers.gradleProperty("POM_SCM_DEV_CONNECTION").getOrElse(""))
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri(providers.gradleProperty("GITHUB_PACKAGES_URL").getOrElse(""))
            credentials {
                username = providers.environmentVariable("GITHUB_ACTOR").orNull
                password = providers.environmentVariable("GITHUB_TOKEN").orNull
            }
        }
    }
}

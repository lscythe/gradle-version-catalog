plugins {
    id("com.vanniktech.maven.publish")
}

val version = providers.environmentVariable("VERSION")
    .orElse(providers.gradleProperty("VERSION"))
    .orElse("0.0.0-SNAPSHOT")

mavenPublishing {
    coordinates(
        groupId = providers.gradleProperty("GROUP").get(),
        artifactId = project.name,
        version = version.get()
    )
    
    pom {
        name.set(project.name)
        description.set(project.description ?: "Lscythe Version Catalog")
        url.set("https://github.com/lscythe/version-catalog")
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
            }
        }
        
        scm {
            url.set("https://github.com/lscythe/version-catalog")
            connection.set("scm:git:git://github.com/lscythe/version-catalog.git")
            developerConnection.set("scm:git:ssh://git@github.com/lscythe/version-catalog.git")
        }
    }
}

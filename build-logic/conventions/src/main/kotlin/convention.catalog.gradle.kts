plugins {
    `version-catalog`
    id("convention.publish")
}

catalog {
    versionCatalog {
        from(files("libs.versions.toml"))
    }
}

// Prefer Android JVM variant when multiple Kotlin platform types are available
dependencies.attributesSchema.attribute(
    Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java)
) {
    disambiguationRules.add(PlatformDisambiguationRule::class.java)
}

abstract class PlatformDisambiguationRule : AttributeDisambiguationRule<String> {
    override fun execute(details: MultipleCandidatesDetails<String>) {
        val candidates = details.candidateValues
        when {
            "androidJvm" in candidates -> details.closestMatch("androidJvm")
            "jvm" in candidates -> details.closestMatch("jvm")
        }
    }
}

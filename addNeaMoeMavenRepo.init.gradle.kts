allprojects {
    afterEvaluate {
        extensions.findByType<PublishingExtension>()?.repositories {
            maven("https://repo.nea.moe/mirror") {
                name = "neamoeMirror"
                credentials(PasswordCredentials::class)
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
            maven("https://repo.nea.moe/releases") {
                name = "neamoeReleases"
                credentials(PasswordCredentials::class)
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
    }    
}




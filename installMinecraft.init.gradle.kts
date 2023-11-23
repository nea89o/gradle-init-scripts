/**
 * Install a mod into your minecraft mod folder using a gradle task from any mod you are editing.
 * This requires you to set the global property `minecraft.modfolder` in your global gradle.properties.
 * Afterwards you can run `./gradlew installToMinecraft` (or ./gradlew :subproject:installToMinecraft)
 * to install a mod into your minecraft mod folder while deleting old versions of that mod. This isn't
 * based on mod ids, but instead on archiveBaseName, so you will need to have a jar task that behaves
 * normally.
 * Will only work if it detects architectury loom, fabric loom, essentials loom, forge gradle or unimined.
 * Will prefer a remapJar task, or otherwise use the shadowJar or jar tasks (for forge gradle).
 */


allprojects {
    this.afterEvaluate {
        if (!listOf(
            "gg.essential.loom",
            "cc.polyfrost.loom",
            "dev.architectury.loom",
            "fabric-loom",
            "net.minecraftforge.gradle.tweaker-client",
            "net.minecraftforge.gradle.forge",
            "xyz.wagyourtail.unimined"
        ).any { plugins.hasPlugin(it) }
    ) {
        return@afterEvaluate
    }
    val toInstall = listOf("remapJar", "shadowJar", "jar").mapNotNull {
        tasks.findByName(it)
    }.filterIsInstance<org.gradle.jvm.tasks.Jar>().firstOrNull()
    val targetDirectory = File(project.findProperty("minecraft.modfolder")!!)
    if (toInstall != null) {
        tasks.create("installToMinecraft") {
            dependsOn(toInstall)
            doLast {
                val modJar = toInstall.archiveFile.get().asFile
                targetDirectory.listFiles()
                .filter {
                    it.name.startsWith(toInstall.archiveBaseName.get())
                }
                .forEach {
                    it.delete()
                }
                modJar.copyTo(targetDirectory.resolve(modJar.name))
                println("Installed $modJar to $targetDirectory")
            }
        }
    }
}
}

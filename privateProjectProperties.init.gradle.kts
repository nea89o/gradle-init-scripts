import java.util.Properties

allprojects {
    val file = project.file(".gradle/private.properties")
    if (file.exists()) {
        val properties = Properties()
        properties.load(file.inputStream())
        properties.forEach { (k, v) ->
			(this.properties as MutableMap<String, String>).put(k as String, v as String)
        }
    }
}




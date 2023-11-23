# Gradle Init Scripts

Collection of useful gradle configuration snippets / init scripts.

To use one of these init scripts, place it into `$GRADLE_HOME/init.d` (typically `~/.gradle/init.d/`). This folder will not exist by default.

Note that these might break on older versions of gradle, so if you use old gradle versions and get compilation errors, rename the offending scripts to end with `.disabled` or something similar while you work on those projects. That being said, most of them should work on gradle 6+.

If you want to specify a gradle property globally you can do so in `$GRADLE_HOME/gradle.properties` (typically `~/.gradle/gradle.properties`).


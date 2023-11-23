/**
 * Configures gradles signing plugin to use the gpg command instead of gradle properties for keys.
 * Requires you to set global gradle properties like so:
 * ```
 * signing.gnupg.executable=gpg
 * signing.gnupg.useLegacyGpg=true
 * signing.gnupg.keyName=AA563E93EB628D91
 * ```
 *
 * You can get the keyname by running `gpg --list-secret-keys --keyid-format long`. The id you want here is a signing key (it will have a `S` inside of the `[S]` brackets.). Usually you will want to sign with a subkey (indicated by `ssb` at the beginning of the line).
 */


allprojects {
    afterEvaluate {
        extensions.findByType<SigningExtension>()?.run {
            useGpgCmd()
        }
    }
}

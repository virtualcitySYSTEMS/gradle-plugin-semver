# gradle-plugin-semver

The `gradle-plugin-semver` is simple and lightweight gradle plugin for facilitating the management of semantic
versioning in Java projects.

###  How to use it

In your Java project, you need to first create a json file `version.json` in the project root directory. The json file
must have a `version` attribute for containing the project version, for example:

``` json
{"version": "1.0.0"}
```

Next, define the plugin and the version to use in the `pluginManagement` block, and place this block at the
beginning of your `settings.gradle` file:

``` groovy
pluginManagement {
    plugins {
        id 'com.github.virtualcitysystems.semver' version '1.1.0'
    }
}
```

Afterward, you need to apply the semver plugin by adapting your `build.gradle` file like the following:

``` groovy
plugins {
    id 'com.github.virtualcitysystems.semver'
}
```

Once everything is set up, you can add the following code in your `build.gradle` file to manage your project version
based on the `version.json`.

``` groovy
version bumpVersionPatch.getCurrentVersion()
```

After reloading the gradle project, a new gradle task `bumpVersionPatch` is available. After executing that task, the
patch version number will be incremented by 1. In this example, the version number will be bumped to `1.0.1` and saved
into the json file `version.json`. Essentially, this gradle task works very similar to the npm command
`npm version patch`. You may use this plugin to automatically patch version number in your CI/CD workflow.

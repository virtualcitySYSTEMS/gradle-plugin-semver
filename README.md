# gradle-plugin-semver
The `gradle-plugin-semver` is simple and lightweight gradle plugin for facilitating the management of semantic versioning in Java projects.

###  How to use it
In your java project, you need to first create a json file `version.json` in the project root directory. The json file should have a `version`
attribute for containing the project version, for example:

``` json
{"version": "1.0.0"}
```

You can add the following code in your `build.gradle` file to set your project version based on the `version.json`.

``` groovy
version new JsonSlurper().parseText(new File("$rootDir/version.json").text)["version"]
```

In the next step, you need to apply the semver plugin by adapting your `build.gradle` file like the following:

``` groovy
plugins {
  id 'com.github.virtualcitysystems.semver' version '1.0.0'
}
```

After reloading the gradle project, a new gradle task `bumpVersionPatch` is available. Once it has been executed, the patch number of the version will be incremented by 1. In this example, the version
will be bumped to `1.0.1` and written back into the json file automatically. Essentially, the gradle task works very similar to the npm command `npm version patch`. You may use this plugin to implement
the automatic version bumping for your CI/CD workflow. 

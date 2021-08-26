# gradle-plugin-semver
The `gradle-plugin-semver` is simple and lightweight gradle plugin for facilitating the management of semantic versioning in Java projects.

###  Hot to use it
In your java project, you need to first create a json file `version.json` in the project root directory. The json file should have a `version`
attribute for containing the project version, for example:

``` json
{"version": "1.0.0"}
```

Once the gradle task `bumpVersionPatch` has been executed, the patch version number will be incremented by 1. In this example, the version
will be bumped to `1.0.1` and written back into the json file. Essentially, the gradle task works very similar to the npm command `npm version patch`.
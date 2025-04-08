package com.github.virtualcitysystems.semver;

public class VersionPatchTask extends VersionTask {
    @Override
    protected String getNewVersion(Version version) {
        return version.incrementPatch().toVersionString(false);
    }
}

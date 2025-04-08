package com.github.virtualcitysystems.semver;

public class VersionPrereleaseTask extends VersionTask {
    @Override
    protected String getNewVersion(Version version) {
        return version.incrementPreRelease().toVersionString(true);
    }
}

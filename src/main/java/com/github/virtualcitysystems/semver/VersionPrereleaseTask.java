package com.github.virtualcitysystems.semver;

public class VersionPrereleaseTask extends VersionTask {
    @Override
    protected String getNewVersion(Version version) {
        version = version.isReleaseCandidate() ?
                version.incrementPreRelease() :
                version.incrementPatch();

        return version.toVersionString(true);
    }
}

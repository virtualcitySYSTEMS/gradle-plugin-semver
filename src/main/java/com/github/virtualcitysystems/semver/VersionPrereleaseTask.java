package com.github.virtualcitysystems.semver;

public class VersionPrereleaseTask extends VersionTask {
    @Override
    protected String getNewVersion(Version version) {
        version = version.isReleaseCandidate() ?
                version.incrementPreRelease() :
                version.incrementMinor().resetPatch();

        return version.toVersionString(true);
    }
}

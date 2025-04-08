package com.github.virtualcitysystems.semver;

public class VersionPatchTask extends VersionTask {
    @Override
    protected String getNewVersion(Version version) {
        if (!version.isReleaseCandidate()) {
            version.incrementPatch();
        }

        return version.toVersionString(false);
    }
}

package com.github.virtualcitysystems.semver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Version {
    private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)(?:-rc\\.(\\d+))?");

    private int major;
    private int minor;
    private int patch;
    private int preRelease;

    private Version(int major, int minor, int patch, int preRelease) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.preRelease = preRelease;
    }

    public static Version of(String version) throws IllegalArgumentException {
        Matcher matcher = VERSION_PATTERN.matcher(version);
        if (matcher.matches()) {
            int major = Integer.parseInt(matcher.group(1));
            int minor = Integer.parseInt(matcher.group(2));
            int patch = Integer.parseInt(matcher.group(3));
            int preRelease = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 0;
            return new Version(major, minor, patch, preRelease);
        } else {
            throw new IllegalArgumentException("The version does not follow the semver pattern " +
                    "'major.minor.path[-rc.prerelease]' but was '" + version + "'.");
        }
    }

    public int getMajor() {
        return major;
    }

    public Version incrementMajor() {
        major++;
        return this;
    }

    public int getMinor() {
        return minor;
    }

    public Version incrementMinor() {
        minor++;
        return this;
    }

    public int getPatch() {
        return patch;
    }

    public Version incrementPatch() {
        patch++;
        return this;
    }

    public int getPreRelease() {
        return preRelease;
    }

    public Version incrementPreRelease() {
        preRelease++;
        return this;
    }

    public String toVersionString(boolean includePreRelease) {
        String version = major + "." + minor + "." + patch;
        return includePreRelease ? version + "-rc." + preRelease : version;
    }
}

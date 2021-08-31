package com.github.virtualcitysystems.semver;

import java.util.Arrays;

public class VersionPatchTask extends VersionDefaultTask {
    @Override
    protected String getVersion(String[] split) throws Exception {
        String[] copy = Arrays.copyOf(split, 3);

        if (split.length == 4) {
            try {
                copy[2] = copy[2].substring(0, copy[2].indexOf("-rc"));
            } catch (IndexOutOfBoundsException e) {
                throw new Exception("The prerelease version number doesn't follow the pattern '-rc'.");
            }
        } else {
            int patchNumber;
            try {
                patchNumber = Integer.parseInt(copy[2]);
            } catch (NumberFormatException e) {
                throw new Exception("The patch number in the version.json file is not a valid integer, but '" + copy[2] + "'.");
            }
            copy[2] = String.valueOf(++patchNumber);
        }

        return String.join(".", copy);
    }
}

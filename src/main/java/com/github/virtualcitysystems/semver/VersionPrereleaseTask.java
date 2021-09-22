package com.github.virtualcitysystems.semver;

import java.util.Arrays;

public class VersionPrereleaseTask extends VersionDefaultTask {
    @Override
    protected String getNewVersion(String[] split) throws Exception {
        String[] copy = Arrays.copyOf(split, split.length);

        String lastDigit = copy[copy.length - 1];
        try {
            int lastDigitNum = Integer.parseInt(lastDigit);
            copy[copy.length - 1] = String.valueOf(++lastDigitNum);
        } catch (NumberFormatException e) {
            throw new Exception("The last version digit in the version.json file is not a valid integer, but '" + lastDigit + "'.");
        }

        String newVersion = String.join(".", copy);
        if (copy.length == 3) {
            newVersion += "-rc.0";
        }

        return newVersion;
    }
}

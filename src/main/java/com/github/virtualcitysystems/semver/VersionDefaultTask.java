package com.github.virtualcitysystems.semver;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public abstract class VersionDefaultTask extends DefaultTask {

    public VersionDefaultTask() {
        setGroup("semver");
    }

    @SuppressWarnings("unchecked")
    @TaskAction
    public void doAction() throws Exception {
        String versionFilePath = getProject().getProjectDir().toPath().resolve("version.json").toAbsolutePath().toString();
        JSONParser parser = new JSONParser();

        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(versionFilePath));
        } catch (FileNotFoundException e) {
            throw new Exception("The version.json file is not found in the project root directory.");
        } catch (Throwable e) {
            throw new Exception("Failed to parse the version.json file. Please check if it has a valid JSON structure.");
        }

        String version = (String) jsonObject.get("version");
        if (version == null) {
            throw new Exception("The version attribute is missing in the version.json file.");
        }

        String[] split = version.split(Pattern.quote("."));
        if (split.length != 3 && split.length != 4) {
            throw new Exception("The version '" + version + "' in the version.json file does not follow the semver layout 'major.minor.patch[-rc.prerelease]'.");
        }

        String newVersion = getVersion(split);
        jsonObject.replace("version", newVersion);

        FileWriter file = null;
        try {
            file = new FileWriter(versionFilePath);
            file.write(jsonObject.toJSONString());
        } catch (IOException e) {
            throw new Exception("Failed to save the new bumped version into the JSON file '" + versionFilePath + "'." , e);
        } finally {
            if (file != null) {
                try {
                    file.flush();
                    file.close();
                } catch (IOException e) {
                    //
                }
            }
        }

        System.out.println("The version number in the version.json file has been bumped to '" + newVersion + "' successfully.");
    }

    protected abstract String getVersion(String[] split) throws Exception;
}

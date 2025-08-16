package com.github.virtualcitysystems.semver;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;

public abstract class VersionTask extends DefaultTask {
    File projectDir;

    public VersionTask() {
        setGroup("semver");
    }

    protected abstract String getNewVersion(Version version) throws Exception;

    @InputFile
    public File getProjectDir() {
        return projectDir;
    }

    @TaskAction
    public void doAction() throws Exception {
        String currentVersion = getCurrentVersion();
        String newVersion = getNewVersion(Version.of(currentVersion));

        ObjectMapper mapper = new ObjectMapper();
        try (JsonGenerator writer = mapper.createGenerator(getVersionFile(), JsonEncoding.UTF8)) {
            writer.writeStartObject();
            writer.writeStringField("version", newVersion);
            writer.writeEndObject();
        } catch (IOException e) {
            throw new Exception("Failed to write version file.", e);
        }

        getLogger().lifecycle("Bumping version from '{}' to '{}'.", currentVersion, newVersion);
    }

    @Internal
    public String getCurrentVersion() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (JsonParser parser = mapper.createParser(getVersionFile())) {
            JsonNode node = mapper.readTree(parser);
            if (node != null && node.has("version")) {
                return node.get("version").asText();
            } else {
                throw new Exception("No \"version\" property found in version.json file. ");
            }
        } catch (IOException e) {
            throw new Exception("Failed to parse version.json file.", e);
        }
    }

    private File getVersionFile() {
        return projectDir.toPath().resolve("version.json").toFile();
    }
}

package com.github.virtualcitysystems.semver;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class SemverPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().register("bumpVersionPatch", VersionPatchTask.class);
    }
}

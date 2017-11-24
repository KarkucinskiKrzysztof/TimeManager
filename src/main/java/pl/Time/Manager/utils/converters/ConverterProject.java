package pl.Time.Manager.utils.converters;

import pl.Time.Manager.database.models.Project;
import pl.Time.Manager.modelFx.ProjectFx;

public class ConverterProject {

    public static ProjectFx projectToProjectFx(Project project){
        ProjectFx projectFx = new ProjectFx();
        projectFx.setCategory(project.getCategory());
        projectFx.setColor(project.getColor());
        projectFx.setName(project.getName());
        projectFx.setId(project.getId());
        projectFx.setDescription(project.getDescription());
        return projectFx;
    }
}

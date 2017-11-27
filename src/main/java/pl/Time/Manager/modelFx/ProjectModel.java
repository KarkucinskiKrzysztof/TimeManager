package pl.Time.Manager.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.Time.Manager.database.dao.ProjectDao;
import pl.Time.Manager.database.dbuitls.DbManager;
import pl.Time.Manager.database.models.Project;
import pl.Time.Manager.utils.converters.ConverterProject;
import pl.Time.Manager.utils.exceptions.ApplicationException;

import java.util.List;

public class ProjectModel {

    private ObservableList<ProjectFx> projectList = FXCollections.observableArrayList();
    private ObjectProperty<ProjectFx> project = new SimpleObjectProperty<>(new ProjectFx());
    private ObjectProperty<ProjectFx> projectEdit = new SimpleObjectProperty<>(new ProjectFx());


    public void init() throws ApplicationException {
        ProjectDao projectDao = new ProjectDao();
        List<Project> projects = projectDao.queryForAll(Project.class);
        initProjectList(projects);
    }



    private void initProjectList(List<Project> projects) {
        this.projectList.clear();
        projects.forEach(c -> {
            ProjectFx projectFx = ConverterProject.projectToProjectFx(c);
            this.projectList.add(projectFx);
        });

    }

    public void saveProjectEditInDataBase() throws ApplicationException {
        saveOrUpdate(this.getProjectEdit());
    }

    public void saveProjectInDataBase() throws ApplicationException {
        saveOrUpdate(this.getProject());
    }

    private void saveOrUpdate(ProjectFx projectFx) throws ApplicationException {
        ProjectDao projectDao = new ProjectDao();
        Project project = ConverterProject.projectFxToProject(projectFx);
        projectDao.creatOrUpdate(project);
        init();
    }


    public void updateProjectInDataBase() throws ApplicationException {
        ProjectDao projectDao = new ProjectDao();
        Project tempProject = projectDao.findById(Project.class, getProject().getId());
        tempProject.setName(getProject().getName());
        projectDao.creatOrUpdate(tempProject);
        init();
    }
    public void deleteProjectById() throws ApplicationException {
        ProjectDao projectDao = new ProjectDao();
        projectDao.deleteById(Project.class, project.getValue().getId());
        init();
    }

    public ObservableList<ProjectFx> getProjectList() {
        return projectList;
    }

    public void setProjectList(ObservableList<ProjectFx> projectList) {
        this.projectList = projectList;
    }

    public ProjectFx getProject() {
        return project.get();
    }

    public ObjectProperty<ProjectFx> projectProperty() {
        return project;
    }

    public void setProject(ProjectFx project) {
        this.project.set(project);
    }


    public ProjectFx getProjectEdit() {
        return projectEdit.get();
    }

    public ObjectProperty<ProjectFx> projectEditProperty() {
        return projectEdit;
    }

    public void setProjectEdit(ProjectFx projectEdit) {
        this.projectEdit.set(projectEdit);
    }
}

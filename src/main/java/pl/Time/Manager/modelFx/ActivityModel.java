package pl.Time.Manager.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.Time.Manager.database.dao.ActivityDao;
import pl.Time.Manager.database.dao.CategoryDao;
import pl.Time.Manager.database.dao.ProjectDao;
import pl.Time.Manager.database.models.Activity;
import pl.Time.Manager.database.models.Category;
import pl.Time.Manager.database.models.Project;
import pl.Time.Manager.utils.converters.ConverterActivity;
import pl.Time.Manager.utils.converters.ConverterCategory;
import pl.Time.Manager.utils.converters.ConverterProject;
import pl.Time.Manager.utils.exceptions.ApplicationException;

import java.util.List;
import java.util.function.Predicate;

public class ActivityModel {

    private ObjectProperty<ActivityFx> activityFx = new SimpleObjectProperty<>(new ActivityFx());
    private ObjectProperty<ActivityFx> activityFxEdit = new SimpleObjectProperty<>(new ActivityFx());
    private ObservableList<CategoryFx> categoryFxList = FXCollections.observableArrayList();
    private ObservableList<ProjectFx> projectFxList = FXCollections.observableArrayList();
    private ObservableList<ActivityFx> activityFxList = FXCollections.observableArrayList();


    private ObjectProperty<CategoryFx> categoryFxFilter = new SimpleObjectProperty<>();
    private ObjectProperty<ProjectFx> projectFxFilter = new SimpleObjectProperty<>();



    // sprawdzamy cza kategoria wybrana w comboboxie ma takie samo id jak kategoria aktywności
    public Predicate<ActivityFx> predicateCategory(){
        Predicate<ActivityFx> predicate = activityFx-> activityFx.getCategoryFx().getId() == getCategoryFxFilter().getId();
        return predicate;
    }
    public Predicate<ActivityFx> predicateProject(){
        Predicate<ActivityFx> predicate = activityFx-> activityFx.getProjectFx().getId() == getProjectFxFilter().getId();
        return predicate;
    }



    public void init() throws ApplicationException {
        initCategoryFxList();
        initProjectFxList();
        initActivityFxList();

    }

    private void initProjectFxList() throws ApplicationException {
        ProjectDao projectDao = new ProjectDao();
        List<Project> projectList = projectDao.queryForAll(Project.class);
        projectFxList.clear();
        projectList.forEach(c->{
            ProjectFx projectFx = ConverterProject.projectToProjectFx(c);
            projectFxList.add(projectFx);
        });
    }

    private void initCategoryFxList() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.queryForAll(Category.class);
        categoryFxList.clear();
        categoryList.forEach(c->{
            CategoryFx categoryFx = ConverterCategory.categoryToCategoryFx(c);
            categoryFxList.add(categoryFx);
        });
    }
    public void initActivityFxList() throws ApplicationException {
        ActivityDao activityDao = new ActivityDao();
        List<Activity> categoryList = activityDao.queryForAll(Activity.class);
        activityFxList.clear();
        categoryList.forEach(c->{
            ActivityFx activityFx = ConverterActivity.activityToActivityFx(c);
            this.activityFxList.add(activityFx);
        });
    }


    public void saveActivityInDataBase() throws ApplicationException {
        Activity activity = ConverterActivity.activityFxToActivity(this.getActivityFx());

        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findById(Category.class, this.getActivityFx().getCategoryFx().getId());

        ProjectDao projctDao = new ProjectDao();
        Project project = projctDao.findById(Project.class, this.getActivityFx().getProjectFx().getId());

        activity.setCategory(category);
        activity.setProject(project);

        ActivityDao activityDao = new ActivityDao();
        activityDao.creatOrUpdate(activity);
        init();
    }

    public ActivityFx getActivityFxEdit() {
        return activityFxEdit.get();
    }

    public ObjectProperty<ActivityFx> activityFxEditProperty() {
        return activityFxEdit;
    }

    public void setActivityFxEdit(ActivityFx activityFxEdit) {
        this.activityFxEdit.set(activityFxEdit);
    }

    public ActivityFx getActivityFx() {
        return activityFx.get();
    }

    public ObjectProperty<ActivityFx> activityFxProperty() {
        return activityFx;
    }

    public void setActivityFx(ActivityFx activityFx) {
        this.activityFx.set(activityFx);
    }

    public ObservableList<ActivityFx> getActivityFxList() {
        return activityFxList;
    }

    public void setActivityFxList(ObservableList<ActivityFx> activityFxList) {
        this.activityFxList = activityFxList;
    }

    public ObservableList<CategoryFx> getCategoryFxList() {
        return categoryFxList;
    }

    public void setCategoryFxList(ObservableList<CategoryFx> categoryFxList) {
        this.categoryFxList = categoryFxList;
    }

    public ObservableList<ProjectFx> getProjectFxList() {
        return projectFxList;
    }

    public void setProjectFxList(ObservableList<ProjectFx> projectFxList) {
        this.projectFxList = projectFxList;
    }
    public CategoryFx getCategoryFxFilter() {
        return categoryFxFilter.get();
    }

    public ObjectProperty<CategoryFx> categoryFxFilterProperty() {
        return categoryFxFilter;
    }

    public void setCategoryFxFilter(CategoryFx categoryFxFilter) {
        this.categoryFxFilter.set(categoryFxFilter);
    }

    public ProjectFx getProjectFxFilter() {
        return projectFxFilter.get();
    }

    public ObjectProperty<ProjectFx> projectFxFilterProperty() {
        return projectFxFilter;
    }

    public void setProjectFxFilter(ProjectFx projectFxFilter) {
        this.projectFxFilter.set(projectFxFilter);
    }

}

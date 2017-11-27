package pl.Time.Manager.modelFx;

import javafx.beans.property.*;
import java.time.LocalDate;



public class ActivityFx {
    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<ProjectFx> projectFx = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFx = new SimpleObjectProperty<>();
    private SimpleStringProperty discription = new SimpleStringProperty();
    private IntegerProperty duration = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> addDate = new SimpleObjectProperty<>();
    private SimpleStringProperty time = new SimpleStringProperty();

    public ProjectFx getProjectFx() {
        return projectFx.get();
    }

    public ObjectProperty<ProjectFx> projectFxProperty() {
        return projectFx;
    }

    public void setProjectFx(ProjectFx projectFx) {
        this.projectFx.set(projectFx);
    }

    public CategoryFx getCategoryFx() {
        return categoryFx.get();
    }

    public ObjectProperty<CategoryFx> categoryFxProperty() {
        return categoryFx;
    }

    public void setCategoryFx(CategoryFx categoryFx) {
        this.categoryFx.set(categoryFx);
    }

    public String getDiscription() {
        return discription.get();
    }

    public SimpleStringProperty discriptionProperty() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription.set(discription);
    }

    public int getDuration() {
        return duration.get();
    }

    public IntegerProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public LocalDate getAddDate() {
        return addDate.get();
    }

    public ObjectProperty<LocalDate> addDateProperty() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate.set(addDate);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return "ActivityFx{" +
                "id=" + id +
                ", projectFx=" + projectFx +
                ", categoryFx=" + categoryFx +
                ", discription=" + discription +
                ", duration=" + duration +
                ", addDate=" + addDate +
                ", time=" + time +
                '}';
    }
}

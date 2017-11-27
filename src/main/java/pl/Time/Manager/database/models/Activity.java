package pl.Time.Manager.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "ACTIVITY")
public class Activity implements BaseModel{

    public static final String PROJECT_ID = "PROJECT_ID";
    public static final String CATEGORY_ID = "CATEGORY_ID";

    public Activity() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = PROJECT_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Project project;
    @DatabaseField(columnName = CATEGORY_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Category category;

    @DatabaseField(columnName = "DESCRIPTION")
    private String description;

    @DatabaseField(columnName = "DURATION")
    private int duration;

    @DatabaseField(columnName = "ADDED_DATE")
    private Date addedDate;

    public static String getProjectId() {
        return PROJECT_ID;
    }

    public static String getCategoryId() {
        return CATEGORY_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}


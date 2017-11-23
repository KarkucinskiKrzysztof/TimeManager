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
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

//    @DatabaseField(columnName = "NAME", canBeNull = false)
//    private String name;

//    @DatabaseField(columnName = "ADD_DATE")
//    private Date addDate;

//    @DatabaseField(columnName = "ISBN")
//    private String isbn;

//    @DatabaseField(columnName = "RATING", width = 1)
//    private int rating;
//    public String getDescription() {
//        return name;
//    }
//
//    public void setDescription(String name) {
//        this.name = name;
//    }

//    public Date getAddDate() {
//        return addDate;
//    }
//
//    public void setAddDate(Date addDate) {
//        this.addDate = addDate;
//    }

//    public String getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(String isbn) {
//        this.isbn = isbn;
//    }

//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }



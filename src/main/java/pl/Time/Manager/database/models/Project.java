package pl.Time.Manager.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "PROJECTS")
public class Project implements BaseModel{
    public Project() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "DESCRIPTION", canBeNull = false)
    private String description;


    @DatabaseField(columnName = "COLOR", canBeNull = false)
    private String color;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Activity> activities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public ForeignCollection<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ForeignCollection<Activity> activities) {
        this.activities = activities;
    }
}
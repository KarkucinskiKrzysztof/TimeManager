package pl.Time.Manager.utils.converters;

import pl.Time.Manager.controllers.ProjectController;
import pl.Time.Manager.database.models.Activity;
import pl.Time.Manager.database.models.Category;
import pl.Time.Manager.database.models.Project;
import pl.Time.Manager.modelFx.ActivityFx;
import pl.Time.Manager.utils.Utils;

import static pl.Time.Manager.utils.Utils.convertToDate;

public class ConverterActivity {

    public static Activity activityFxToActivity(ActivityFx activityFx){
        Activity activity = new Activity();
        activity.setId(activityFx.getId());
        activity.setAddedDate(convertToDate(activityFx.getAddDate()));
        activity.setDuration(activityFx.getDuration());
        activity.setDescription(activityFx.getDiscription());


        return activity;
    }

    public static ActivityFx activityToActivityFx(Activity activity) {
        ActivityFx activityFx = new ActivityFx();
        activityFx.setId(activity.getId());
        activityFx.setDuration(activity.getDuration());
        activityFx.setDiscription(activity.getDescription());
        activityFx.setAddDate(Utils.convertToLocalDate(activity.getAddedDate()));
        activityFx.setCategoryFx(ConverterCategory.categoryToCategoryFx(activity.getCategory()));
        activityFx.setProjectFx(ConverterProject.projectToProjectFx(activity.getProject()));
        return activityFx;
    }
}

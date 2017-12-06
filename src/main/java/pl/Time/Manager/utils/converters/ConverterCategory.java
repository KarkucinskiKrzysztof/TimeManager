package pl.Time.Manager.utils.converters;

import pl.Time.Manager.database.models.Category;
import pl.Time.Manager.modelFx.CategoryFx;

public class ConverterCategory {

    public static CategoryFx categoryToCategoryFx (Category category){
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());
        categoryFx.setName(category.getName());
        categoryFx.setDescription(category.getDescription());
        categoryFx.setColor(category.getColor());
        return categoryFx;
    }
 }

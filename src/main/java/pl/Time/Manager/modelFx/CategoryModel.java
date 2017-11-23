package pl.Time.Manager.modelFx;

import com.j256.ormlite.dao.DaoManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.Time.Manager.database.dao.CategoryDao;
import pl.Time.Manager.database.dbuitls.DbManager;
import pl.Time.Manager.database.models.Category;
import pl.Time.Manager.utils.exceptions.ApplicationException;

import java.util.List;

public class CategoryModel {

    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>();


    public void init() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        List<Category> categories = categoryDao.queryForAll(Category.class);
        this.categoryList.clear();
        categories.forEach(c -> {
            CategoryFx categoryFx = new CategoryFx();
            categoryFx.setId(c.getId());
            categoryFx.setName(c.getName());
            this.categoryList.add(categoryFx);
        });
        DbManager.closeConnectionSource();
    }

    public void deleteCategoryById() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        categoryDao.deleteById(Category.class, category.getValue().getId());
        DbManager.closeConnectionSource();
        init();
    }

    public void saveCategoryInDataBase(String name,String desc, String color) throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category category = new Category();
        category.setName(name);
        category.setDescription(desc);
        category.setColor(color);
        categoryDao.creatOrUpdate(category);
        DbManager.closeConnectionSource();
        init();
    }
    public void updateCategoryInDataBase() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category tempCategory = categoryDao.findById(Category.class, getCategory().getId());
        tempCategory.setName(getCategory().getName());
        categoryDao.creatOrUpdate(tempCategory);
        DbManager.closeConnectionSource();
        init();
    }

    public ObservableList<CategoryFx> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ObservableList<CategoryFx> categoryList) {
        this.categoryList = categoryList;
    }

    public  CategoryFx getCategory() {
        return category.get();
    }

    public ObjectProperty<CategoryFx> categoryProperty() {
        return category;
    }

    public void setCategory(CategoryFx category) {
        this.category.set(category);
    }

}
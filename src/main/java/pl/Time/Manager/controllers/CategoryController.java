package pl.Time.Manager.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.Time.Manager.modelFx.CategoryFx;
import pl.Time.Manager.modelFx.CategoryModel;



public class CategoryController {

    @FXML
    private Button addCategoryButton;
    @FXML
    private Button deleteCategoryButton;
    @FXML
    private TextField categoryTextField;
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    private CategoryModel categoryModel;

    @FXML
    public void initialize() {
        this.categoryModel = new CategoryModel();
        this.categoryModel.init();
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        initBindings();
    }

    private void initBindings() {
        this.addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
        this.deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
    }


    public void addCategoryOnAction() {
        categoryModel.saveCategoryInDataBase(categoryTextField.getText());
        categoryTextField.clear();
    }

    public void onActionDeleteButton() {
        this.categoryModel.deleteCategoryById();
    }

    public void onActionComboBox() {
        System.out.println(this.categoryComboBox.getSelectionModel().getSelectedItem());
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }
}







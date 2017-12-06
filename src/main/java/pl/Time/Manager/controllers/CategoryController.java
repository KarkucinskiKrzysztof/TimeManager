package pl.Time.Manager.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import pl.Time.Manager.modelFx.CategoryFx;
import pl.Time.Manager.modelFx.CategoryModel;
import pl.Time.Manager.utils.DialogsUtils;
import pl.Time.Manager.utils.exceptions.ApplicationException;


public class CategoryController {

    @FXML
    private Button addCategoryButton;
    @FXML
    private Button deleteCategoryButton;
    @FXML
    private Button editCategoryButton;
    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private ColorPicker categoryColorPicker;
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private TableColumn<CategoryFx, String> nameColumn;
    @FXML
    private TableColumn<CategoryFx, String> colorColumn;
    @FXML
    private TableColumn<CategoryFx, String> descriptionColumn;
    @FXML
    private TableColumn<CategoryFx, Number> totalTimeColumn;
    @FXML
    private TableView<CategoryFx> categoryTableView;

    private CategoryModel categoryModel;

    @FXML
    public void initialize() {
        this.categoryModel = new CategoryModel();
        try {
            this.categoryModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        initBindings();
        initTableBindings();

    }

    private void initTableBindings() {
        this.categoryTableView.setItems(this.categoryModel.getCategoryList());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.colorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
        this.descriptionColumn.setCellValueFactory((cellData -> cellData.getValue().descriptionProperty())); // gdzies jest discriptionProperty
        // this.totalTimeColumn.setCellValueFactory(cellData->cellData.getValue().xxx);
    }

    private void initBindings() {
        this.addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty().or(descriptionTextField.textProperty().isEmpty()));
        this.deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());  // aktywacja przycisku delete dopiero po wybraniu comboBoxa
        this.editCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());    // aktywacja przycisku edit dopiero po wybraniu comboBoxa
    }

    public void addCategoryOnAction() {
        try {
            categoryModel.saveCategoryInDataBase(categoryTextField.getText(), descriptionTextField.getText(), categoryColorPicker.getValue().toString());
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        categoryTextField.clear();
        descriptionTextField.clear();
    }

    public void onActionDeleteButton() {
        try {
            this.categoryModel.deleteCategoryById();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public void onActionComboBox() {
        System.out.println(this.categoryComboBox.getSelectionModel().getSelectedItem());
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }

    public void categoryColorPickerButton() {
        Color color = categoryColorPicker.getValue();
        System.out.println(color);
    }

    public void onActionEditCategoryButton() {
        String newCategoryName = DialogsUtils.editDialog(this.categoryModel.getCategory().getName());   // efekt pracy okna jest zapisywany w Stringu jeśli zechcemy modyfikować ine elementy category niż tylko name to trzeba będzie to zmienić na klase Category
        if (newCategoryName != null) {
            this.categoryModel.getCategory().setName(newCategoryName);
            try {
                this.categoryModel.updateCategoryInDataBase();
            } catch (ApplicationException e) {
                DialogsUtils.errorDialog(e.getMessage());
            }
        }
    }
}







package view;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import service.UserService;

import java.io.IOException;

public class ShowUsersController {
    UserService userService = new UserService();

    @FXML private TableView<User> usersTable = new TableView<User>();

    @FXML private TableColumn<User, String> emailCol;

    @FXML private TableColumn<User, String> firstNameCol;

    @FXML private TableColumn<User, String> lastNameCol;

    @FXML private TableColumn<User, Integer> idCol;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Main main = new Main();
        main.changeScene("home.fxml");
    }

    @FXML
    private void initialize(){
        usersTable.setItems(userService.getUsers());
        idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
    }
}

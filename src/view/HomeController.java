package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {
    private final Main main = new Main();

    @FXML void AddLog(ActionEvent event) {

    }

    @FXML void DeleteLog(ActionEvent event) {

    }

    @FXML void addEmployee(ActionEvent event) {

    }

    @FXML void addFeedback(ActionEvent event) {

    }

    @FXML void addUser(ActionEvent event) {

    }

    @FXML void deleteEmployee(ActionEvent event) {

    }

    @FXML void deleteFeedback(ActionEvent event) {

    }

    @FXML void showAllEmployees(ActionEvent event) throws IOException {
        main.changeScene("showEmployees.fxml");
    }

    @FXML void showAllFeedbacks(ActionEvent event) throws IOException {
        main.changeScene("showFeedbacks.fxml");
    }

    @FXML void showAllLogs(ActionEvent event) throws IOException {
        main.changeScene("showLogs.fxml");
    }

    @FXML void showAllUsers(ActionEvent event) throws IOException {
        main.changeScene("showUsers.fxml");
    }

    @FXML void showTrendline(ActionEvent event) {

    }
}

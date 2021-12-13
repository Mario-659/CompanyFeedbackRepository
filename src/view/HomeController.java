package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {
    private final Main main = new Main();

    @FXML void AddLog(ActionEvent event) throws IOException {
        main.changeScene("../resources/fxml/addLog.fxml");
    }

    @FXML void DeleteLog(ActionEvent event) {

    }

    @FXML void addEmployee(ActionEvent event) throws IOException {
        main.changeScene("../resources/fxml/addEmployee.fxml");
    }

    @FXML void addFeedback(ActionEvent event) throws IOException {
        main.changeScene("../resources/fxml/addFeedback.fxml");
    }

    @FXML void addUser(ActionEvent event) throws IOException {
        main.changeScene("../resources/fxml/addUser.fxml");
    }

    @FXML void deleteEmployee(ActionEvent event) {

    }

    @FXML void deleteFeedback(ActionEvent event) {

    }

    @FXML void showAllEmployees(ActionEvent event) throws IOException {
        main.changeScene("../resources/fxml/showEmployees.fxml");
    }

    @FXML void showAllFeedbacks(ActionEvent event) throws IOException {
        main.changeScene("../resources/fxml/showFeedbacks.fxml");
    }

    @FXML void showAllLogs(ActionEvent event) throws IOException {
        main.changeScene("../resources/fxml/showLogs.fxml");
    }

    @FXML void showAllUsers(ActionEvent event) throws IOException {
        main.changeScene("../resources/fxml/showUsers.fxml");
    }

    @FXML void showTrendline(ActionEvent event) throws IOException {
        main.changeScene("../resources/fxml/trendline.fxml");
    }
}

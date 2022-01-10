package CompanyManagementRepository.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {
    private final Main main = new Main();

    @FXML void AddLog(ActionEvent event) throws IOException {
        main.changeScene("/fxml/addLog.fxml");
    }

    @FXML void addEmployee(ActionEvent event) throws IOException {
        main.changeScene("/resources/fxml/addEmployee.fxml");
    }

    @FXML void addFeedback(ActionEvent event) throws IOException {
        main.changeScene("/fxml/addFeedback.fxml");
    }

    @FXML void addUser(ActionEvent event) throws IOException {
        main.changeScene("/fxml/addUser.fxml");
    }

    @FXML void showAllEmployees(ActionEvent event) throws IOException {
        main.changeScene("/fxml/showEmployees.fxml");
    }

    @FXML void showAllFeedbacks(ActionEvent event) throws IOException {
        main.changeScene("/fxml/showFeedbacks.fxml");
    }

    @FXML void showAllLogs(ActionEvent event) throws IOException {
        main.changeScene("/fxml/showLogs.fxml");
    }

    @FXML void showAllUsers(ActionEvent event) throws IOException {
        main.changeScene("/fxml/showUsers.fxml");
    }

    @FXML void showTrendline(ActionEvent event) throws IOException {
        main.changeScene("/fxml/trendline.fxml");
    }
}

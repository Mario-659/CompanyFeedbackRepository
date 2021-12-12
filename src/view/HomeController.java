package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    @FXML void addUser(ActionEvent event) throws IOException {
        Stage popup = makePopup("addUser.fxml", "addUser");
        main.changeScene("addUser.fxml");
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

    private Stage makePopup(String fxml, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage popup = new Stage();
        popup.setScene(new Scene(root));
        popup.setTitle(title);
        popup.initModality(Modality.NONE);
        popup.setResizable(false);
        return popup;
    }
}

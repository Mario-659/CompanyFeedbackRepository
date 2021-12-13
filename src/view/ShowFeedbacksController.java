package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Feedback;
import service.FeedbackService;

import java.io.IOException;
import java.sql.SQLException;

public class ShowFeedbacksController {
    private FeedbackService feedbackService = new FeedbackService();

    @FXML private TableView<Feedback> feedbacksTable;
    @FXML private TableColumn<Feedback, String> dateCol;
    @FXML private TableColumn<Feedback, String> descriptionCol;
    @FXML private TableColumn<Feedback, String> employeeCol;
    @FXML private TableColumn<Feedback, Integer> idCol;
    @FXML private TableColumn<Feedback, Boolean> isPositiveCol;
    @FXML private TableColumn<Feedback, Integer> significanceCol;
    @FXML private TableColumn<Feedback, String> submitterCol;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Main main = new Main();
        main.changeScene("/resources/fxml/home.fxml");
    }

    @FXML
    private void initialize(){
        feedbacksTable.setItems(feedbackService.getFeedbacks());
        idCol.setCellValueFactory(new PropertyValueFactory<Feedback, Integer>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Feedback, String>("dateTime"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Feedback, String>("description"));
        significanceCol.setCellValueFactory(new PropertyValueFactory<Feedback, Integer>("significance"));
        submitterCol.setCellValueFactory(new PropertyValueFactory<Feedback, String>("submitter"));
        employeeCol.setCellValueFactory(new PropertyValueFactory<Feedback, String>("employee"));
        isPositiveCol.setCellValueFactory(new PropertyValueFactory<Feedback, Boolean>("positive"));
    }

    @FXML
    private void deleteFeedbacks(ActionEvent actionEvent) {
        ObservableList<Feedback> selectedFeedback = feedbacksTable.getSelectionModel().getSelectedItems();
        //TODO might produce bug when there are more than 1 selected feedbacks but with iter it throws error "no such element"
        feedbackService.delete(selectedFeedback.get(0));
    }
}

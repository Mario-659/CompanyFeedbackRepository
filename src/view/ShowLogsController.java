package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Feedback;
import model.Log;
import service.LogsService;

import java.io.IOException;

public class ShowLogsController {
    private LogsService logsService = new LogsService();

    @FXML private TableView<Log> logsTable = new TableView<Log>();

    @FXML private TableColumn<Log, String> dateCol;

    @FXML private TableColumn<Log, String> descriptionCol;

    @FXML private TableColumn<Log, Integer> idCol;

    @FXML private TableColumn<Log, Integer> significanceCol;

    @FXML private TableColumn<Log, String> subjectCol;

    @FXML private TableColumn<Log, String> submitterCol;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Main main = new Main();
        main.changeScene("/resources/fxml/home.fxml");
    }

    @FXML
    public void initialize() {
        logsTable.setItems(logsService.getLogs());
        idCol.setCellValueFactory(new PropertyValueFactory<Log, Integer>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Log, String>("dateTime"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Log, String>("description"));
        significanceCol.setCellValueFactory(new PropertyValueFactory<Log, Integer>("significance"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<Log, String>("subject"));
        submitterCol.setCellValueFactory(new PropertyValueFactory<Log, String>("user"));
    }

    @FXML
    private void deleteLog(ActionEvent actionEvent) {
        ObservableList<Log> selectedLog = logsTable.getSelectionModel().getSelectedItems();
        //TODO might produce bug when there are more than 1 selected feedbacks but with iter it throws error "no such element"
        logsService.delete(selectedLog.get(0));
    }
}

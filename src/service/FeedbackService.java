package service;

import Database.DAO.FeedbackDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Feedback;

import java.sql.SQLException;

public class FeedbackService {
    private static FeedbackDAO feedbackDAO = new FeedbackDAO();
    private static ObservableList<Feedback> feedbacks = FXCollections.observableArrayList();

    public ObservableList<Feedback> getFeedbacks() {
        update();
        return feedbacks;
    }

    private void update(){
        try {
            feedbacks.setAll(feedbackDAO.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

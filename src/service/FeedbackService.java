package service;

import Database.DAO.FeedbackDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.Feedback;
import model.User;
import validators.InputValidator;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class FeedbackService {
    private static FeedbackDAO feedbackDAO = new FeedbackDAO();
    private static ObservableList<Feedback> feedbacks = FXCollections.observableArrayList();

    public ObservableList<Feedback> getFeedbacks() {
        update();
        return feedbacks;
    }

    public boolean addFeedback(LocalDateTime dateTime, User submitter, Employee employee, boolean positive, String significance, String description){
        if(!InputValidator.validateSignificance(significance)) return false;
        description = InputValidator.checkBlank(description);
        if(description == null) return false;

        Feedback feedback = new Feedback(dateTime, submitter, employee, positive, Integer.parseInt(significance), description);
        try {
            feedbackDAO.save(feedback);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void update(){
        try {
            feedbacks.setAll(feedbackDAO.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

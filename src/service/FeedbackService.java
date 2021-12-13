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
import java.util.List;
import java.util.stream.Collectors;

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

    public String getTrend(Employee employee){
        try {
            String message = employee.toString() + " has ";
            List<Feedback> allFeedbacks = feedbackDAO.getAll();
            List<Feedback> empFeedbacks = allFeedbacks.stream().filter(x -> x.getEmployee().getId() == employee.getId()).collect(Collectors.toList());
            int posNum = (int) empFeedbacks.stream().filter(Feedback::isPositive).count();
            message += posNum + " positive opinions";
            if(posNum != 0) {
                float avgPosWeight = empFeedbacks.stream().filter(Feedback::isPositive).mapToInt(Feedback::getSignificance).sum() / posNum;
                message += " with an average weight of " + avgPosWeight;
            }

            int negNum = (int) empFeedbacks.stream().filter(x -> !x.isPositive()).count();
            message += " and " + negNum + " negative opinions ";
            if(negNum != 0){
                float avgNegWeight = empFeedbacks.stream().filter(x -> !x.isPositive()).mapToInt(Feedback::getSignificance).sum() / negNum;
                message += " with an average weight of " + avgNegWeight;
            }
            return message;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void update(){
        try {
            feedbacks.setAll(feedbackDAO.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Feedback feedback) {
        try {
            feedbackDAO.delete(feedback);
            update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

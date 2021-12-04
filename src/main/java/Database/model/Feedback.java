package Database.model;

import java.time.LocalDateTime;

public class Feedback {
    private int id;
    private LocalDateTime dateTime;
    private User submitter;
    private Employee employee;
    private enum type{
        POSITIVE, NEGATIVE
    }
    private int significance;
    private String description;

    public Feedback(LocalDateTime dateTime, User submitter, Employee employee, int significance, String description) {
        assert significance >= 0 && significance <=5: "Importance of a feedback must be in range {0, 5}. " + significance + " was given";

        this.dateTime = dateTime;
        this.submitter = submitter;
        this.employee = employee;
        this.significance = significance;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSignificance() {
        return significance;
    }

    public void setSignificance(int significance) {
        this.significance = significance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", submitter=" + submitter +
                ", employee=" + employee +
                ", significance=" + significance +
                ", description='" + description.substring(0, 5) + "..." + '}';
    }
}

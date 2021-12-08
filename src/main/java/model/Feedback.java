package model;

import java.time.LocalDateTime;

public class Feedback {
    private int id;
    private LocalDateTime dateTime;
    private User submitter;
    private Employee employee;
    boolean positive;
    private int significance;
    private String description;

    public Feedback(LocalDateTime dateTime, User submitter, Employee employee, boolean positive, int significance, String description) {
        assert significance >= 0 && significance <=5: "Importance of a feedback must be in range {0, 5}. " + significance + " was given";

        this.dateTime = dateTime;
        this.submitter = submitter;
        this.employee = employee;
        this.positive = positive;
        this.significance = significance;
        this.description = description;
    }

    public Feedback(){}

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

    public boolean isPositive() { return positive; }

    public void setPositive(boolean positive) { this.positive = positive; }

    public int getSignificance() {
        assert significance >= 0 && significance <=5: "Importance of a feedback must be in range {0, 5}. " + significance + " was given";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (id != feedback.id) return false;
        if (positive != feedback.positive) return false;
        if (significance != feedback.significance) return false;
        if (dateTime != null ? !dateTime.equals(feedback.dateTime) : feedback.dateTime != null) return false;
        if (submitter != null ? !submitter.equals(feedback.submitter) : feedback.submitter != null) return false;
        if (employee != null ? !employee.equals(feedback.employee) : feedback.employee != null) return false;
        return description != null ? description.equals(feedback.description) : feedback.description == null;
    }
}

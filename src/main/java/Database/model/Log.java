package Database.model;

import java.time.LocalDateTime;

public class Log {
    private int id;
    private LocalDateTime dateTime;
    private String subject;
    private User user;
    private int significance;
    private String description;

    public Log(LocalDateTime dateTime, String subject, User user, int significance, String description){
        assert significance >= 0 && significance <=5: "Importance of a log must be in range {0, 5}. " + significance + " was given";

        this.dateTime = dateTime;
        this.subject = subject;
        this.user = user;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "Log{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", subject='" + subject +
                ", user=" + user +
                ", significance=" + significance +
                ", description='" + description.substring(0, 5) + "..." + '}';
    }
}

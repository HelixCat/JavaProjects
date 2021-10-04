package model;

public class User {

    private int id;
    private String score;
    private String firstName;
    private String lastName;
    private String status;
    private String password;

    public User(int id, String score, String firstName, String lastName, String status, String password) {
        this.id = id;
        this.score = score;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

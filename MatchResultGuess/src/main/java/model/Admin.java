package model;

public class Admin {

    private int id;
    private String firstName;
    private String lastName;
    private final String score = null;
    private String status;
    private String password;

    public Admin(int id, String firstName, String lastName, String status, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getScore() {
        return score;
    }

    public String getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }
}

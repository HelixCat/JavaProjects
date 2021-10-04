package database;

import model.Admin;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Statements {


    Scanner scanner;
    private Statement statement;


    public Statements() throws SQLException {
        scanner  = new Scanner(System.in);
        ConnectionManager.setValue();
        Connection conn = ConnectionManager.getConnection();
        statement = conn.createStatement();

    }

    public void addRecord(Object object) {

        if (object instanceof Admin) {

            try {


                String insertFields = "INSERT INTO game.persons (id, firstName, lastName, score, status, password) VALUES (";
                String insertValues = ((Admin) object).getId() + ",'" + ((Admin) object).getFirstName() + "','"
                        + ((Admin) object).getLastName() + "','" + ((Admin) object).getScore()
                        + "','" + ((Admin) object).getStatus() + "','" + ((Admin) object).getPassword() + "')";

                String addRecordQuery = insertFields + insertValues;

                statement.executeUpdate(addRecordQuery);

                System.out.printf("record %d added to person as admin: ", ((Admin) object).getId());


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (object instanceof User) {

            try {


                String insertFields = "INSERT INTO game.persons (id, firstName, lastName, score, status, password) VALUES (";
                String insertValues = ((User) object).getId() + ",'" + ((User) object).getFirstName() + "','"
                        + ((User) object).getLastName() + "','" + ((User) object).getScore()
                        + "','" + ((User) object).getStatus() + "','" + ((User) object).getPassword() + "')";

                String addRecordQuery = insertFields + insertValues;

                statement.executeUpdate(addRecordQuery);

                System.out.printf("record %d added to person as user: ", ((User) object).getId());


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }


    public void showRecord( int id) {

        try {

            String showRecordQuery = "select id, firstName, lastName, score, status, password from game.persons where id = " + id;
            ResultSet resultSet = statement.executeQuery(showRecordQuery);

            while (resultSet.next()) {

                System.out.println(resultSet.getInt(1) + " " +
                        resultSet.getString(2) + " " +
                        resultSet.getString(3) + " " +
                        resultSet.getString(4));

            }


        } catch (SQLException ex) {

            ex.printStackTrace();

        }

    }

    public void updateRecord(Object object, String score) {

        try {

            if (object instanceof User) {

                ((User) object).setScore(score);

                String updateRecordQuery = "UPDATE game.persons SET score = " +
                        "'" + ((User) object).getScore() + "'" + " where id = " + ((User) object).getId();

                int updatedNumber = statement.executeUpdate(updateRecordQuery);
                System.out.printf("record %d updated as admin\n", updatedNumber);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    public boolean validateLogin(int id, String password) throws SQLException {

        boolean flag = false;

        try {

            String verification = "SELECT * FROM game.persons WHERE id ='" + id + "' AND PASSWORD='" + password + "'";
            ResultSet resultSet = statement.executeQuery(verification);

            if (resultSet.next()) {

                flag = true;

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return flag;

    }


    public String findStatus(int id) throws SQLException {

        String status = "";

        try {

            String query = "SELECT status From game.persons WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                status = resultSet.getString("status");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return status;

    }

}

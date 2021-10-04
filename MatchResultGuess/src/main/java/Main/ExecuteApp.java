package Main;

import database.Statements;
import model.Admin;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;


public class ExecuteApp {

    Scanner scanner = new Scanner(System.in);
    Pattern digitPattern = Pattern.compile("\\d{1}");
    Statements statements = new Statements();

    private List<String> existingMatches = new ArrayList<>();
    private Map<String, String> endedMatches = new TreeMap<>();
    private Map<String, String> predictMatches = new TreeMap<>();

    public ExecuteApp() throws SQLException {
    }

    public void welcome() throws SQLException, IOException {

        System.out.println("welcome to Mahdi bet online\n *** we love your money *** \n***************************\n" +
                "\n\nregister => 1 login => 2 exit => 3");
        String answer = scanner.nextLine();

        while (!digitPattern.matcher(answer).matches() && (answer != "1" || answer != "2" || answer != "3")) {

            System.out.print("Please insert just number value with String Type: 1 => register, 2 => login, 3 => exit ");
            answer = scanner.nextLine();

        }

        switch (answer) {

            case "1":
                register();
                break;
            case "2":
                login();
                break;
            case "3":
                System.exit(0);
                break;

        }

    }

    public void register() {

        PasswordValidator passwordValidator = new PasswordValidator();
        StatusValidator statusValidator = new StatusValidator();

        System.out.print("Please insert the your id");
        String id = scanner.nextLine();

        while (!digitPattern.matcher(id).matches()) {

            System.out.print("""
                    Please insert just number value with String Type
                    Please insert the value of your id: 
                    """);
            id = scanner.nextLine();

        }

        scanner.nextLine();

        System.out.print("Please insert the value of your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Please insert the value of your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Please insert the value of your password: ");
        String password = scanner.nextLine();

        while (!(passwordValidator.passwordValidateCharacterFromUnicode(password)
                && passwordValidator.passwordValidateDigit(password)
                && passwordValidator.passwordValidateLength(password)
                && passwordValidator.passwordValidateSpace(password)
                && passwordValidator.passwordValidateUppercase(password)
                && passwordValidator.passwordValidateLowercase(password))) {

            System.out.print("""
                    Your password must have at least 7 Character with lower and upper case words
                    you have to user unicode character in your password and digits and dont use space:
                    please insert your password""");
            password = scanner.nextLine();

        }

        System.out.print("Please insert the value of your status: admin, user");
        String status = scanner.nextLine();


        while (!(statusValidator.validateAdminStatus(status) || statusValidator.validateUserStatus(status))) {

            System.out.print("Please just use admin or user for status: ");
            status = scanner.nextLine();

        }

        if (status.equalsIgnoreCase("admin")) {

            Admin admin = new Admin(Integer.valueOf(id), firstName, lastName, status, password);

            statements.addRecord(admin.getClass());

        }else if (status.equalsIgnoreCase("user")) {

            User user = new User(Integer.valueOf(id), "0", firstName, lastName, status, password);

            statements.addRecord(user.getClass());

        }

    }

    public void login() throws SQLException, IOException {

        System.out.print("Please insert your id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please insert your password: ");
        String password = scanner.nextLine();

        boolean flag = statements.validateLogin(id, password);

        if (flag == true) {

            String status = statements.findStatus(id);

            if (status.equalsIgnoreCase("admin")){

                adminChooseAction();

            } else {

                userChooseAction();

            }

        } else {

            System.err.println("invalid login. please try again");
            login();

        }

    }

    public void adminChooseAction() throws SQLException, IOException {


        System.out.println("""
                welcome to your admin page:\s
                please choose your action:\s
                1 => create match,
                2 => submit the final result (close match),
                3 => come back to main menu\s""");

        String answer = scanner.nextLine();

        while (!digitPattern.matcher(answer).matches() && (answer != "1" || answer != "2" || answer != "3")) {

            System.out.print("Please insert just number value with String Type: 1 =>  create match," +
                    " 2 => submit the final result, 3 => come back to main menu ");
            answer = scanner.nextLine();

        }

        switch (answer) {

            case "1":
                createMatch();
                break;
            case "2":
                finalResultInput();
                break;
            case "3":
                welcome();
                break;

        }

    }

    public void userChooseAction() throws SQLException, IOException {

        System.out.println("""
                welcome to your user page:\s
                please choose your action:\s
                1 => predict match result,
                2 => display score,
                3 => come back to main menu\s""");

        String answer = scanner.nextLine();

        while (!digitPattern.matcher(answer).matches() && (answer != "1" || answer != "2" || answer != "3")) {

            System.out.print("Please insert just number value with String Type: 1 =>  predict match result, 2 => display score, 3 => come back to main menu ");
            answer = scanner.nextLine();

        }

        switch (answer) {

            case "1":
                predictMatchResult();
                break;
            case "2":
                System.out.println("Please Insert your id:");
                int id = scanner.nextInt();
                statements.showRecord(id);
                break;
            case "3":
                welcome();
                break;

        }

    }

    public void createMatch() {

        try {

            System.out.println("do you want create match: 1 => yes, 0 => no: ");
            String answer = scanner.nextLine();
            scanner.nextLine(); // to fix scanner next problem

            while (!digitPattern.matcher(answer).matches() && (answer != "1" || answer != "0")) {

                System.out.print("Please insert just number value with String Type: 1 => yes, 0 => no: ");
                answer = scanner.nextLine();

            }

            if (answer.equalsIgnoreCase("1")) {

                System.out.print("Please insert the value of game id:\n just using number with String type: ");
                String gameId = scanner.nextLine();

                while (!digitPattern.matcher(answer).matches()) {

                    System.out.print("Please insert the value of game id:\n just using number with String type: ");
                    gameId = scanner.nextLine();

                }

                existingMatches.add(gameId);


            } else {

                System.out.print("do you want go back to the main menu: 1 => admin menu, 2 => main menu:");
                String answer1 = scanner.nextLine();

                while (!digitPattern.matcher(answer1).matches() && (answer1 != "1" || answer1 != "0")) {

                    System.out.print("Please insert just number value with String Type:1 => admin menu, 2 => main menu: ");
                    answer1 = scanner.nextLine();

                }

                if (answer1.equalsIgnoreCase("1")) {


                    adminChooseAction();

                } else {

                    welcome();

                }

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

    public String finalResultInput() {

        String inputMatchResult = null;

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Please insert the value of gameId: ");
            String gameId = scanner.nextLine();

            while (!digitPattern.matcher(gameId).matches()) {

                System.out.print("Please insert just number value with String Type: \n" +
                        "Please insert the point value of game id: ");
                gameId = scanner.nextLine();

            }

            while (endedMatches.containsKey(gameId)){

                System.out.print("This match has been ended you can't predict the ended match\nPlease insert new game id: ");
                gameId = scanner.nextLine();

                while (!digitPattern.matcher(gameId).matches()) {

                    System.out.print("Please insert just number value with String Type: \n" +
                            "Please insert the point value of game id: ");
                    gameId = scanner.nextLine();

                }

            }

            System.out.print("Please insert the point value of team1: ");
            String team1points = scanner.nextLine();

            while (!digitPattern.matcher(team1points).matches()) {

                System.out.print("Please insert just number value with String Type: \n" +
                        "Please insert the point value of team1: ");
                team1points = scanner.nextLine();

            }

            System.out.print("Please insert the point value of team2: ");
            String team2points = scanner.nextLine();

            while (!digitPattern.matcher(team2points).matches()) {

                System.out.print("Please insert just number value with String Type: \n" +
                        "Please insert the point value of team2: ");
                team2points = scanner.nextLine();

            }

            inputMatchResult = team1points + "," + team2points;
            endedMatches.put(gameId, inputMatchResult);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return inputMatchResult;

    }

    public void predictMatchResult() {

        String predictInput = null;

        System.out.println("Please insert the value of game id that you want predict: ");
        String gameId = scanner.nextLine();

        while (!digitPattern.matcher(gameId).matches()) {

            System.out.print("Please insert just number value with String Type:\n" +
                    " Please insert the value of game id that you want predict: ");
            gameId = scanner.nextLine();

        }

        if (existingMatches.contains(gameId) && !endedMatches.containsKey(gameId)) {

            System.out.print("Please insert the point value of team1: ");
            String team1points = scanner.nextLine();

            while (!digitPattern.matcher(team1points).matches()) {

                System.out.print("Please insert just number value with String Type: \n" +
                        "Please insert the point value of team1: ");
                team1points = scanner.nextLine();

            }

            System.out.print("Please insert the point value of team2: ");
            String team2points = scanner.nextLine();

            while (!digitPattern.matcher(team2points).matches()) {

                System.out.print("Please insert just number value with String Type: \n" +
                        "Please insert the point value of team2: ");
                team2points = scanner.nextLine();

            }

            predictInput = team1points + "," + team2points;

        } else {

            System.out.println("this match is closed, you can't predict closed match");
            predictMatchResult();

        }

        predictMatches.put(gameId, predictInput);

    }

}

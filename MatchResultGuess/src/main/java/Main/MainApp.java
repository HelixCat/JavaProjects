package Main;

import java.io.IOException;
import java.sql.SQLException;

public class MainApp {

    public static void main(String[] args) throws SQLException, IOException {

        ExecuteApp executeApp = new ExecuteApp();
        executeApp.welcome();

    }

}

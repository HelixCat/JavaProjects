package Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatusValidator {

    private Pattern pattern;
    private Matcher matcher;

    private final String ADMIN_PATTERN = "\\bAdmin\\b";
    private final String USER_PATTERN = "\\bUser\\b";

    public boolean validateAdminStatus(String status) {

        pattern = Pattern.compile(ADMIN_PATTERN);
        matcher = pattern.matcher(status);

        return matcher.matches();

    }

    public boolean validateUserStatus(String status) {

        pattern = Pattern.compile(USER_PATTERN);
        matcher = pattern.matcher(status);

        return matcher.matches();

    }

}

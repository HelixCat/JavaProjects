/*
 *    Filename:  PasswordValidator.java
 *
 *    Description:  DrugApplication _  This program and this class validate the password to be powerful.
 *
 *
 *       Created:  05/01/2021 01:02:00
 *       Revision: ...................
 *
 *    Author:  Mehdi ghomsheh
 *
 *
 * =====================================================================================
 */
package Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class PasswordValidator {

    private Pattern pattern;
    private Matcher matcher;

    private final String LOWERCASE_PATTERN = "(?=.*[a-z]).*"; // store value of lowercase pattern
    private final String UPPERCASE_PATTERN = "(?=.*[A-Z]).*"; // store value of uppercase pattern
    private final String DIGIT_PATTERN = "(?=.*\\d).*"; // store value of digit pattern
    private final String UNICODE_PATTERN = "(?=.*[~!@#$%^&*()_-]).*"; // store value of unicode pattern
    private final String LENGTH_PATTERN = ".{8,40}"; // store value of length pattern
    private final String SPACE_PATTERN = "(?=\\S+$)"; // store value of Space pattern

    //start method passwordValidateLowerCase to validate the password based on lowercase
    public boolean passwordValidateLowercase(String password) {

        pattern = Pattern.compile(LOWERCASE_PATTERN);

        matcher = pattern.matcher(password);

        return matcher.matches();

    } // end method passwordValidateLowercase

    // start method passwordValidateUppercase to validate the password based on Uppercase
    public boolean passwordValidateUppercase(String password) {

        pattern = Pattern.compile(UPPERCASE_PATTERN);

        matcher = pattern.matcher(password);

        return matcher.matches();

    } // end method passwordValidateUppercase

    // start method passwordValidateDigit to validate the password based on Digit
    public boolean passwordValidateDigit(String password) {

        pattern = Pattern.compile(DIGIT_PATTERN);

        matcher = pattern.matcher(password);

        return matcher.matches();

    } // end method passwordValidateDigit

    // start method passwordValidateCharacterFromUnicode to validate the password based on Character From Unicode
    public boolean passwordValidateCharacterFromUnicode(String password) {

        pattern = Pattern.compile(UNICODE_PATTERN);

        matcher = pattern.matcher(password);

        return matcher.matches();

    } // end method passwordValidateCharacterFromUnicode

    // start method passwordValidateLength to validate the password based on Length
    public boolean passwordValidateLength(String password) {

        pattern = Pattern.compile(LENGTH_PATTERN);

        matcher = pattern.matcher(password);

        return matcher.matches();

    } // end method passwordValidateLength

    // start method passwordValidateSpace to validate the password based on Space 
    public boolean passwordValidateSpace(String password) {

        pattern = Pattern.compile(SPACE_PATTERN);

        matcher = pattern.matcher(password);

        return matcher.matches();

    } // end method passwordValidateSpace

} // end class PasswordValidator

package com.quickquiz.helper;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.*;

public class PasswordValidator {

    public boolean isValidPassword(String password, HttpServletResponse resp) throws IOException {
        int validationResult = validatePassword(password);
        switch (validationResult) {
            case 0:
                return true;
            case 1:
                sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Password is too short.");
                break;
            case 2:
                sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Password is too long.");
                break;
            case 3:
                sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Password must contain at least one uppercase letter.");
                break;
            case 4:
                sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Password must contain at least one lowercase letter.");
                break;
            case 5:
                sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Password must contain at least one digit.");
                break;
            case 6:
                sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Password must contain at least one special character.");
                break;
            default:
                sendError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unknown error.");
        }
        return false;
    }

    private int validatePassword(String password) {
        // Define password criteria
        int minLength = 8;
        int maxLength = 20;
        String uppercaseRegex = ".*[A-Z].*";
        String lowercaseRegex = ".*[a-z].*";
        String digitRegex = ".*\\d.*";
        String specialCharacterRegex = ".*[@#$%^&+=].*";

        // Check length
        if (password.length() < minLength) {
            return 1; // Password is too short
        } else if (password.length() > maxLength) {
            return 2; // Password is too long
        }

        // Check for uppercase, lowercase, digit, and special character
        Pattern uppercasePattern = Pattern.compile(uppercaseRegex);
        Pattern lowercasePattern = Pattern.compile(lowercaseRegex);
        Pattern digitPattern = Pattern.compile(digitRegex);
        Pattern specialCharacterPattern = Pattern.compile(specialCharacterRegex);

        Matcher uppercaseMatcher = uppercasePattern.matcher(password);
        Matcher lowercaseMatcher = lowercasePattern.matcher(password);
        Matcher digitMatcher = digitPattern.matcher(password);
        Matcher specialCharacterMatcher = specialCharacterPattern.matcher(password);

        if (!uppercaseMatcher.matches()) {
            return 3; // Password must contain at least one uppercase letter
        } else if (!lowercaseMatcher.matches()) {
            return 4; // Password must contain at least one lowercase letter
        } else if (!digitMatcher.matches()) {
            return 5; // Password must contain at least one digit
        } else if (!specialCharacterMatcher.matches()) {
            return 6; // Password must contain at least one special character
        }

        return 0; // Password is valid
    }

    private void sendError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.getWriter().write(message);
    }
}

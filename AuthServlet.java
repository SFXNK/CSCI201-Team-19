package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            login(request, response);
        } else if ("signup".equals(action)) {
            signUp(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        // Get username and password from request
        // Query the database for validation
        // Return login status (success or failure)
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) {
        // Get username, email, and password from request
        // Check if the user already exists
        // Insert new user into the database if not found
        // Return signup status (success or error message)
    }
}

package org.example.parkingsystemv2.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parkingsystemv2.authorization.sign_up.Sign_UP;
import org.json.JSONObject;

import java.io.IOException;

@WebServlet(name = "signupservlet", value = "/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            stringBuilder.append(line);
        }

        JSONObject requestJson = new JSONObject(stringBuilder.toString());
        String username = requestJson.getString("username");
        String name = requestJson.getString("name");
        String surname = requestJson.getString("surname");
        String email = requestJson.getString("email");
        String phone = requestJson.getString("phone");
        String password = requestJson.getString("password");
        String againPassword = requestJson.getString("againPassword");

        String result = register(username, name, surname, email, phone, password, againPassword);
        boolean success = result.isEmpty();
        String message = success ? "Registration Successful" : "Registration Failed: " + result;
        String errorCode = success ? "success" : "failure";

        JSONObject responseJson = new JSONObject();
        responseJson.put("success", success);
        responseJson.put("message", message);
        responseJson.put("errorCode", errorCode);

        response.getWriter().write(responseJson.toString());
    }

    private String register(String username, String name, String surname, String email, String phone, String password, String againPassword) {
        Sign_UP signUp = new Sign_UP(username, name, surname, email, phone, password, againPassword);
        return signUp.signUp();
    }
}

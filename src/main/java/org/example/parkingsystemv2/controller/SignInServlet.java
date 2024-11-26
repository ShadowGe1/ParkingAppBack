package org.example.parkingsystemv2.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parkingsystemv2.authorization.sign_in.Sign_IN;
import org.json.JSONObject;

import java.io.IOException;

@WebServlet(name = "signinservlet", value = "/signin")
public class SignInServlet extends HttpServlet {

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
        String username = requestJson.getString("email");
        String password = requestJson.getString("password");

        boolean success = authenticate(username, password);
        String message = success ? "Login successful" : "Invalid username or password";

        JSONObject responseJson = new JSONObject();
        responseJson.put("success", success);
        responseJson.put("message", message);

        response.getWriter().write(responseJson.toString());
    }

    private boolean authenticate(String email, String password) {
        Sign_IN signIn = new Sign_IN(email, password);
        return signIn.checkEmail() && signIn.checkPassword();
    }
}

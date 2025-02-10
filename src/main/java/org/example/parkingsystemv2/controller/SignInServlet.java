package org.example.parkingsystemv2.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.parkingsystemv2.authorization.sign_in.Sign_IN;
import org.example.parkingsystemv2.entity.User;
import org.example.parkingsystemv2.service.UserRepository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebServlet(name = "signinservlet", value = "/signin")
public class SignInServlet extends HttpServlet {
    private String username = "";

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
        String email = requestJson.getString("email");
        String password = requestJson.getString("password");

        boolean success = authenticate(email, password);
        String message = success ? "Login successful" : "Invalid username or password";

        JSONObject responseJson = new JSONObject();
        responseJson.put("success", success);
        responseJson.put("message", message);
        responseJson.put("username", username);

        response.getWriter().write(responseJson.toString());
    }

    private boolean authenticate(String email, String password) {
        Sign_IN signIn = new Sign_IN(email, password);
        if (signIn.checkEmail() && signIn.checkPassword()) {
            UserRepository userRepository = new UserRepository();
            Optional<User> user = userRepository.getUserByEmail(email);
            user.ifPresent(value -> this.username = value.getUsername());
            return true;
        }
        return false;
    }
}

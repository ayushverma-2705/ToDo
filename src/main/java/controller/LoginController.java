package controller;
import database.Store;
/**
 * Created by ayush.v on 10/08/17.
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.servlet.http.Cookie;


import org.json.*;
import todo.LoginService;

@WebServlet(name="LoginController",urlPatterns={"/login"})

public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        (new LoginService()).service(request, response);
        return;
    }
}


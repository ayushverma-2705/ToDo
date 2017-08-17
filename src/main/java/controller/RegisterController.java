package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import todo.RegisterService;

/**
 * Created by ayush.v on 10/08/17.
 */


@WebServlet(name="RegisterController",urlPatterns={"/register"})

public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RegisterService.registerNewUser(request, response);
    }
}


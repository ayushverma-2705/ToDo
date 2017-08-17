package controller;

import todo.AssignService;
import todo.Pair;
import database.Store;
import todo.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Long.parseLong;

@WebServlet(name="ModifyController",urlPatterns={"/modify" })

public class ModifyController  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AssignService.assignIt(request, response);
    }
}

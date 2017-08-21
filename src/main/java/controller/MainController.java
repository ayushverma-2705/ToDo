package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import database.Store;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
import todo.AddTodoService;
import todo.FetchTodoService;
import todo.Pair;
import todo.Todo;
import user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.*;

import static java.lang.Long.parseLong;

/**
 * Created by ayush.v on 10/08/17.
 */


@WebServlet(name="MainController",urlPatterns={"/addTask" })

public class MainController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("In GET MAIN Controller");
        FetchTodoService.fetchIT(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        AddTodoService.addTodo(request, response);
        return;
    }

}


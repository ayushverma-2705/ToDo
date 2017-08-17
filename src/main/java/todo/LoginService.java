package todo;

import database.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

public class LoginService {
    Store obj = Store.getInstance();

    public String Validation(String username, String password) {
        String message;

        if (obj.checkUsername(username)) {
            if (obj.getUser(username).getPassword().equals(password)) {
                message = "Success";
            } else {
                message = "Invalid Password";
            }
        } else {
            message = "Invalid User";
        }

        return message;
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String message = Validation(username, password);

        if (message.equals("Success")) {
            System.out.println("Login Successfull");
            String token =  new BigInteger(130, new SecureRandom()).toString(32);
            Cookie session = new Cookie("session-id", token);
            response.addCookie(session);

            Cookie userCookie = new Cookie("username", username);
            response.addCookie(userCookie);

            obj.addLogin(token, username);

            response.sendRedirect(request.getContextPath() + "/home.html");
            return;
        } else {
            request.setAttribute("error", message);
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.include(request, response);
        }
    }
}

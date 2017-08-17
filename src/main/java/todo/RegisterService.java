package todo;

import database.Store;
import user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RegisterService {
    static Store obj = Store.getInstance();

    public static void registerNewUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username1");
        String password = request.getParameter("password1");
        String emailId = request.getParameter("email");
        String phone = request.getParameter("phone");

        String message = null;

        System.out.println(" Post Register Started \n");

        response.setContentType("text/html");

        System.out.println("username " + username + " pasword " + password);
        if (obj.checkUsername(username)) {
            message = "UserName Already Exists ...!!! Try Another ";

            System.out.println("User Exists  \n");

            //response.sendRedirect(request.getContextPath());

            request.setAttribute("error", message);
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.include(request, response);

//            PrintWriter out = response.getWriter();
//            out.write(message);
//
//            out.flush();

            return;

        } else {
            User user = new User(username, password, emailId, phone);

            obj.addRegister(username, user);

            String token = new BigInteger(130, new SecureRandom()).toString(32);
            Cookie session = new Cookie("session-id", token);
            response.addCookie(session);

            Cookie userCookie = new Cookie("username", username);
            response.addCookie(userCookie);

            obj.addLogin(token, username);


            System.out.println(" New User Register Started \n");


            response.sendRedirect(request.getContextPath() + "/home.html");

            return;

            // RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
            // dispatcher.include(request,response);

        }
    }
}

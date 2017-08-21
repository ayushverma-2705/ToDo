package todo;

import database.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Long.parseLong;

public class DeleteService {
    static Store obj = Store.getInstance();

    public static void notExists(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        String message = "";
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("Content-Type", "text/plain");
        message = "Someone has already Deleted it";
        writer.print(message);
        writer.flush();
        writer.close();
    }


    public  static void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(! CheckLogin.checkLogin(request,response)){

            response.sendRedirect("request.getContextPath()");

            return;
        }

        System.out.println("Delete Service");

        long todoId = parseLong(request.getParameter("todoid"));
        int status = Integer.parseInt(request.getParameter("status"));

        if (obj.removeToDo(todoId, status)) {
            obj.addToTrack(todoId);

            System.out.println(" Deletion Successfull");
        } else {
            notExists(request, response);
        }

    }
}

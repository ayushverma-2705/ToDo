package todo;

import controller.MainController;
import database.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Long.parseLong;

public class AssignService {
    static Store obj = Store.getInstance();

    public static void notExists(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        String message = "";
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("Content-Type", "text/plain");
        message = "Someone has already Updated it";
        writer.print(message);
        writer.flush();
        writer.close();
    }

    public static void assignIt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(! CheckLogin.checkLogin(request,response)){

            response.sendRedirect("request.getContextPath()");

            return;
        }

        long todoId = parseLong(request.getParameter("todoid"));
        String assignTo = request.getParameter("assignTo");
        int status = Integer.parseInt(request.getParameter("status"));

        String assignBy = CheckLogin.getUserFromSessionId(request,response);

        System.out.println("In doPut  ");
        System.out.println(todoId);
        System.out.println(assignTo);
        System.out.println(assignBy);
        System.out.println("status :-- " + status);

        long box = status;

        //System.out.println("Printing Pending ToDo'S");
        // obj.printPendingToDo();

        if (box == 0) {
            synchronized (AssignService.class) {

                Todo todo = obj.deleteInPending(todoId);

                if (todo == null) {
                    notExists(request, response);

                    return;
                }

                System.out.println(" In Put todo after deletion" + todo.gettId());

                todo.setAssignBy(assignBy);
                todo.setAssignTo(assignTo);
                obj.addInProgress(todo);

                obj.addToTrack(todo.gettId());
            }
        } else if (box == 1) {
            synchronized (AssignService.class) {
                Todo todo = obj.deleteInProgress(todoId);

                if (todo == null) {
                    notExists(request, response);

                    return;
                }

                obj.addinCompleted(todo);

                obj.addToTrack(todo.gettId());
            }
        }

        return;
    }
}

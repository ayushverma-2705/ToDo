package todo;

import database.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTodoService {
    static Store obj = Store.getInstance();

    public static void addTodo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(!CheckLogin.checkLogin(request,response)){
            System.out.println("  LOGIN FAILED!!!!!!!");
            return;
        }


        System.out.println(" At Do Post Method");


        String title = request.getParameter("title");
        String description= request.getParameter("description");
        String userName = CheckLogin.getUserFromSessionId(request,response);


        System.out.println(title + "   " + description + "   " + userName);
        Todo todo = new Todo(title,description, userName);

        obj.createTodoItem(todo);
//
//        System.out.println("At post method");
//        obj.printPendingToDo();

        obj.addToTrack(todo.gettId());


       // System.out.println(" On Exit Do-Post Method Pending To Do is :-");
        // obj.printPendingToDo();


//        response.sendRedirect(request.getContextPath() + "/home.html");
    }
}

package todo;

import database.Store;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutService {
    static Store obj = Store.getInstance();

    public static void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[]  cookies = request.getCookies();

        for (Cookie cur : cookies) {
            if (cur.getName().equals("session-id")) {
                obj.removeUserLogin(cur.getValue());
                cur = new Cookie("session-id",null);
                cur.setMaxAge(0);
                response.addCookie(cur);
                response.sendRedirect( "/ToDo");
                return;
            }
        }
        response.sendRedirect( "/ToDo");
        return;
    }
}

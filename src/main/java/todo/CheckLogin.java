package todo;

import database.Store;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckLogin {
    static Store obj = Store.getInstance();

    protected static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[]  cookie = request.getCookies();

        System.out.println(" sjkbdhbjsvbhjnz ");
        try {
            System.out.println(" IN trying;");
            for (Cookie cur : cookie) {
                System.out.println("In cookies");
                if (cur.getName().equals("session-id")) {
                    System.out.println("Found session-id " + cur.getValue());
                    if (obj.getLoginAccounts().containsKey(cur.getValue()))
                        return true;
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        return false;
    }

    protected static String getUserFromSessionId(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[]  cookie = request.getCookies();

        try {
            for (Cookie cur : cookie) {
                if (cur.getName().equals("session-id")) {
                    if (obj.getLoginAccounts().containsKey(cur.getValue()))
                        return obj.getLoginAccounts().get(cur.getValue());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}

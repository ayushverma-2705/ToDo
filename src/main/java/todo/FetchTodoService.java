package todo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import database.Store;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FetchTodoService {
    static Store obj = Store.getInstance();

    public static void notLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("Content-Type", "text/html");
        writer.print("<a href=\"/ToDo\">Login/Register before :)</a>");

        writer.flush();
        writer.close();
    }


    public static void fetchIT(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        System.out.println(" In Fetch It");
        if (CheckLogin.checkLogin(request, response)) {
            PrintWriter writer = response.getWriter();
            System.out.println(" LOGGED IN !!!");

            response.setContentType("application/json");

            String req_date = request.getParameter("Date");


            JsonArray pendingArray;
            JsonArray progressArray;
            JsonArray completedArray;
            JsonArray deleteArray;

            if (!(req_date.equals("undefined"))) {
                DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);  //change the format

                Date date = null;


                System.out.println(" Date is Valid and it is " + req_date);

                try {
                    date = format.parse(req_date);
                    // todos = store.getForAUser(date);
                } catch (ParseException e) {

                    e.printStackTrace();
                }


                HashSet<Integer> set = new HashSet<Integer>();

                for (Map.Entry<Date, Integer > entry : obj.trackRecord.entrySet())
                {

                    if((entry.getKey()).compareTo(date) > 0) {
                        //    System.out.println(entry.getKey() + "  dd  " + date + "   d jn  " + (entry.getKey()).compareTo(date));
                        System.out.println(" Getting inside for dates " + entry.getKey().toString() + " todo id value that is updated is " + entry.getValue());
                        set.add(entry.getValue());
                    }
                }

                System.out.println("Printing Set " + set.toString());

                ArrayList<Todo> UpPending = new ArrayList<Todo>();
                ArrayList<Todo> UpProgess = new ArrayList<Todo>();
                ArrayList<Todo> UpComplete = new ArrayList<Todo>();
                ArrayList<Integer> UpDelete = new ArrayList<Integer>();

                for (Integer tid : set) {
                    if (obj.foundInPending(tid)) {
                        System.out.println(tid + " Found in pending");
                        UpPending.add(obj.fromPending(tid));
                    } else if (obj.foundInProgress(tid)) {
                        System.out.println(tid + " Found in progress");
                        UpProgess.add(obj.fromProgress(tid));
                    } else if (obj.foundInCompleted(tid))  {
                        System.out.println(tid + " Found in complete");
                        UpComplete.add(obj.fromComplete(tid));
                    }
                    else {
                        System.out.println(tid + " Found in delete");
                        UpDelete.add(tid);
                    }
                }

                pendingArray = (JsonArray) new Gson().toJsonTree(UpPending, new TypeToken<List<Todo>>() { }.getType());
                progressArray = (JsonArray) new Gson().toJsonTree(UpProgess, new TypeToken<List<Todo>>() { }.getType());
                completedArray = (JsonArray) new Gson().toJsonTree(UpComplete, new TypeToken<List<Todo>>() { }.getType());
                deleteArray = (JsonArray) new Gson().toJsonTree(UpDelete).getAsJsonArray();

            } else {
                System.out.println(" Date is Undefined");
                pendingArray = (JsonArray) new Gson().toJsonTree(obj.getPendingToDo(), new TypeToken<List<Todo>>() {
                }.getType());
                progressArray = (JsonArray) new Gson().toJsonTree(obj.getProgressToDo(), new TypeToken<List<Todo>>() {
                }.getType());
                completedArray = (JsonArray) new Gson().toJsonTree(obj.getCompletedToDo(), new TypeToken<List<Todo>>() {
                }.getType());

                ArrayList<Integer> UpDelete = new ArrayList<Integer>();
                deleteArray = (JsonArray) new Gson().toJsonTree(UpDelete).getAsJsonArray();
//
//                deleteArray = (JsonArray) new Gson().toJsonTree(UpDelete, new TypeToken<List<Integer>>() { }.getType());

            }

            //   obj.printPendingToDo();

            JSONObject totalList = new JSONObject();

            try {
                totalList.put("pending", pendingArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                totalList.put("progress", progressArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            try {
                totalList.put("completed", completedArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                totalList.put("deleted", deleteArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                totalList.put("Date", (new Date()).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }


            System.out.println(" Printing Pending Array : -- " + pendingArray);
            System.out.println(" Printing Total Json Object :-- " + totalList.toString());
            System.out.println(" Exiting Get Main Controller ");

            writer.write(totalList.toString());
            writer.flush();

            writer.close();
            return;
        } else {
            System.out.println("Login Failed !!!");
            notLoggedIn(request, response);
            //response.sendRedirect("request.getContextPath()");

            return;
        }
    }
}

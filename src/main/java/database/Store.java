package database;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
import todo.Todo;
import user.User;
import todo.Pair;


/**
 * Created by ayush.v on 10/08/17.
 */


public class Store {

    private static Store instance = null;
    private Map<String,String> loginAccounts;
    private List<Todo> pendingToDo;
    private List<Todo> progressToDo;
    private List<Todo> completedToDo;
    private Map<String, User> registeredUsers;

    public Map<Date, Integer> trackRecord;

    private Store(){
        this.loginAccounts = new HashMap<String,String>();
        this.pendingToDo = new ArrayList<Todo>() ;
        this.progressToDo = new ArrayList<Todo>();
        this.completedToDo = new ArrayList<Todo>();
        this.registeredUsers = new HashMap<String, User>();
        this.trackRecord = new HashMap<Date, Integer>();
    }

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }

        return instance;
    }

    public void removeUserLogin(String cookie) {
        loginAccounts.remove(cookie);
    }


    public boolean checkUsername(String username) {
        return registeredUsers.containsKey(username);
    }

    public User getUser(String username) {
        return registeredUsers.get(username);
    }

    public void createTodoItem(Todo item){
        pendingToDo.add(item);
    }

    public void addToTrack(long todoId) {
        trackRecord.put(new Date(), (int)todoId);
    }

    public  Todo deleteInPending(long todoId) {
        Todo current = null;

        System.out.println(" Searching for " + todoId + " in delete in pending");
        int index = -1;
        boolean found = false;

        for (Todo todo : pendingToDo) {
            ++index;
            System.out.println(todo.gettId() + "  ttt  " + todoId);
            if (todo.gettId() == todoId) {
                current = todo;
                found = true;

                System.out.println("Found Current in store");
                break;
            }
        }

        System.out.println("Before Deletion");
        printPendingToDo();

        if (found) {
         //   trackRecord.put(new Date(), new Pair(todoId, 0, 1));
            pendingToDo.remove(index);
        }

        System.out.println("After Deletion");
        printPendingToDo();

        return current;
    }

    public void addInProgress(Todo todo) {
        todo.setStatus(1);
        progressToDo.add(todo);
    }

    public  Todo deleteInProgress(long todoId) {
        Todo current = null;
        int index = -1;

        boolean found = false;

        for (Todo todo : progressToDo) {
            ++index;
            if (todo.gettId() == todoId) {
                current = todo;
                found = true;
                break;
            }
        }

        if (found) {
            progressToDo.remove(index);
        }

        return current;
    }

    public void addinCompleted(Todo todo) {
        todo.setStatus(2);
        completedToDo.add(todo);
    }

    public void printPendingToDo() {
        for (Todo obj : pendingToDo) {
            System.out.println(obj.getTitle() + "  " + obj.getDescription());
        }
    }


    public void addLogin(String cookie, String username) {
        loginAccounts.put(cookie, username);
    }

    public void addRegister(String username, User user) {
        registeredUsers.put(username, user);
    }

    public List<Todo> getPendingToDo() {
        return pendingToDo;
    }

    public void setPendingToDo(List<Todo> pendingToDo) {
        this.pendingToDo = pendingToDo;
    }

    public List<Todo> getProgressToDo() {
        return progressToDo;
    }

    public void setProgressToDo(List<Todo> progressToDo) {
        this.progressToDo = progressToDo;
    }

    public List<Todo> getCompletedToDo() {
        return completedToDo;
    }

    public void setCompletedToDo(List<Todo> completedToDo) {
        this.completedToDo = completedToDo;
    }

    public Map<String, User> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(Map<String, User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public Map<String, String > getLoginAccounts() {
        return loginAccounts;
    }

    public void setLoginAccounts(Map<String, String> loginAccounts) {
        this.loginAccounts = loginAccounts;
    }

    public boolean foundInPending(Integer tid) {
        long Tid = (long) tid;

        for (Todo todo : pendingToDo) {
            if (todo.gettId() == Tid) {
                return true;
            }
        }

        return false;
    }

    public Todo fromPending(Integer tid) {
        long Tid = (long) tid;

        for (Todo todo : pendingToDo) {
            if (todo.gettId() == Tid) {
                return todo;
            }
        }

        return null;
    }

    public boolean foundInProgress(Integer tid) {
        long Tid = (long) tid;

        for (Todo todo : progressToDo) {
            if (todo.gettId() == Tid) {
                return true;
            }
        }

        return false;
    }

    public Todo fromProgress(Integer tid) {
        long Tid = (long) tid;

        for (Todo todo : progressToDo) {
            if (todo.gettId() == Tid) {
                return todo;
            }
        }

        return null;
    }

    public Todo fromComplete(Integer tid) {
        long Tid = (long) tid;

        for (Todo todo : completedToDo) {
            if (todo.gettId() == Tid) {
                return todo;
            }
        }

        return null;
    }
}

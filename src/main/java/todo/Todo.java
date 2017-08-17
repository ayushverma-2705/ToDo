package todo;
import java.util.Date;

/**
 * Created by ayush.v on 10/08/17.
 */

public class Todo {
    private String title;
    private String description;
    private String creatorName;
    private Date creationDate;
    private static long toDoId = 0;
    private long status;
    private long tId;
    private String assignTo;
    private String assignBy;


    public Todo(String title, String description, String creatorName) {
        this.title = title;
        this.description = description;
        this.creatorName = creatorName;
        this.creationDate = new Date();
        ++toDoId;
        this.status = 0;
        this.tId = toDoId;
        assignTo = assignBy = "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String  getTitle() {
        return title;
    }

    public void setTask(String description) {
        this.description = description;
    }

    public String  getDescription() {
        return description;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public static long getToDoId() {
        return toDoId;
    }

    public void setToDoId(long toDoId) {
        this.toDoId = toDoId;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getAssignBy() {
        return assignBy;
    }

    public void setAssignBy(String assignBy) {
        this.assignBy = assignBy;
    }
}
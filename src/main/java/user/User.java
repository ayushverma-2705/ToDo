package user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ayush.v on 10/08/17.
 */

public class User {
    private String userName;
    private String password;
    private String emailId;
    private String phone;


    @JsonCreator
    public User(@JsonProperty("username") String username, @JsonProperty("password") String password,
                @JsonProperty("email") String email, @JsonProperty("phone") String phone){
        this.userName = username;
        this.password = password;
        this.emailId = email;
        this.phone = phone;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

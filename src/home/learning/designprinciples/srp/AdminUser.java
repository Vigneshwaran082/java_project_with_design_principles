package home.learning.designprinciples.srp;

/**
 * Module 3 : Holds only admin[SYSTEM] user info ,
 * used for credential validation or session management in feature
 * or security related concern.
 * */
public class AdminUser {

    private String userName;

    private String password;

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

    @Override
    public String toString() {
        return "AdminUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}

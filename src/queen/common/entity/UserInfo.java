package queen.common.entity;

public class UserInfo {

    private String userId;

    private String username;

    private String password;

    public UserInfo() {
    }

    public UserInfo(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

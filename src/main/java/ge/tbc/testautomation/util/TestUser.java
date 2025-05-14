package ge.tbc.testautomation.util;

public class TestUser {
    private final String email;
    private final String password;

    public TestUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}

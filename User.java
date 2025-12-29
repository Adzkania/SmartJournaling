public class User {
    private String email;
    private String password;
    private String displayName;
    
    public User(String email, String displayName, String password) {
        this.email = email;
        this.displayName = displayName;
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
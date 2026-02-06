package campus.models;

public class Admin implements User {

    private final int id;
    private final String username;

    public Admin(int id, String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }

    public String getUsername() {
        return username;
    }
}

package edu.aitu.oop3.db;

public final class JdbcConfig {

    private static final JdbcConfig INSTANCE = new JdbcConfig();

    private final String url;
    private final String user;
    private final String password;

    private JdbcConfig() {
        this.url = System.getenv("DB_URL");
        this.user = System.getenv("DB_USER");
        this.password = System.getenv("DB_PASS");

        if (url == null || user == null || password == null) {
            throw new IllegalStateException(
                    "DB_URL, DB_USER, DB_PASS must be set as environment variables"
            );
        }
    }

    public static JdbcConfig getInstance() {
        return INSTANCE;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}

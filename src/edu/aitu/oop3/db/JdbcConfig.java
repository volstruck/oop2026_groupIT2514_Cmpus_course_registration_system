package edu.aitu.oop3.db;

public class JdbcConfig {
    private String user;
    private String password;

    public JdbcConfig(
//            String host, String dbname,
            String user, String password){
//        setHost(host);
//        setDbname(dbname);
        setUser(user);
        setPassword(password);
    }

//    public void setDbname(String dbname) {
//        this.dbname = dbname;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser(){
        return this.user;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString(){
        return "jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres";
    }
}

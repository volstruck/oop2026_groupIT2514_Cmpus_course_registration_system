package campus.models;

public class Student {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int courseYear;
    private String faculty;

    public Student(int id, String name, String surname,
                   String email, String password,
                   int courseYear, String faculty) {
        setId(id);
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPassword(password);
        setCourseYear(courseYear);
        setFaculty(faculty);
    }

    public int getId(){ return id;}
    public String getName(){return name;}
    public String getSurname(){return surname;}
    public String getPassword(){return password;}
    public String getEmail(){return email;}
    public int getCourseYear(){return courseYear;}
    public String getFaculty(){return faculty;}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}

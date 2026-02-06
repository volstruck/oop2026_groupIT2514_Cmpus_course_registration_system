package campus.factory;

import campus.models.Admin;
import campus.models.Student;
import campus.models.User;

public class UserFactory {

    private UserFactory() {}

    public static User createStudent(Student s) {
        return s;
    }

    public static User createAdmin(String username) {
        return new Admin(0, username);
    }
}

package campus.Main;

import campus.exceptions.*;
import campus.factory.UserFactory;
import campus.models.*;
import campus.repository.jdbc.*;
import campus.service.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final CourseService courseService =
            new CourseService(
                    new JdbcCourseRepository(),
                    new JdbcEnrollmentRepository()
            );

    private static final StudentService studentService =
            new StudentService(new JdbcStudentRepository());

    private static final EnrollmentService enrollmentService =
            new EnrollmentService(
                    new JdbcEnrollmentRepository(),
                    new JdbcCourseRepository()
            );

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== CAMPUS SYSTEM ===");
            System.out.println("1. Student login");
            System.out.println("2. Student registration");
            System.out.println("3. Admin");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> registerStudent();
                case 3 -> adminMenu();
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // ================= STUDENT =================

    private static void studentMenu() {

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Student student;
        try {
            student = studentService.login(email, password);
        } catch (IllegalStateException e) {
            System.out.println("Login failed: " + e.getMessage());
            return; // go back to main menu
        }

        int studentId = student.getId();
        User user = UserFactory.createStudent(student);

        while (true) {
            System.out.println("\n--- STUDENT MENU ---");
            System.out.println("1. View courses");
            System.out.println("2. Enroll in course");
            System.out.println("3. Drop course");
            System.out.println("4. My enrollments");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> listCourses();
                case 2 -> enroll(studentId);
                case 3 -> drop(studentId);
                case 4 -> listMyEnrollments(studentId);
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void registerStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Surname: ");
        String surname = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Course year: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Faculty: ");
        String faculty = sc.nextLine();

        studentService.createStudent(
                new Student(0, name, surname, email, password, year, faculty)
        );

        System.out.println("Registration successful.");
    }

    private static void enroll(int studentId) {
        try {
            System.out.print("Course ID: ");
            int courseId = Integer.parseInt(sc.nextLine());

            enrollmentService.enroll(studentId, courseId);
            System.out.println("Enrollment successful.");

        } catch (CapacityExceededException |
                 ScheduleConflictException |
                 EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void drop(int studentId) {
        try {
            System.out.print("Course ID: ");
            int courseId = Integer.parseInt(sc.nextLine());

            enrollmentService.drop(studentId, courseId);
            System.out.println("Dropped.");

        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listMyEnrollments(int studentId) {
        List<Enrollment> list =
                enrollmentService.getEnrollmentsByStudent(studentId);

        if (list.isEmpty()) {
            System.out.println("No enrollments.");
            return;
        }

        list.forEach(e ->
                System.out.println("Course ID: " + e.getCourseId()));
    }

    // ================= ADMIN =================

    private static void adminMenu() {
        if (!adminLogin()) {
            System.out.println("Access denied.");
            return;
        }

        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Create course");
            System.out.println("2. Delete course");
            System.out.println("3. List courses");
            System.out.println("4. View course enrollments");
            System.out.println("5. List students");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> createCourse();
                case 2 -> deleteCourse();
                case 3 -> listCourses();
                case 4 -> listCourseEnrollments();
                case 5 -> listStudents();
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static boolean adminLogin() {
        System.out.print("Admin username: ");
        String username = sc.nextLine();

        System.out.print("Admin password: ");
        String password = sc.nextLine();

        if (username.equals("admin") && password.equals("admin123")) {
            User admin = UserFactory.createAdmin(username);
            return true;
        }
        return false;
    }

    private static void createCourse() {
        System.out.print("Course name: ");
        String name = sc.nextLine();

        System.out.print("Instructor: ");
        String instructor = sc.nextLine();

        System.out.print("Credits: ");
        int credits = Integer.parseInt(sc.nextLine());

        System.out.print("Weeks: ");
        int weeks = Integer.parseInt(sc.nextLine());

        System.out.print("Max capacity: ");
        int cap = Integer.parseInt(sc.nextLine());

        System.out.print("Faculties (comma separated): ");
        List<String> faculties = List.of(sc.nextLine().split(","));

        System.out.print("Day (1–7): ");
        int day = Integer.parseInt(sc.nextLine());

        System.out.print("Start time (HH:mm): ");
        LocalTime start = LocalTime.parse(sc.nextLine());

        System.out.print("End time (HH:mm): ");
        LocalTime end = LocalTime.parse(sc.nextLine());

        Course course = new CourseBuilder()
                .name(name)
                .instructor(instructor)
                .credits(credits)
                .weeks(weeks)
                .maxCapacity(cap)
                .faculties(faculties)
                .schedule(start, end, day)
                .build();

        courseService.createCourse(course);

        System.out.println("Course created.");
    }

    private static void deleteCourse() {
        try {
            System.out.print("Course ID: ");
            int id = Integer.parseInt(sc.nextLine());

            courseService.deleteCourse(id);
            System.out.println("Course deleted.");

        } catch (CourseHasEnrollmentsException |
                 EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listCourses() {
        courseService.getAllCourses().forEach(c ->
                System.out.printf("%d | %s | %s%n",
                        c.getId(), c.getName(), c.getInstructorName()));
    }

    private static void listCourseEnrollments() {
        System.out.print("Course ID: ");
        int courseId = Integer.parseInt(sc.nextLine());

        List<Enrollment> list =
                enrollmentService.getEnrollmentsByCourse(courseId);

        if (list.isEmpty()) {
            System.out.println("No enrollments.");
            return;
        }

        list.forEach(e ->
                System.out.println("Student ID: " + e.getStudentId()));
    }

    private static void listStudents() {
        studentService.getAllStudents().forEach(s ->
                System.out.printf("%d | %s %s%n",
                        s.getId(), s.getName(), s.getSurname()));
    }
}

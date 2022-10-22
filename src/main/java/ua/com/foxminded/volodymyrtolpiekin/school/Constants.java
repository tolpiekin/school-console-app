package ua.com.foxminded.volodymyrtolpiekin.school;

public final class Constants {
    public static final String DB_URL = "url";
    public static final String DB_USER = "dbuser";
    public static final String DB_DRIVER = "driver";
    public static final String DB_PASSWORD = "dbpassword";
    public static final String SQL_COURSES_FIND_BY_NAME = "select * from courses where course_name = ?";
    public static final String SQL_COURSES_FIND_BY_ID = "select * from courses where course_id = ?";
    public static final String SQL_COURSES_GET_ALL = "select * from courses";
    public static final String SQL_COURSES_INSERT = "insert into courses (course_id, course_name, course_description) " +
            "values (?,?,?)";
    public static final String SQL_COURSES_UPDATE = "update courses set course_name = ?, course_description = ? where " +
            "course_id = ?";
    public static final String SQL_COURSES_DELETE = "delete from courses where course_id = ?";
    public static final String SQL_COURSES_GET_STUDENTS_BY_COURSE =
            "SELECT  students.first_name, students.last_name, courses.course_name\n" +
                    "FROM students\n" +
                    "INNER JOIN course_attendance\n" +
                    "ON students.student_id = course_attendance.student_id\n" +
                    "INNER JOIN courses \n" +
                    "ON course_attendance.course_id = courses.course_id\n" +
                    "AND courses.course_name='%s';";
    public static final String SQL_COURSES_ADD_STUDENT_TO_COURSE =
            "INSERT INTO course_attendance(student_id, course_id) VALUES(?, ?)";
    public static final String SQL_COURSES_REMOVE_STUDENT_FROM_COURSE =
            "DELETE FROM course_attendance WHERE student_id=%d AND course_id=%d";
    public static final String SQL_COURSES_IF_STUDENT_AT_COURSE =
            "SELECT * FROM course_attendance WHERE student_id=? AND course_id=?";
    public static final String SQL_GROUPS_FIND_BY_NAME = "select * from groups where group_name = ?";
    public static final String SQL_GROUPS_FIND_BY_ID = "select * from groups where group_id = ?";
    public static final String SQL_GROUPS_GET_ALL = "select * from groups";
    public static final String SQL_GROUPS_INSERT = "insert into groups (group_id, group_name) values (?,?)";
    public static final String SQL_GROUPS_UPDATE = "update groups set group_name = ? where group_id = ?";
    public static final String SQL_GROUPS_DELETE = "delete from groups where group_id = ?";
    public static final String SQL_GROUPS_LESS_THEN =
            "SELECT groups.group_name, count(students.group_id) " +
                    "FROM groups " +
                    "INNER JOIN students " +
                    "ON students.group_id = groups.group_id " +
                    "GROUP BY groups.group_name " +
                    "HAVING count(students.group_id) <= %d";
    public static final String SQL_STUDENTS_FIND_BY_LAST_NAME = "select * from students where last_name = ?";
    public static final String SQL_STUDENTS_FIND_BY_ID = "select * from students where student_id = ?";
    public static final String SQL_STUDENTS_GET_ALL = "select * from students";
    public static final String SQL_STUDENTS_INSERT = "insert into students (student_id, group_id, first_name, " +
            "last_name) values (?,?,?,?)";
    public static final String SQL_STUDENTS_UPDATE = "update students set group_id = ?, first_name = ?, " +
            "last_name = ? where student_id = ?";
    public static final String SQL_STUDENTS_DELETE = "delete from students where student_id = ?";


    private Constants() {

    }
}

package ua.com.foxminded.volodymyrtolpiekin.school;

public final class MenuConstants  {
    public static final int EXIT = 0;
    public static final int GROUP_SMALLER_THAN = 1;
    public static final int COURSE_ATTENDANCE = 2;
    public static final int ADD_STUDENT = 3;
    public static final int DEL_STUDENT = 4;
    public static final int ADD_STUDENT_TO_COURSE = 5;
    public static final int DEL_STUDENT_FROM_COURSE = 6;
    public static final String MAIN_TITLE = "Main Menu for Student Data. Make your choice:\n\n";
    public static final String GROUP_SMALLER_THAN_TITLE = "1. Find all groups with less or equals student count\n";
    public static final String COURSE_ATTENDANCE_TITLE = "2. Find all students related to course with given name\n";
    public static final String ADD_STUDENT_TITLE = "3. Add new student\n";
    public static final String DEL_STUDENT_TITLE = "4. Delete student by STUDENT_ID\n";
    public static final String ADD_STUD_TO_COURSE_TITLE = "5. Add a student to the course (from a list)\n";
    public static final String DEL_STUD_FROM_COURSE_TITLE = "6. Remove the student from one of his or her courses\n";
    public static final String EXIT_TITLE = "\n0. Exit\n";
    public static final String ENTER_YOUR_CHOICE = "Enter your choice (the number) : ";
    public static final String MAIN_MENU = MAIN_TITLE + GROUP_SMALLER_THAN_TITLE+ COURSE_ATTENDANCE_TITLE
            + ADD_STUDENT_TITLE + DEL_STUDENT_TITLE + ADD_STUD_TO_COURSE_TITLE + DEL_STUD_FROM_COURSE_TITLE
            + EXIT_TITLE;
    public static final String GROUP_SMALLER_THAN_REQUEST = "Enter max number of students per group : ";
    public static final String COURSE_NAME_REQUEST = "Enter course name : ";
    public static final String GROUP_ID_REQUEST = "Enter group id for new student :";
    public static final String STUDENT_ID_REQUEST = "Enter student id :";
    public static final String STUDENT_OUTPUT_TEMPLATE = "%3d. %s %s";
    public static final String FIRST_NAME_REQUEST = "Enter student's first name : ";
    public static final String LAST_NAME_REQUEST = "Enter student's last name : ";
    public static final String ADD_STUDENT_CONFIRMATION = "Student %s %s added with the id: %d %n";
    public static final String DEL_STUDENT_CONFIRMATION = "Record for student with id: %d, deleted successfully...%n";
    public static final String ADD_STUDENT_TO_COURSE_CONFIRMATION = "Student %d added to course %d ...";
    public static final String COURSE_ID_REQUEST = "Enter course id :";
    public static final String DEL_FROM_COURSE_CONFIRMATION = "Record deleted successfully...";
    public static final String PRESS_ENTER_TO_CONTINUE = "Press Enter key to continue.";
    public static final String COURSE_LIST_TEMPLATE = "%2d. %s";
    public static final String STUDENT_NOT_ATTENDING_ANY_COURSE = "Student not attending any course.";

    private MenuConstants(){}
}

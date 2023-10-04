package Lab1.Managers;

import Lab1.models.Faculty;
import Lab1.models.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Logger {
    private static final String PATH = "C:\\IT Roma\\OOP Labs\\Labs\\src\\Lab1\\Logs\\";
    private static final String LOG_FILE_PATH = PATH + "log.txt";
    private final String username;

    public Logger(String username) {
        this.username = username;
    }
    
    private String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return formatter.format(currentTime);
    }

    public void saveCreateFaculty(Faculty faculty) {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  GENERAL_OPERATION  : created_faculty  :  " + faculty + "\n");
            logFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAddStudent(Student student) {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  FACULTY_OPERATION  : created_student  :  " + student + "\n");
            logFile.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGraduateStudent(Student student) {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  FACULTY_OPERATION  : graduated_student  :  " + student + "\n");
            logFile.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void saveResetDatabase() {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  DATABASE_OPERATION  : reset_data  :  emptied faculty.txt\n");
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  DATABASE_OPERATION  : reset_data  :  deleted existing faculties\n");
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  DATABASE_OPERATION  : reset_data  :  deleted existing students\n");
            logFile.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void saveStoreDatabase() {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  DATABASE_OPERATION  : save_data  :  saved current state of university\n");
            logFile.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBatchEnrollment() {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  BATCH_OPERATION  : load_data  :  loaded available students for enrollment\n");
            logFile.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBatchGraduation() {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  BATCH_OPERATION  : load_data  :  loaded available students for graduation\n");
            logFile.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void resetLog() {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveResetLog() {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  LOG_OPERATION  :  reset_logs  :  emptied logs file\n");
            logFile.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBatchFail(int i) {
        if (i == 1) {
            try {
                FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
                logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  BATCH_OPERATION  : load_data  :  failed to batch the enrollment for students\n");
                logFile.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
                logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  BATCH_OPERATION  : load_data  :  failed to batch the graduation for students\n");
                logFile.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void saveAddStudentDuplicate() {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  BATCH_OPERATION  : load_data  :  student duplicate detected\n");
            logFile.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFacultyForStudent() {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  BATCH_OPERATION  : load_data  :  faculty not found for student\n");
            logFile.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void saveEntry() {
        try {
            FileWriter logFile = new FileWriter(LOG_FILE_PATH, true);
            logFile.write(getCurrentTime() + "  :  " + "USER_LOGGED  :  " + this.username + "  :  INITIATION  : entry  :  app initialized\n");
            logFile.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}

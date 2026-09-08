import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static Logger instance;
    private BufferedWriter writer;
    private String fileName;

    private Logger() {
        fileName = "default_log.txt";
        openFile();
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private void openFile() {
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.out.println("Error opening log file: " + e.getMessage());
        }
    }

    public void setFileName(String fileName) {
        close();
        this.fileName = fileName;
        openFile();
    }

    public void write(String message) {
        try {
            if (writer != null) {
                writer.write(message);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (writer != null) {
                writer.close();
                writer = null;
            }
        } catch (IOException e) {
            System.out.println("Error closing log file: " + e.getMessage());
        }
    }
}

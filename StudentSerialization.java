import java.io.*;

// Student class implementing Serializable
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // Method to display student details
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", GPA: " + gpa);
    }
}

public class StudentSerialization {
    private static final String FILE_NAME = "student.ser";

    // Method to serialize a Student object
    public static void serializeStudent(Student student) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(student);
            System.out.println("Student serialized successfully.");
        } catch (IOException e) {
            System.err.println("Error serializing student: " + e.getMessage());
        }
    }

    // Method to deserialize a Student object
    public static Student deserializeStudent() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Student) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing student: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        // Creating a Student object
        Student student = new Student(1, "John Doe", 3.8);

        // Serializing the Student object
        serializeStudent(student);

        // Deserializing the Student object
        Student deserializedStudent = deserializeStudent();
        if (deserializedStudent != null) {
            System.out.println("Deserialized Student:");
            deserializedStudent.display();
        }
    }
}

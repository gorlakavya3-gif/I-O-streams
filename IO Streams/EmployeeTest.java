import java.io.*;
import java.util.Date;

public class EmployeeTest {

    public static void main(String[] args) {

        try {
            // Create Employee object
            Employee emp = new Employee(
                    "Kavya",
                    new Date(),
                    "IT",
                    "Software Engineer",
                    50000.0);

            // Serialization
            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream("data"));
            oos.writeObject(emp);
            oos.close();

            System.out.println("Employee object serialized successfully.");

            // Deserialization
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("data"));

            Employee e = (Employee) ois.readObject();
            ois.close();

            // Display details
            System.out.println("\nEmployee Details:");
            System.out.println("Name        : " + e.getName());
            System.out.println("Date of Birth : " + e.getDateOfBirth());
            System.out.println("Department  : " + e.getDepartment());
            System.out.println("Designation : " + e.getDesignation());
            System.out.println("Salary      : " + e.getSalary());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
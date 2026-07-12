import java.io.*;
import java.util.*;

public class EmployeeManagement {

    static final String FILE_NAME = "employee.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {

            System.out.println("\nMain Menu");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addEmployee(sc);
                    break;

                case 2:
                    displayEmployees();
                    break;

                case 3:
                    System.out.println("Thank You...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    // Add Employee
    static void addEmployee(Scanner sc) {

        try {

            System.out.print("Enter Employee ID : ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Employee Name : ");
            String name = sc.nextLine();

            System.out.print("Enter Employee Age : ");
            int age = sc.nextInt();

            System.out.print("Enter Employee Salary : ");
            double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, age, salary);

            File file = new File(FILE_NAME);

            ObjectOutputStream oos;

            if (file.exists()) {

                oos = new AppendableObjectOutputStream(
                        new FileOutputStream(file, true));

            } else {

                oos = new ObjectOutputStream(
                        new FileOutputStream(file));

            }

            oos.writeObject(emp);
            oos.close();

            System.out.println("Employee Added Successfully.");

        }

        catch (Exception e) {

            System.out.println(e);

        }

    }

    // Display Employees
    static void displayEmployees() {

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(FILE_NAME));

            System.out.println("\n------ Report ------");

            while (true) {

                Employee emp = (Employee) ois.readObject();

                System.out.println(
                        emp.getId() + " " +
                        emp.getName() + " " +
                        emp.getAge() + " " +
                        emp.getSalary());

            }

        }

        catch (EOFException e) {

            System.out.println("------ End of Report ------");

        }

        catch (Exception e) {

            System.out.println("No Employee Records Found.");

        }

    }
}
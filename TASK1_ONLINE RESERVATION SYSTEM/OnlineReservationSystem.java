import java.io.*;
import java.text.*;
import java.util.*;

public class OnlineReservationSystem {
    private static String currentUser = null;
    private static List<User> users = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        loadUsers();
        loadReservations();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Online Reservation System");
            System.out.println("1. Login");
            System.out.println("2. Make a reservation");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    cancelReservation(scanner);
                    break;
                case 4:
                    saveReservations();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private static void loadUsers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                User user = new User(parts[0], parts[1]);
                users.add(user);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    private static void loadReservations() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Date date = new Date(Long.parseLong(parts[2]));
                Reservation reservation = new Reservation(parts[0], Integer.parseInt(parts[1]), date);
                reservations.add(reservation);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading reservations: " + e.getMessage());
        }
    }

    private static void saveReservations() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("reservations.txt"));
            for (Reservation reservation : reservations) {
                writer.println(reservation.getName() + "," + reservation.getNumberOfGuests() + ","
                        + reservation.getDate().getTime());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = username;
                System.out.println("Login successful");
                return;
            }
        }
        System.out.println("Invalid username or password");
    }

    private static void makeReservation(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("You must login first");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter number of guests: ");
        int numberOfGuests = scanner.nextInt();
        System.out.print("Enter date (mm/dd/yyyy): ");
        String dateString = scanner.next();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date date = dateFormat.parse(dateString);
            Reservation reservation = new Reservation(name, numberOfGuests, date);
            reservations.add(reservation);
            System.out.println("Reservation made for " + name);
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
    }

    private static void cancelReservation(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("You must login first");
            return;
        }
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter date (mm/dd/yyyy): ");
        String dateString = scanner.next();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date date = dateFormat.parse(dateString);
            boolean found = false;
            for (Reservation reservation : reservations) {
                if (reservation.getName().equals(name) && reservation.getDate().equals(date)) {
                    reservations.remove(reservation);
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println("Reservation cancelled for " + name);
            } else {
                System.out.println("Reservation not found");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
    }
}

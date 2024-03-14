import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Network1{
    private static int currentScreen = 0;
    private static  java.sql.Connection con;
    private static int userId;
    private static String userName;

    public static void main(String[] args) throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network1";
        con = java.sql.DriverManager.getConnection( host );
        Scanner sc = new Scanner(System.in);
        int option;
        while (true) {
            printMenu();
            option = getOption();
            if (option == 0) {
                break;
            }
            if (currentScreen == 0) {
                switch (option) {
                    case 2: login(); break;
                    case 3: register(); break;
                }
            } else {
                switch (option) {
                    case 6: logout(); break;
                }
            }
        }
    }
    private static int getOption() {
        Scanner sc = new Scanner(System.in);
        int option = -4;
        try {
            option = Integer.parseInt(sc.next());
            if ((currentScreen == 0 && option > 3) || (currentScreen == 1 && option > 6)) {
                System.out.println("Incorrect option");
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("Incorrect option");
        }
        return option;
    }
    private static void printMenu() {
        System.out.println("-------------------------------------------------------------------------------------------------");
        if (currentScreen == 0) {
            System.out.println("0 Exit | 1 All posts | 2 Log in ! 3 Register");
        } else {
            System.out.println("0 Exit | 1 My posts | 2 New post | 3 New comment | 4 Like | 5 Other Posts | 6 Logout " + userName);
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
    }
    private static void login() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Name:");
        String name = sc.nextLine();
        PreparedStatement st = null;
        String query= "Select* From usuarios where nombre = ?";
        st = con.prepareStatement(query);
        st.setString(1,name);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            userId = rs.getInt("id");
            userName = rs.getString("nombre");
            currentScreen = 1;
        } else {
            System.out.println("Usuer not found");
        }
    }
    private static void register() {
        currentScreen = 1;
    }
    private static void logout() {
        currentScreen = 0;
    }
}


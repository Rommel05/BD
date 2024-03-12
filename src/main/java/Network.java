import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Network {
    private static  java.sql.Connection con;
    public static void main(String[] args) throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network1";
        con = java.sql.DriverManager.getConnection( host );
        menu();
    }
    private static void menu() throws SQLException{
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("0 salir || 1 login");
            int opcion = sc.nextInt();

            if (opcion == 0) {
                break;
            } else if (opcion == 1) {
                log();
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private static void log() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre de usuario:");
        String nombre = sc.nextLine();
        System.out.println("Ingrese la contraseña:");
        String contraseña = sc.nextLine();
        PreparedStatement st = null;
        String query = "select * from usuarios where nombre = ? and contraseña = ?";
        st = con.prepareStatement(query);
        st.setString(1,nombre);
        st.setString(2,contraseña);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            System.out.println("Se inició la sesión exitosamente");
            opcionesDentroUsuario();
        } else {
            System.out.println("No se pudo iniciar la sesión");
        }
    }

    private static void opcionesDentroUsuario() {

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println(" 0 Salir || 1 post || 2 comentario ");
            int opcion = sc.nextInt();
            if (opcion == 0) {
                break;
            } else if (opcion == 1) {
                posts();
            } else if (opcion == 2) {
                comentarios();
            } else {
                System.out.println("Opción inválida.");
            }

        }
    }

    private static void posts() {

    }

    private static void comentarios() {

    }
}

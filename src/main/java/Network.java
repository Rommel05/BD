import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Network {
    private static  java.sql.Connection con;
    public static void main(String[] args) throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network1";
        con = java.sql.DriverManager.getConnection( host );
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce una de las opciones:");
        System.out.println("1 Para crear usuario" + "\n" + "2 para borrar usuario" + "\n" + "3 para crear posts" + "\n" + "4 para eliminar posts" + "\n" +  "5 para añadir comentarios" + "\n" + "6 para eliminar comentarios" + "\n" + "0 para finalizar");
        while (true) {
            int num = sc.nextInt();
            if (num == 0) {
                break;
            }
            if (num == 1) {
                crearUsuarios();
                System.out.println("Introduce otra opción: ");
            }
            if (num == 2) {
                borrarUsuarios();
                System.out.println("Introduce otra opción: ");
            }

        }
    }
    private static void crearUsuarios() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre");
        String nombre = sc.nextLine();
        System.out.println("Introduce el apellido");
        String apellido = sc.nextLine();
        PreparedStatement st = null;
        String sql = "INSERT INTO usuarios (nombre, apellidos) values (?, ?)";
        st = con.prepareStatement(sql);
        st.setString(1,nombre);
        st.setString(2,apellido);
        st.executeUpdate();
    }

    private static void borrarUsuarios() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Statement st = con.createStatement();
        System.out.println("Introduce el nombre");
        String nombre = sc.nextLine();
        String sql = "DELETE FROM usuarios WHERE nombre = " + nombre;
    }
}

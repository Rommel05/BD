import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Network {
    private static  java.sql.Connection con;
    public static void main(String[] args) throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network1";
        con = java.sql.DriverManager.getConnection( host );
        System.out.println("1 Para crear usuario" + "\n" + "2 para borrar usuario" + "\n" + "3 para crear posts" + "\n" + "4 para eliminar posts" + "\n" +  "5 para añadir comentarios" + "\n" + "6 para eliminar comentarios");
    }
    private static void crearUsuarios(int id, String nombre, String apellido) throws SQLException{
        PreparedStatement st = null;
        String sql = "INSERT INTO usuarios (id, nombre, apellidos) values (?, ?, ?)";

    }
}

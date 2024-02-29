import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static  java.sql.Connection con;
    public static void main(String[] args) throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network";
        con = java.sql.DriverManager.getConnection( host );
        //crearTabla();
        //usuarios();
        insaertarUsuarios(3,"Rommel","Romero");
        insaertarUsuarios(4,"Maria","Gonzales");
    }
    private static void crearTabla() throws SQLException{
        Statement st = con.createStatement();
        st.executeUpdate("CREATE TABLE T1 (c1 VARCHAR(50))");
        st.close();
    }
    private static void usuarios() throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM usuarios");

        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t");
            System.out.println(rs.getString(2) + "\t");
            System.out.println(rs.getString(3));
        }
        st.close();
        rs.close();
    }
    private static void insaertarUsuarios(int id, String nombre, String apellidos) throws SQLException{
        Statement st = con.createStatement();
        String sql = "INSERT INTO USUarios (id, nombre, apellidos) VALUES ('" + id + "', '" + nombre + "', '" + apellidos + "')";
        st.executeUpdate(sql);
    }
}

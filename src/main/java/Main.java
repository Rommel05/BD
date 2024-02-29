import java.sql.PreparedStatement;
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
        //insaertarUsuarios(3,"Rommel","Romero");
        //insaertarUsuarios(4,"Maria","Gonzales");
        //crearTablaEmp();
        //instertarEmpleados();
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
    private static void crearTablaEmp() throws SQLException{
        Statement st = con.createStatement();
        st.executeUpdate("CREATE TABLE empleados (" + " num INTEGER PRIMARY KEY, " + "nombre VARCHAR(255), " + "departamento INTEGER, " + "edad INTEGER, " + "sueldo REAL);");
    }
    private static void instertarEmpleados() throws SQLException {
        //PreparedStatement para crear tablas
        PreparedStatement st = null;
        String sql = "INSERT INTO empleados (num, nombre, departamento, edad, sueldo) VALUES (?, ?, ?, ?, ?)";
        st = con.prepareStatement(sql);
        st.setInt(1,1);
        st.setString(2,"Andreu");
        st.setInt(3, 10);
        st.setInt(4,32);
        st.setDouble(5,1000.0);
        st.executeUpdate();

        st.setInt(1,2);
        st.setString(2,"Bernat");
        st.setInt(3, 20);
        st.setInt(4,28);
        st.setDouble(5,1200.0);
        st.executeUpdate();

        st.setInt(1,3);
        st.setString(2,"Claudia");
        st.setInt(3, 10);
        st.setInt(4,26);
        st.setDouble(5,1400.0);
        st.executeUpdate();

        st.setInt(1,4);
        st.setString(2,"Damián");
        st.setInt(3, 10);
        st.setInt(4,40);
        st.setDouble(5,1300.0);
        st.executeUpdate();
    }
}

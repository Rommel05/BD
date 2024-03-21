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
                    case 1: allPost(); break;
                    case 2: login(); break;
                    case 3: register(); break;
                }
            } else {
                switch (option) {
                    case 6: logout(); break;
                    case 1: MyPost(); break;
                    case 5: OtherPost(); break;
                    case 2: newPost(); break;
                    case 3: newComment(); break;
                    case 4: like(); break;
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
            System.out.println("0 Exit | 1 My posts | 2 New post | 3 New comment | 4 Like | 5 Other's Posts | 6 Logout "  + userName);
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
    private static void register() throws SQLException {
        Scanner sc = new Scanner(System.in);
        PreparedStatement st = null;
        System.out.println("name:");
        String name = sc.nextLine();
        System.out.println("Contraseña:");
        String contraseña = sc.nextLine();
        String query = "insert into usuarios (nombre, contraseña) values (?, ?)";
        st = con.prepareStatement(query);
        st.setString(1,name);
        st.setString(2,contraseña);
        st.executeUpdate();
        currentScreen = 1;
    }
    private static void logout() {
        currentScreen = 0;
    }
    private static void allPost() throws SQLException{
        PreparedStatement st = null;
        String query= "Select u.nombre, p.id ,p.texto, p.likes, p.fecha From posts p, usuarios u where p.id_usuario = u.id";
        st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("nombre") + " - " + rs.getInt("id") + " - " + rs.getString("texto") + " - " + rs.getInt("likes") + " - " +rs.getDate("fecha"));
        }
    }

    private static void MyPost() throws SQLException{
        PreparedStatement st = null;
        String query= "Select u.nombre, p.id ,p.texto, p.likes, p.fecha From posts p, usuarios u where p.id_usuario = u.id and id_usuario = ?";
        st = con.prepareStatement(query);
        st.setInt(1, userId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("nombre") + " - " + rs.getInt("id") + " - " + rs.getString("texto") + " - " + rs.getInt("likes") + " - " +rs.getDate("fecha"));
        }
    }

    private static void OtherPost() throws SQLException{
        PreparedStatement st = null;
        String query= "Select u.nombre, p.id ,p.texto, p.likes, p.fecha From posts p, usuarios u where p.id_usuario = u.id and id_usuario != ? ";
        st = con.prepareStatement(query);
        st.setInt(1, userId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("nombre") + " - " + rs.getInt("id") + " - " + rs.getString("texto") + " - " + rs.getInt("likes") + " - " +rs.getDate("fecha"));
        }
    }

    private static void newPost() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Post: ");
        String post = sc.nextLine();
        PreparedStatement st = null;
        String query = "insert into posts (texto, id_usuario) values (?, ?)";
        st = con.prepareStatement(query);
        st.setString(1,post);
        st.setInt(2,userId);
        st.executeUpdate();
        currentScreen = 1;
    }

    private static void newComment() throws SQLException{
        Scanner sc = new Scanner(System.in);
        int postid;
        String comment;
        OtherPost();
        System.out.println("Post id:");
        postid = Integer.parseInt(sc.nextLine());
        System.out.println("Comment: ");
        comment = sc.nextLine();

        PreparedStatement st = null;
        String query = "insert into comentarios (texto, id_usuario, id_post) values (?, ?, ?)";
        st = con.prepareStatement(query);
        st.setString(1,comment);
        st.setInt(2,userId);
        st.setInt(3,postid);
        st.executeUpdate();
    }

    private static void like() throws SQLException{
        Scanner sc = new Scanner(System.in);
        int postid;
        OtherPost();
        System.out.println("Post id:");
        postid = Integer.parseInt(sc.nextLine());

        PreparedStatement st = null;
        String query = "update posts set likes = likes + 1 where id = ?";
        st = con.prepareStatement(query);
        st.setInt(1,postid);
        st.executeUpdate();
    }
}


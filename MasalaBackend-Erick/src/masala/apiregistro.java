package masala;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/registro")
public class apiregistro extends HttpServlet {

    private final String URL = "jdbc:mysql://localhost:3306/masala_db";
    private final String USER = "root";
    private final String PASS = "Erick143"; // tu clave real de MySQL

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            out.print("Datos incompletos");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            // Verificar si el usuario ya existe
            String checkSql = "SELECT * FROM usuarios WHERE email = ?";
            PreparedStatement checkSt = con.prepareStatement(checkSql);
            checkSt.setString(1, email);
            ResultSet rs = checkSt.executeQuery();

            if (rs.next()) {
                out.print("El correo ya está registrado");
                con.close();
                return;
            }

            // Insertar nuevo usuario
            String insertSql = "INSERT INTO usuarios (email, password) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(insertSql);
            pst.setString(1, email);
            pst.setString(2, password);
            pst.executeUpdate();

            con.close();
            out.print("Registro correcto");

        } catch (Exception e) {
            e.printStackTrace();
            out.print("Error en el servidor");
        }
    }
}

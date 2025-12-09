package masala;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="apilogin", urlPatterns={"/api/login"})
public class apilogin extends HttpServlet {

    private final String URL = "jdbc:mysql://localhost:3306/masala_db";
    private final String USER = "root";
    private final String PASS = "Erick143"; 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "SELECT * FROM usuarios WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.print("Login correcto");
            } else {
                out.print("Credenciales incorrectas");
            }

            con.close();

        } catch (Exception e) {
            out.print("Error en el servidor");
            e.printStackTrace();
        }
    }
}

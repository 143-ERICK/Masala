package masala;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
@WebServlet(name="apiproductos",urlPatterns={"/api/productos"})
public class apiproductos extends HttpServlet{
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out=res.getWriter();
        String json="""
        [
          {"id":1,"nombre":"Curry de pollo","precio":7500,"categoria":"Currys","disponible":true},
          {"id":2,"nombre":"Naan de ajo","precio":2100,"categoria":"Naan","disponible":true},
          {"id":3,"nombre":"Lassi de mango","precio":3200,"categoria":"Bebidas","disponible":true}
        ]
        """;
        out.print(json);
        out.flush();
    }
}

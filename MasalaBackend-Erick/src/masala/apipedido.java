package masala;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
@WebServlet(name="apipedido",urlPatterns={"/api/pedido"})
public class apipedido extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("application/json;charset=UTF-8");
        BufferedReader r=req.getReader();StringBuilder sb=new StringBuilder();String l;
        while((l=r.readLine())!=null)sb.append(l);
        String b=sb.toString();boolean tiene=b.contains("direccion");
        PrintWriter out=res.getWriter();
        if(!tiene)out.print("{\"ok\":false,\"message\":\"falta dirección o items\"}");
        else out.print("{\"ok\":true,\"message\":\"pedido registrado\",\"pedido\":{\"id\":1234,\"estado\":\"RECIBIDO\"}}");
        out.flush();
    }
}

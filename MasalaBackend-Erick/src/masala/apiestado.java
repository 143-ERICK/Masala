package masala;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
@WebServlet(name="apiestado",urlPatterns={"/api/estado"})
public class apiestado extends HttpServlet{
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("application/json;charset=UTF-8");
        String id=req.getParameter("pedido");
        if(id==null)id="0";
        String j=String.format("{\"ok\":true,\"pedidoId\":%s,\"estados\":[\"RECIBIDO\",\"PREPARÁNDOSE\",\"LISTO PARA ENTREGA\"]}",id);
        PrintWriter out=res.getWriter();
        out.print(j);
        out.flush();
    }
}

package masala;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
@WebServlet(name="apilogin",urlPatterns={"/api/login"})
public class apilogin extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("application/json;charset=UTF-8");
        BufferedReader r=req.getReader();StringBuilder sb=new StringBuilder();String l;
        while((l=r.readLine())!=null)sb.append(l);
        String b=sb.toString();boolean ok=b.contains("erick@masala.cl")&&b.contains("1234");
        PrintWriter out=res.getWriter();
        if(ok)out.print("{\"ok\":true,\"message\":\"login correcto\",\"token\":\"TOKEN_FAKE_123\"}");
        else out.print("{\"ok\":false,\"message\":\"credenciales inválidas\"}");
        out.flush();
    }
}

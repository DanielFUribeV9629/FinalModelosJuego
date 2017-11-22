package servlets;
import datos.DBJuego;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Juego;


/**
 * Servlet implementation class InsertarUsuarioo
 */
@WebServlet("/InsertarJuego")
public class InsertarJuego extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarJuego() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Juego juego;
        juego= new Juego();
        DBJuego conDb=new DBJuego();
         PrintWriter out = response.getWriter();
         

        try {
                    juego.setNombre(request.getParameter("nombre"));
                    juego.setPegi(Integer.parseInt(request.getParameter("pegi")));
                    juego.setDescripcion(request.getParameter("descripcion"));
                    juego.setPortada(request.getParameter("portada"));

            conDb.añadirJuego(juego);
            out.println("<html>");
            out.println("<head></head>");         
            out.println("<body>");
         
            out.println("<h1>juego registado</h1>"); 
         
         out.println("</body>");
         out.println("</html>");   
        } catch (SQLException ex) {
            out.println("<html>");
         out.println("<head></head>");         
         out.println("<body>");
         
        out.println("<h1>Error insertando en la base de datos</h1>"); 
         
         out.println("</body>");
         out.println("</html>");   
        }
                 
    }

}
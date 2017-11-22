package servlets;
import datos.DBJugador;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Jugador;

/**
 * Servlet implementation class InsertarUsuarioo
 */
@WebServlet("/InsertarUsuario")
public class InsertarUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarUsuario() {
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
        Jugador jugador;
        jugador= new Jugador();
        DBJugador conDb=new DBJugador();
         PrintWriter out = response.getWriter();
         

        try {
                    jugador.setNickname(request.getParameter("nickname"));
                    jugador.setPassword(request.getParameter("pass"));
                    jugador.setNombre(request.getParameter("nombre"));
                    jugador.setApellido(request.getParameter("apellido"));
                    jugador.setE_mail(request.getParameter("email"));
                    jugador.setPais(Integer.parseInt(request.getParameter("pais")));
                    jugador.setAvatar(request.getParameter("avatar"));
                    jugador.setFecha_Nacimiento(request.getParameter("fechaNacimiento"));
            conDb.registrarjugador(jugador);
            out.println("<html>");
            out.println("<head></head>");         
            out.println("<body>");
         
            out.println("<h1>jugador registado</h1>"); 
         
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
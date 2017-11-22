/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import datos.DBJuego;
import datos.DBJugador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Juego;
import logica.Jugador;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "ConsultarJuego", urlPatterns = {"/ConsultarJuego"})
public class ConsultarJuego extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DBJuego conDb = new DBJuego();
        try {
            
            Juego[] juego = conDb.getJuegos();
            int i=0;
            request.getSession().setAttribute("juego",juego);
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Inicio</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> JUEGOS: </h1>");
            while(juego.length>i){
                out.println("<h3> NOMBRE: " + juego[i].getNombre()+ "</h3>");
                out.println("<h4> DESCRIPCION: " + juego[i].getDescripcion()+ "</h4>");
                out.println("<h4> ID: " + juego[i].getId()+ "</h4>");
                out.println("<h4> PEGI: " + juego[i].getPegi()+ "</h4>");
                i++;
            }
            //out.println("<h1> JUGADORES: </h1>");
            //i=0;
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Inicio</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error at " + e.toString() + "</h1>");
            out.println("<p>seguimiento: "+conDb.getMensaje()+"</p>");
            out.println("</body>");
            out.println("</html>");

        }finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
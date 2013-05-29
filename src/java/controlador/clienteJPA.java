/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import ejbsession.EJBSessionJPAFilm;
import ejbsession.FilmFacade;
import entidad.Film;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author java
 */
public class clienteJPA extends HttpServlet {
    
    @EJB
    EJBSessionJPAFilm ejbSesionJPA;
    @EJB
    FilmFacade filmFacade;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
        try {
            String idFilm = request.getParameter("idFilm");
            Film oF = null;
            Film oFilm1 = null;
            if(idFilm != null){
            //Primera forma
            oF = ejbSesionJPA.buscar(Integer.parseInt(idFilm));
            //Segunda forma
            oFilm1 = filmFacade.find(Integer.valueOf(idFilm));            
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet clienteJPA</title>");            
            out.println("</head>");
            out.println("<body>");
            if(oFilm1 != null){
            out.println("<h1>Se obtuvo el Film con nombre: " + oFilm1.getName() + "</h1>");
            out.println("<p>Film: "+oFilm1.toString()+"</p>");
            }else {
            out.println("<h1>No se encontró el Film.</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e){
            out.println("<p>Error: "+e.getMessage()+"</p>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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

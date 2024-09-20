/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DB.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
public class ModelRenameServlet extends HttpServlet {

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
        String model = request.getParameter("model");
        String newmodel = request.getParameter("newmodel");
        Connection con=null;
        PreparedStatement ps=null;
        try {
            con = Database.getConnection();
            String query = "update model_price set MODEL=? where  MODEL =? ";
            ps = con.prepareStatement(query);
            ps.setString(1, newmodel);
            ps.setString(2, model);
            int a= ps.executeUpdate();
            if(a>0){
            String query1 = "update stock set MODEL=? where  MODEL =? ";
            ps = con.prepareStatement(query1);
            ps.setString(1, newmodel);
            ps.setString(2, model);
            ps.executeUpdate();
            String query2 = "update vendor_stock set PRODUCT=? where  PRODUCT =? ";
            ps = con.prepareStatement(query2);
            ps.setString(1, newmodel);
            ps.setString(2, model);
            ps.executeUpdate();  
            String query3 = "update mobilesell set MODEL=? where  MODEL =? ";
            ps = con.prepareStatement(query3);
            ps.setString(1, newmodel);
            ps.setString(2, model);
            ps.executeUpdate();  
            response.sendRedirect("model_colorEntry.jsp");   
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
   try { if (ps != null) ps.close(); ps=null; } catch (SQLException ex2) { }
   try { if (con != null) con.close(); con=null; } catch (SQLException ex2) { }
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
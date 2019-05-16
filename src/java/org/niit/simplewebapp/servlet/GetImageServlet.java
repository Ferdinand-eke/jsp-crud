/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.niit.simplewebapp.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.niit.simplewebapp.beans.Product;
import org.niit.simplewebapp.utils.DBUtils;
import org.niit.simplewebapp.utils.MyUtils;

/**
 *
 * @author Zinachi Computer
 */
@WebServlet(name = "GetImageServlet", urlPatterns = {"/getImage"})
public class GetImageServlet extends HttpServlet {
private final String UPLOAD_DIRECTORY = "resources/upload";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
 
        String errorString = null;
 
        try {
            Product product = DBUtils.findProduct(conn, code);
            
            String filePath = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY + File.separator + product.getFilename();
            System.out.println("path is " + filePath);
            InputStream is = getServletContext().getResourceAsStream("C:/Users/Zinachi Computer/Documents/NetBeansProjects/SimpleWebApp/build/web/resources" + File.separator + product.getFilename());

            BufferedImage bi = ImageIO.read(is);
            OutputStream os = response.getOutputStream();
            ImageIO.write(bi, "png", os);
        } catch (SQLException e) {
            errorString = e.getMessage();
            request.setAttribute("errorString", errorString);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}

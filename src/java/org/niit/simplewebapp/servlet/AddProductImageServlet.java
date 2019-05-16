/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.niit.simplewebapp.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
import org.niit.simplewebapp.beans.Product;
import org.niit.simplewebapp.utils.DBUtils;
import org.niit.simplewebapp.utils.MyUtils;

 
@WebServlet(name = "AddProductImageServlet", urlPatterns = { "/addProductImage" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 *5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddProductImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY = "resources/upload";
    public AddProductImageServlet() {
        super();
    }
 
    // Show product edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
 
        Product product = null;
 
        String errorString = null;
 
        try {
            product = DBUtils.findProduct(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/addProductImageView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the product information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorString = null;
        String code = (String) request.getParameter("code");
        Product product = null;
       
        try {
            
            Part filePart = request.getPart("file");
            String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            try (InputStream fileContent = filePart.getInputStream()) {
                //String filePath = getServletContext().getRealPath(UPLOAD_DIRECTORY + File.separator + filename); 
                String filePath = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY + File.separator + filename;
                File dir = new File(getServletContext().getInitParameter("image-upload") );
                File file = new File(filePath);
                Files.copy(fileContent, file.toPath(),StandardCopyOption.REPLACE_EXISTING);
            }
	    Connection conn = MyUtils.getStoredConnection(request); 
            product = DBUtils.findProduct(conn, code);
            product.setFilename(filename);
            DBUtils.updateProductImage(conn, product);
                
            // File uploaded successfully
            request.setAttribute("message", "File Uploaded Successfully");
       
        }catch (SQLException e) {
                errorString = e.getMessage();
            }catch (IOException | ServletException ex) {
		request.setAttribute("message", "File Upload Failed due to "
						+ ex);
                errorString = "File Upload Failed due to " + ex.getMessage();
	}
        
            // If error, forward to Edit page.
        if (errorString != null) {
            // Store infomation to request attribute, before forward to views.
            request.setAttribute("errorString", errorString);
            request.setAttribute("product", product);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/addProductImageView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the result page.
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
	
    }
 
    
}
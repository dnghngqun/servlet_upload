package org.example.tomcatdemo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.tomcatdemo.models.FileInfo;

import java.io.*;
import java.util.List;

@WebServlet("/delete")
public class FileDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("name");
        ServletContext context = getServletContext();
        List<FileInfo> uploadedFiles = (List<FileInfo>) context.getAttribute("uploadedFiles");
        if (uploadedFiles != null) {
            uploadedFiles.removeIf(f -> {
                if (f.getName().equals(fileName)) {
                    String uploadsDir = context.getRealPath("/uploads");
                    File file = new File(uploadsDir, fileName);
                    file.delete();
                    return true;
                }
                return false;
            });
            context.setAttribute("uploadedFiles", uploadedFiles);
        }
        resp.sendRedirect("result.jsp");
    }
}
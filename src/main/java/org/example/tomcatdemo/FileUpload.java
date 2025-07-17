package org.example.tomcatdemo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.tomcatdemo.models.FileInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class FileUpload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Part filePart = req.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            long fileSize = filePart.getSize();

            // Save file to uploads directory
            String uploadsDir = getServletContext().getRealPath("/uploads");
            Files.createDirectories(Paths.get(uploadsDir));
            String filePath = uploadsDir + "/" + fileName;
            Files.copy(filePart.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

            ServletContext context = getServletContext();
            List<FileInfo> uploadedFiles = (List<FileInfo>) context.getAttribute("uploadedFiles");
            if (uploadedFiles == null) {
                uploadedFiles = new ArrayList<>();
            }
            uploadedFiles.add(new FileInfo(fileName, fileSize, fileName));
            context.setAttribute("uploadedFiles", uploadedFiles);

            req.setAttribute("fileName", fileName);
            req.setAttribute("fileSize", fileSize);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/result.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Lỗi: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
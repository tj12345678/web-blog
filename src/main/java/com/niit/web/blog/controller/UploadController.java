package com.niit.web.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * @author tj
 * @ClassName UploadController
 * @Description TODO
 * @Date 2019/11/20
 * @Version 1.0
 **/
@MultipartConfig(maxFileSize = 1024 *1024*5)
@WebServlet(urlPatterns = "/api/upload")
public class UploadController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("filename");
        String name = part.getSubmittedFileName();
        System.out.println(name);
        String path = req.getSession().getServletContext().getRealPath("");
        System.out.println(path);
        //f:\\blog\\target\\blog\\1.jpg
        part.write(path + name);
        System.out.println(path + name);
        resp.setContentType("image/jpg");
        req.setAttribute("msg", "上传成功！");
        req.setAttribute("url", "http://localhost:8080/" + name);
        req.getRequestDispatcher("/upload.jsp").forward(req, resp);
    }
}

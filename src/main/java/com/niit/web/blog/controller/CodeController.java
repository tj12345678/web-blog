package com.niit.web.blog.controller;



import com.niit.web.blog.util.GraphicHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author mqxu
 */
@WebServlet(urlPatterns = {"/code", "/check"})
public class CodeController extends HttpServlet {
    private static final long serialVersionUID = 3398560501558431737L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int width = 180;
        final int height = 40;
        final String imgType = "jpg";
        final OutputStream output = resp.getOutputStream();
        String code = GraphicHelper.create(width, height, imgType);
        //计入全局
        req.getServletContext().setAttribute("code", code);
        GraphicHelper.setOutputStream(output);
        resp.setContentType("image/" + imgType);
        File file = new File("D:\\code.jpg");
        InputStream inputStream = new FileInputStream(file);
        byte[] b = new byte[(int) file.length()];
        inputStream.read(b);
        output.write(b);
        inputStream.close();
        output.flush();
        output.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String inputCode = req.getParameter("")
    }
}

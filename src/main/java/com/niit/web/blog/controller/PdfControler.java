package com.niit.web.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author tj
 * @ClassName PdfControler
 * @Description TODO
 * @Date 2019/11/19
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/pdf")
public class PdfControler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        File file = new File("D:/web_home/1.pdf");

        req.getSession().setAttribute("code",file);

        InputStream inputStream=new FileInputStream(file);
        byte[] b =new byte[(int) file.length()];
        inputStream.read(b);
        resp.setContentType("application/pdf");

        OutputStream outputStream = resp.getOutputStream();
        outputStream.write(b);
//        关流
        outputStream.close();
        inputStream.close();
    }
}

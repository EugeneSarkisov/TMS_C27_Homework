package com.form.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/save_request")
public class UploadFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = String.valueOf(req.getParameter("age"));
        if(name.length() <= 0 || age.length() <= 0){
            req.getRequestDispatcher("save_request.jsp").forward(req, resp);
        }
        req.setAttribute("name", name);
        req.setAttribute("age", age);
        getServletContext().getRequestDispatcher("/success_request.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("save_request.jsp").forward(req, resp);
    }
}

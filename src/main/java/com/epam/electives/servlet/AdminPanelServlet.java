package com.epam.electives.servlet;

import com.epam.electives.model.UserProfile;
import com.epam.electives.services.UserMainService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/adminpanel")
public class AdminPanelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter pw = resp.getWriter();
//        pw.print("<h1>Hello World!</h1>");
//        pw.close();
        UserMainService userMainService = new UserMainService();
        HttpSession session = req.getSession();
        session.setAttribute("users", userMainService.getUsers());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/adminpanel.jsp").forward(req, resp);
//        resp.sendRedirect("jsp/adminpanel.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

package com.student.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "pwd123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String course = request.getParameter("course");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            String query = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);

            int rows = ps.executeUpdate();

            con.close();

            if (rows > 0) {
                response.sendRedirect("view");
            } else {
                response.getWriter().println("Insert Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e);
        }
    }
}
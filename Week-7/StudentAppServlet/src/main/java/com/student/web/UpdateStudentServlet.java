package com.student.web;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/update")
public class UpdateStudentServlet extends HttpServlet {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "pwd123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String course = request.getParameter("course");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            String query = "UPDATE students SET name=?, age=?, course=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setInt(4, id);

            ps.executeUpdate();

            con.close();

       
            response.sendRedirect("view");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
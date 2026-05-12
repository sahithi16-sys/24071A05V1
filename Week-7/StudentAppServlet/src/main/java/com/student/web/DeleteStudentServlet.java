package com.student.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/delete")   
public class DeleteStudentServlet extends HttpServlet {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "pwd123";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.executeUpdate();

            con.close();

            
            response.sendRedirect("view");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
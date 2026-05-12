package com.student.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/edit")
public class EditStudentServlet extends HttpServlet {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "pwd123";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Edit Student</title>");

                
                out.println("<style>");

                out.println("body {");
                out.println("  font-family: 'Segoe UI', sans-serif;");
                out.println("  background: #F0EEEA;");
                out.println("  margin: 0;");
                out.println("}");

                out.println(".container {");
                out.println("  width: 420px;");
                out.println("  margin: 80px auto;");
                out.println("  background: #FFFFFF;");
                out.println("  padding: 25px;");
                out.println("  border: 3px solid black;");
                out.println("  box-shadow: 8px 8px 0px black;");
                out.println("}");

                out.println("h2 {");
                out.println("  text-align: center;");
                out.println("  margin-bottom: 20px;");
                out.println("}");

                out.println("label {");
                out.println("  display: block;");
                out.println("  margin-top: 12px;");
                out.println("  font-weight: bold;");
                out.println("}");

                out.println("input {");
                out.println("  width: 100%;");
                out.println("  padding: 10px;");
                out.println("  margin-top: 5px;");
                out.println("  border: 2px solid black;");
                out.println("  background: #F0DDD6;");
                out.println("}");

                out.println("input:focus {");
                out.println("  outline: none;");
                out.println("  background: #F0EEEA;");
                out.println("}");

                out.println("button {");
                out.println("  width: 100%;");
                out.println("  margin-top: 20px;");
                out.println("  padding: 12px;");
                out.println("  border: 3px solid black;");
                out.println("  background: #97B3AE;");
                out.println("  font-weight: bold;");
                out.println("  cursor: pointer;");
                out.println("  box-shadow: 4px 4px 0px black;");
                out.println("}");

                out.println("button:hover {");
                out.println("  transform: translate(2px,2px);");
                out.println("  box-shadow: 2px 2px 0px black;");
                out.println("}");

                out.println("</style>");
                out.println("</head>");

                out.println("<body>");

                out.println("<div class='container'>");
                out.println("<h2>Edit Student</h2>");

                out.println("<form action='update' method='post'>");

                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");

                out.println("<label>Name</label>");
                out.println("<input type='text' name='name' value='" + rs.getString("name") + "' required>");

                out.println("<label>Age</label>");
                out.println("<input type='number' name='age' value='" + rs.getInt("age") + "' required>");

                out.println("<label>Course</label>");
                out.println("<input type='text' name='course' value='" + rs.getString("course") + "' required>");

                out.println("<button type='submit'>Update Student</button>");

                out.println("</form>");
                out.println("</div>");

                out.println("</body>");
                out.println("</html>");
            }

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e + "</h3>");
        }
    }
}
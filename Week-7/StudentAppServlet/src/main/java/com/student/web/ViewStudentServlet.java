package com.student.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet("/view")
public class ViewStudentServlet extends HttpServlet {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "pwd123";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Students</title>");

            out.println("<style>");

            out.println("body {");
            out.println("  font-family: 'Segoe UI', sans-serif;");
            out.println("  background: #F0EEEA;");
            out.println("  margin: 0;");
            out.println("  padding: 30px;");
            out.println("}");

            out.println("h2 {");
            out.println("  text-align: center;");
            out.println("  margin-bottom: 20px;");
            out.println("}");

            out.println(".container {");
            out.println("  width: 85%;");
            out.println("  margin: auto;");
            out.println("  background: #FFFFFF;");
            out.println("  padding: 20px;");
            out.println("  border: 3px solid black;");
            out.println("  box-shadow: 8px 8px 0px black;");
            out.println("}");

            out.println(".add-btn {");
            out.println("  display: inline-block;");
            out.println("  margin-bottom: 15px;");
            out.println("  padding: 10px 15px;");
            out.println("  background: #97B3AE;");
            out.println("  border: 2px solid black;");
            out.println("  box-shadow: 3px 3px 0px black;");
            out.println("  text-decoration: none;");
            out.println("  color: black;");
            out.println("  font-weight: bold;");
            out.println("}");

            out.println(".add-btn:hover {");
            out.println("  transform: translate(2px,2px);");
            out.println("  box-shadow: 1px 1px 0px black;");
            out.println("}");

            out.println("table {");
            out.println("  width: 100%;");
            out.println("  border-collapse: collapse;");
            out.println("}");

            out.println("th {");
            out.println("  background: #D2E0D3;");
            out.println("  border: 2px solid black;");
            out.println("  padding: 10px;");
            out.println("}");

            out.println("td {");
            out.println("  border: 2px solid black;");
            out.println("  padding: 10px;");
            out.println("  text-align: center;");
            out.println("}");

            out.println("tr:nth-child(even) { background: #F0DDD6; }");
            out.println("tr:nth-child(odd) { background: #F0EEEA; }");

            out.println("a {");
            out.println("  text-decoration: none;");
            out.println("  padding: 5px 10px;");
            out.println("  margin: 2px;");
            out.println("  border: 2px solid black;");
            out.println("  display: inline-block;");
            out.println("  font-weight: bold;");
            out.println("}");

            out.println(".edit { background: #D6CBBF; color: black; }");
            out.println(".delete { background: #F2C3B9; color: black; }");

            out.println(".edit:hover, .delete:hover {");
            out.println("  transform: translate(2px,2px);");
            out.println("}");

            out.println("</style>");
            out.println("</head>");

            out.println("<body>");

            out.println("<h2>Student List</h2>");

            out.println("<div class='container'>");

            out.println("<a href='index.html' class='add-btn'>+ Add Student</a>");

            out.println("<table>");
            out.println("<tr><th>ID</th><th>Name</th><th>Age</th><th>Course</th><th>Action</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getInt("age") + "</td>");
                out.println("<td>" + rs.getString("course") + "</td>");

                out.println("<td>");
                out.println("<a class='edit' href='edit?id=" + rs.getInt("id") + "'>Edit</a>");
                out.println("<a class='delete' href='delete?id=" + rs.getInt("id") + "'>Delete</a>");
                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e + "</h3>");
        }
    }
}
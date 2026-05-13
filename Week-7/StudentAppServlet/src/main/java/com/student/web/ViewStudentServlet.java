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

            out.println("  :root {");
            out.println("    --bg: #F5F7FA;");
            out.println("    --panel: #ffffff;");
            out.println("    --primary: #34699A;");
            out.println("    --primary-soft: #7DA7C5;");
            out.println("    --accent: #DA7B93;");
            out.println("    --text: #1F2933;");
            out.println("    --border: rgba(63, 84, 112, 0.15);");
            out.println("  }");

            out.println("  * { box-sizing: border-box; }");
            out.println("  body {");
            out.println("    margin: 0;");
            out.println("    min-height: 100vh;");
            out.println("    font-family: 'Segoe UI', Tahoma, Geneva, sans-serif;");
            out.println("    color: var(--text);");
            out.println("    background: linear-gradient(180deg, #EAF1F8 0%, #F8FAFC 100%);");
            out.println("  }");

            out.println("  .page-wrapper {");
            out.println("    max-width: 1100px;");
            out.println("    margin: 0 auto;");
            out.println("    padding: 32px 20px 48px;");
            out.println("  }");

            out.println("  .header {");
            out.println("    display: flex;");
            out.println("    justify-content: space-between;");
            out.println("    align-items: flex-end;");
            out.println("    gap: 16px;");
            out.println("    margin-bottom: 26px;");
            out.println("  }");

            out.println("  .title-group h1 {");
            out.println("    margin: 0;");
            out.println("    font-size: 2.1rem;");
            out.println("    letter-spacing: -0.03em;");
            out.println("  }");

            out.println("  .title-group p {");
            out.println("    margin: 8px 0 0;");
            out.println("    color: #52606D;");
            out.println("    line-height: 1.6;");
            out.println("  }");

            out.println("  .card {");
            out.println("    background: var(--panel);");
            out.println("    border: 1px solid var(--border);");
            out.println("    border-radius: 24px;");
            out.println("    box-shadow: 0 24px 60px rgba(37, 78, 126, 0.12);");
            out.println("    overflow: hidden;");
            out.println("    padding: 24px;");
            out.println("  }");

            out.println("  .toolbar {");
            out.println("    display: flex;");
            out.println("    justify-content: space-between;");
            out.println("    align-items: center;");
            out.println("    margin-bottom: 24px;");
            out.println("    gap: 16px;");
            out.println("  }");

            out.println("  .add-btn {");
            out.println("    display: inline-flex;");
            out.println("    align-items: center;");
            out.println("    justify-content: center;");
            out.println("    padding: 14px 22px;");
            out.println("    background: var(--primary);");
            out.println("    color: white;");
            out.println("    border: none;");
            out.println("    border-radius: 999px;");
            out.println("    text-decoration: none;");
            out.println("    font-weight: 700;");
            out.println("    transition: transform 0.18s ease, box-shadow 0.18s ease;");
            out.println("  }");

            out.println("  .add-btn:hover {");
            out.println("    transform: translateY(-1px);");
            out.println("    box-shadow: 0 14px 32px rgba(52, 105, 154, 0.16);");
            out.println("  }");

            out.println("  table {");
            out.println("    width: 100%;");
            out.println("    border-collapse: separate;");
            out.println("    border-spacing: 0 10px;");
            out.println("    min-width: 680px;");
            out.println("  }");

            out.println("  thead th {");
            out.println("    background: #F2F6FB;");
            out.println("    color: #1F2933;");
            out.println("    font-weight: 700;");
            out.println("    text-align: left;");
            out.println("    padding: 16px 18px;");
            out.println("    border-bottom: 1px solid rgba(31, 41, 51, 0.08);");
            out.println("  }");

            out.println("  tbody tr {");
            out.println("    background: white;");
            out.println("    border-radius: 18px;");
            out.println("    box-shadow: 0 8px 24px rgba(90, 112, 138, 0.08);");
            out.println("  }");

            out.println("  tbody td {");
            out.println("    padding: 18px;");
            out.println("    border: none;");
            out.println("    vertical-align: middle;");
            out.println("  }");

            out.println("  tbody tr + tr td {");
            out.println("    border-top: 1px solid rgba(31, 41, 51, 0.06);");
            out.println("  }");

            out.println("  tbody tr:nth-child(odd) {");
            out.println("    background: #ffffff;");
            out.println("  }");

            out.println("  tbody tr:nth-child(even) {");
            out.println("    background: #FAFCFF;");
            out.println("  }");

            out.println("  .action-links {");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    gap: 10px;");
            out.println("    flex-wrap: wrap;");
            out.println("  }");

            out.println("  .action-links a {");
            out.println("    display: inline-flex;");
            out.println("    align-items: center;");
            out.println("    justify-content: center;");
            out.println("    min-width: 76px;");
            out.println("    padding: 10px 12px;");
            out.println("    border-radius: 999px;");
            out.println("    font-size: 0.95rem;");
            out.println("    transition: transform 0.18s ease, opacity 0.18s ease;");
            out.println("  }");

            out.println("  .edit {");
            out.println("    background: rgba(82, 160, 133, 0.14);");
            out.println("    color: #1F3A3B;");
            out.println("    border: 1px solid rgba(82, 160, 133, 0.24);");
            out.println("  }");

            out.println("  .delete {");
            out.println("    background: rgba(218, 123, 147, 0.14);");
            out.println("    color: #5B2F39;");
            out.println("    border: 1px solid rgba(218, 123, 147, 0.28);");
            out.println("  }");

            out.println("  .edit:hover, .delete:hover {");
            out.println("    transform: translateY(-1px);");
            out.println("    opacity: 0.96;");
            out.println("  }");

            out.println("  @media (max-width: 780px) {");
            out.println("    .header { flex-direction: column; align-items: stretch; }");
            out.println("    .toolbar { flex-direction: column; align-items: stretch; }");
            out.println("    table { min-width: 100%; }");
            out.println("    thead th, tbody td { padding: 14px 12px; }");
            out.println("  }");

            out.println("</style>");
            out.println("</head>");

            out.println("<body>");
            out.println("<div class='page-wrapper'>");
            out.println("  <div class='header'>");
            out.println("    <div class='title-group'>");
            out.println("      <h1>Student Records</h1>");
            out.println("      <p>Review, edit, or remove student details from the class registry.</p>");
            out.println("    </div>");
            out.println("  </div>");
            out.println("  <div class='card'>");
            out.println("    <div class='toolbar'>");
            out.println("      <div>Showing all students in the database.</div>");
            out.println("      <a href='index.html' class='add-btn'>+ Add Student</a>");
            out.println("    </div>");
            out.println("    <table>");
            out.println("      <thead>");
            out.println("        <tr><th>ID</th><th>Name</th><th>Age</th><th>Course</th><th>Action</th></tr>");
            out.println("      </thead>");
            out.println("      <tbody>");

            while (rs.next()) {
                out.println("        <tr>");
                out.println("          <td>" + rs.getInt("id") + "</td>");
                out.println("          <td>" + rs.getString("name") + "</td>");
                out.println("          <td>" + rs.getInt("age") + "</td>");
                out.println("          <td>" + rs.getString("course") + "</td>");
                out.println("          <td>");
                out.println("            <div class='action-links'>");
                out.println("              <a class='edit' href='edit?id=" + rs.getInt("id") + "'>Edit</a>");
                out.println("              <a class='delete' href='delete?id=" + rs.getInt("id") + "'>Delete</a>");
                out.println("            </div>");
                out.println("          </td>");
                out.println("        </tr>");
            }

            out.println("      </tbody>");
            out.println("    </table>");
            out.println("  </div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e + "</h3>");
        }
    }
}
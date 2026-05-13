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
                out.println("  :root {");
                out.println("    --bg: #F4F7FA;");
                out.println("    --panel: #ffffff;");
                out.println("    --primary: #34699A;");
                out.println("    --primary-soft: #7DA7C5;");
                out.println("    --accent: #DA7B93;");
                out.println("    --text: #1F2933;");
                out.println("    --border: rgba(63, 84, 112, 0.12);");
                out.println("  }");
                out.println("  * { box-sizing: border-box; }");
                out.println("  body {");
                out.println("    margin: 0;");
                out.println("    min-height: 100vh;");
                out.println("    font-family: 'Segoe UI', Tahoma, Geneva, sans-serif;");
                out.println("    color: var(--text);");
                out.println("    background: radial-gradient(circle at top left, rgba(52, 105, 154, 0.12), transparent 30%), var(--bg);");
                out.println("  }");
                out.println("  .page-frame {");
                out.println("    max-width: 600px;");
                out.println("    margin: 60px auto;");
                out.println("    padding: 0 18px 40px;");
                out.println("  }");
                out.println("  .page-header {");
                out.println("    margin-bottom: 24px;");
                out.println("    text-align: center;");
                out.println("  }");
                out.println("  .page-header h1 {");
                out.println("    margin: 0;");
                out.println("    font-size: 2rem;");
                out.println("    letter-spacing: -0.04em;");
                out.println("  }");
                out.println("  .page-header p {");
                out.println("    margin: 10px auto 0;");
                out.println("    max-width: 460px;");
                out.println("    color: #556471;");
                out.println("    line-height: 1.65;");
                out.println("  }");
                out.println("  .panel {");
                out.println("    background: var(--panel);");
                out.println("    border: 1px solid var(--border);");
                out.println("    border-radius: 24px;");
                out.println("    box-shadow: 0 24px 60px rgba(37, 78, 126, 0.09);");
                out.println("    padding: 32px;");
                out.println("  }");
                out.println("  .field-group {");
                out.println("    display: grid;");
                out.println("    gap: 18px;");
                out.println("  }");
                out.println("  label {");
                out.println("    display: block;");
                out.println("    margin-bottom: 8px;");
                out.println("    font-weight: 700;");
                out.println("  }");
                out.println("  input {");
                out.println("    width: 100%;");
                out.println("    padding: 14px 16px;");
                out.println("    border: 1px solid rgba(63, 84, 112, 0.16);");
                out.println("    border-radius: 14px;");
                out.println("    background: #FBFDFF;");
                out.println("    color: var(--text);");
                out.println("    transition: border-color 0.18s ease, box-shadow 0.18s ease;");
                out.println("  }");
                out.println("  input:focus {");
                out.println("    outline: none;");
                out.println("    border-color: var(--primary);");
                out.println("    box-shadow: 0 0 0 3px rgba(52, 105, 154, 0.12);");
                out.println("  }");
                out.println("  .button-row {");
                out.println("    display: flex;");
                out.println("    flex-direction: column;");
                out.println("    gap: 14px;");
                out.println("    margin-top: 28px;");
                out.println("  }");
                out.println("  button {");
                out.println("    width: 100%;");
                out.println("    padding: 14px 18px;");
                out.println("    border: none;");
                out.println("    border-radius: 999px;");
                out.println("    background: var(--primary);");
                out.println("    color: white;");
                out.println("    font-size: 1rem;");
                out.println("    font-weight: 700;");
                out.println("    cursor: pointer;");
                out.println("    transition: transform 0.18s ease, box-shadow 0.18s ease;");
                out.println("  }");
                out.println("  button:hover {");
                out.println("    transform: translateY(-1px);");
                out.println("    box-shadow: 0 14px 28px rgba(52, 105, 154, 0.18);");
                out.println("  }");
                out.println("  .back-link {");
                out.println("    display: inline-block;");
                out.println("    color: #556471;");
                out.println("    text-decoration: none;");
                out.println("    font-size: 0.95rem;");
                out.println("    margin-top: 16px;");
                out.println("  }");
                out.println("  .back-link:hover {");
                out.println("    color: var(--primary);");
                out.println("  }");
                out.println("  @media (max-width: 520px) {");
                out.println("    .page-frame { margin: 30px auto; padding: 0 14px 32px; }");
                out.println("    .panel { padding: 24px; }");
                out.println("    .page-header h1 { font-size: 1.75rem; }");
                out.println("  }");
                out.println("</style>");
                out.println("</head>");

                out.println("<body>");
                out.println("  <div class='page-frame'>");
                out.println("    <div class='page-header'>");
                out.println("      <h1>Edit Student</h1>");
                out.println("      <p>Update the record details below and save changes to the student database.</p>");
                out.println("    </div>");
                out.println("    <div class='panel'>");
                out.println("      <form action='update' method='post'>");
                out.println("        <input type='hidden' name='id' value='" + rs.getInt("id") + "'>");
                out.println("        <div class='field-group'>");
                out.println("          <div>");
                out.println("            <label for='name'>Name</label>");
                out.println("            <input id='name' type='text' name='name' value='" + rs.getString("name") + "' required>");
                out.println("          </div>");
                out.println("          <div>");
                out.println("            <label for='age'>Age</label>");
                out.println("            <input id='age' type='number' name='age' value='" + rs.getInt("age") + "' required>");
                out.println("          </div>");
                out.println("          <div>");
                out.println("            <label for='course'>Course</label>");
                out.println("            <input id='course' type='text' name='course' value='" + rs.getString("course") + "' required>");
                out.println("          </div>");
                out.println("        </div>");
                out.println("        <div class='button-row'>");
                out.println("          <button type='submit'>Update Student</button>");
                out.println("          <a class='back-link' href='view'>Return to Student List</a>");
                out.println("        </div>");
                out.println("      </form>");
                out.println("    </div>");
                out.println("  </div>");
                out.println("</body>");
                out.println("</html>");
            }

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e + "</h3>");
        }
    }
}
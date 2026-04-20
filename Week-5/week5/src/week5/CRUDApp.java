package week5;
import java.sql.*;
import java.util.Scanner;

public class CRUDApp {

	static final String URL = "jdbc:mysql://localhost:3306/studentdb";
	static final String USER = "root";
	static final String PASSWORD = "pwd123";

	static Connection con;
	static Scanner sc = new Scanner(System.in);

	static void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected to MySQL successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	static void create() {
//		try {
//			Statement stmt = con.createStatement();
//			stmt.executeUpdate(
//					"CREATE TABLE IF NOT EXISTS Students(RNO INT PRIMARY KEY, NAME VARCHAR(20) NOT NULL, MARK1 INT NOT NULL, MARK2 INT NOT NULL, MARK3 INT NOT NULL);");
//			System.out.println("Table Created Successfully!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	static void insert() {
		try {
			System.out.println("Enter Roll Number: ");
			int id = sc.nextInt();
			System.out.println("Enter Name: ");
			String str = sc.next();
			System.out.println("Enter Marks: ");
			int m1 = sc.nextInt(), m2 = sc.nextInt(), m3 = sc.nextInt();

			PreparedStatement ps = con.prepareStatement("INSERT INTO Students VALUES(?,?,?,?,?);");
			ps.setInt(1, id);
			ps.setString(2, str);
			ps.setInt(3, m1);
			ps.setInt(4, m2);
			ps.setInt(5, m3);
			ps.executeUpdate();
			System.out.println("Record Inserted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void view() {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Students;");

			System.out.println("\nStudent Information");
			System.out.println("ID\tName\tMarks");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + "[" + rs.getInt(3) + ","
						+ rs.getInt(4) + "," + rs.getInt(5) + "]");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void update() {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE Students SET name = ? WHERE rno = ?;");
			System.out.println("Enter Roll Number to Update: ");
			int rno = sc.nextInt();
			
			System.out.println("Enter new Name: ");
			String name = sc.next();
			
			ps.setString(1, name);
			ps.setInt(2, rno);
			
			ps.executeUpdate();
			System.out.println("Record updated successfully");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void delete() {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Students WHERE rno = ?;");
			System.out.println("Enter Roll Number to Delete: ");
			int rno = sc.nextInt();
			
			ps.setInt(1, rno);
					
			ps.executeUpdate();
			System.out.println("Record deleted successfully");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {

        connect();
//        create();

        while (true) {
            System.out.println("\n1.Insert  2.View  3.Update  4.Delete  5.Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    insert();
                    break;
                case 2:
                    view();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    try { con.close(); } catch (Exception e) {}
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
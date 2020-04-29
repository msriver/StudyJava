import java.sql.*;
import java.io.*;


public class Jdbc01 {

	public static void main(String[] args) {
		Connection conn;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root", "1234");
			System.out.println("##DB연결완료##");
			stmt = conn.createStatement();
			
			ResultSet srs = stmt.executeQuery("select * from student");
			printDate(srs,"name", "id", "dept");
			
			srs = stmt.executeQuery("select name, id, dept from student where name = '이기자'");
			printDate(srs, "name", "id", "dept");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("JDBC 드라이버 로드 에러");
			
		} catch (SQLException e) {
			
			System.out.println("SQL 실행 에러");
			
		}
		
		
		
	}

	
	private static void printDate(ResultSet srs, String col1, String col2, String col3) throws SQLException {
		while (srs.next()) {
			if(!col1.equals(null)) System.out.print(new String(srs.getString("name")));
			if(!col2.equals(null)) System.out.print("\t|\t" + srs.getString("id"));
			if(!col3.equals(null)) System.out.println("\t|\t" + new String(srs.getString("dept")));
			else System.out.println();
			
			
		}
	}
}
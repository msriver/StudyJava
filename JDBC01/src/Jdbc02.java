import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc02 {
	public static void main(String[] args) {
		
		Connection conn =null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root", "1234");
			Statement stmt = conn.createStatement();
			System.out.println("DB연결 완료##");
			
			//stmt.executeUpdate("insert into student values('000001','세종', '국문학')"); // insert문
			
			//stmt.execute("update student set name = '세종대왕' where id = '000001'"); // update문
			
			stmt.executeUpdate("delete from student where id = '000001';");
			
			rs = stmt.executeQuery("select * from student;");
			while(rs.next()) {
				System.out.print(rs.getString("id") + "\t");
				System.out.print(rs.getString("name") + "\t");
				System.out.println(rs.getString("dept"));
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 에러");
		} finally {
			try {
				if(rs!=null) rs.close();          // 옆의 try-catch부분은 정말 자주 쓰임.
				if(conn!=null) conn.close();		// 연결을 종료하는 구문
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}

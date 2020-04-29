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
			System.out.println("DB���� �Ϸ�##");
			
			//stmt.executeUpdate("insert into student values('000001','����', '������')"); // insert��
			
			//stmt.execute("update student set name = '�������' where id = '000001'"); // update��
			
			stmt.executeUpdate("delete from student where id = '000001';");
			
			rs = stmt.executeQuery("select * from student;");
			while(rs.next()) {
				System.out.print(rs.getString("id") + "\t");
				System.out.print(rs.getString("name") + "\t");
				System.out.println(rs.getString("dept"));
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("DB ���� ����");
		} finally {
			try {
				if(rs!=null) rs.close();          // ���� try-catch�κ��� ���� ���� ����.
				if(conn!=null) conn.close();		// ������ �����ϴ� ����
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testDBconnection {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/qlbanbe"; 
        String user = "root"; 
        String password = "123456"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Kết nối thành công!");
            }
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại!");
            e.printStackTrace();
        }

	}

}

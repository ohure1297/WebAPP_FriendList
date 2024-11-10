package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Friend;

public class FriendDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/qlbanbe?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    // Kết nối đến CSDL
    private Connection getConnection() throws SQLException {
    	try {
            // Register the MySQL JDBC driver (if necessary)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create the connection to the database
            String url = "jdbc:mysql://localhost:3306/qlbanbe?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
            String user = "root"; // Replace with your MySQL username
            String password = "123456"; // Replace with your MySQL password
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL Driver not found.");
        }
    }

    // Lấy danh sách bạn bè
    public List<Friend> getAllFriends() {
        List<Friend> friends = new ArrayList<>();
        String sql = "SELECT * FROM friends";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Friend friend = new Friend(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("address")
                );
                friends.add(friend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    // Thêm bạn bè
    public void addFriend(Friend friend) {
        String sql = "INSERT INTO friends (name, phone, email, address) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, friend.getName());
            pstmt.setString(2, friend.getPhone());
            pstmt.setString(3, friend.getEmail());
            pstmt.setString(4, friend.getAddress());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật thông tin bạn bè
    public void updateFriend(Friend friend) {
        String sql = "UPDATE friends SET name = ?, phone = ?, email = ?, address = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, friend.getName());
            pstmt.setString(2, friend.getPhone());
            pstmt.setString(3, friend.getEmail());
            pstmt.setString(4, friend.getAddress());
            pstmt.setInt(5, friend.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa bạn bè
    public void deleteFriend(int id) {
        String sql = "DELETE FROM friends WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tìm kiếm bạn bè
    public List<Friend> searchFriends(String keyword) {
        List<Friend> friends = new ArrayList<>();
        String sql = "SELECT * FROM friends WHERE name LIKE ? OR phone LIKE ? OR email LIKE ? OR address LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%";
            pstmt.setString(1, searchKeyword);
            pstmt.setString(2, searchKeyword);
            pstmt.setString(3, searchKeyword);
            pstmt.setString(4, searchKeyword);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Friend friend = new Friend(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("address")
                );
                friends.add(friend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }
}

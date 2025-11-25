import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserLogin {

    /**
     * 用户登录验证
     * @param username 用户名
     * @param password 密码
     * @return 如果验证成功，返回用户角色；否则返回 null
     */
    public static String login(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String encryptedPassword = encryptPassword(password);

        try {
            conn = DBUtil.getConnection();
            if (conn == null) {
                return null; // 连接失败
            }

            // SQL 查询语句
            String sql = "SELECT role FROM user WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, encryptedPassword); // 注意：这里使用加密后的密码

            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 登录成功，返回用户角色 (admin/member)
                return rs.getString("role");
            } else {
                // 用户名或密码错误
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.closeConnection(conn);
            }
        }
        return null;
    }

    private static String encryptPassword(String password) {
        StringBuilder sb = new StringBuilder();
        for (char c : password.toCharArray()) {
            sb.append((char) (c + 10));
        }
        return sb.toString();
    }
    
    // 主方法，用于测试
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 校园社团活动管理系统 ===");
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();

        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        String role = login(username, password);

        if (role != null) {
            System.out.println("\n登录成功！您的角色是: " + role);
            // 在这里可以根据角色跳转到不同的功能界面
            if ("admin".equals(role)) {
                System.out.println("欢迎进入管理员界面...");
            } else if ("member".equals(role)) {
                System.out.println("欢迎进入成员界面...");
            }
        } else {
            System.out.println("\n登录失败！用户名或密码错误。");
        }

        scanner.close();
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class ActivitySignup {

    /**
     * 用户报名参加活动
     * @param userId 用户ID
     * @param activityId 活动ID
     * @return 如果报名成功返回 true，否则返回 false
     */
    public static boolean signupForActivity(int userId, int activityId) {
        Connection conn = null;
        PreparedStatement pstmtCheck = null;
        PreparedStatement pstmtInsert = null;

        try {
            conn = DBUtil.getConnection();
            if (conn == null) {
                return false;
            }
            conn.setAutoCommit(false); // 开启事务

            // 1. 检查活动是否存在且状态为 "approved"
            String checkSql = "SELECT 1 FROM activity WHERE activity_id = ? AND status = 'approved'";
            pstmtCheck = conn.prepareStatement(checkSql);
            pstmtCheck.setInt(1, activityId);
            if (!pstmtCheck.executeQuery().next()) {
                System.out.println("报名失败：活动不存在或未通过审批。");
                conn.rollback();
                return false;
            }

            // 2. 检查用户是否已经报名
            String checkSignupSql = "SELECT 1 FROM activity_signup WHERE user_id = ? AND activity_id = ?";
            pstmtCheck = conn.prepareStatement(checkSignupSql);
            pstmtCheck.setInt(1, userId);
            pstmtCheck.setInt(2, activityId);
            if (pstmtCheck.executeQuery().next()) {
                System.out.println("报名失败：您已经报名过此活动。");
                conn.rollback();
                return false;
            }

            // 3. 执行报名操作
            String insertSql = "INSERT INTO activity_signup (user_id, activity_id, signup_time) VALUES (?, ?, ?)";
            pstmtInsert = conn.prepareStatement(insertSql);
            pstmtInsert.setInt(1, userId);
            pstmtInsert.setInt(2, activityId);
            pstmtInsert.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            int affectedRows = pstmtInsert.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("报名成功！");
                conn.commit(); // 提交事务
                return true;
            } else {
                System.out.println("报名失败：数据库操作异常。");
                conn.rollback(); // 回滚事务
                return false;
            }

        } catch (SQLException e) {
            System.err.println("报名过程中发生数据库错误！");
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 关闭资源
            try {
                if (pstmtCheck != null) pstmtCheck.close();
                if (pstmtInsert != null) pstmtInsert.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.setAutoCommit(true); // 恢复自动提交
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 假设用户已登录，获取到 userId
        int loggedInUserId = 101; // 这是一个示例 ID

        System.out.println("=== 活动报名 ===");
        System.out.print("请输入要报名的活动ID: ");
        int activityId = scanner.nextInt();

        signupForActivity(loggedInUserId, activityId);

        scanner.close();
    }
}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.db.DBConnection;

public class ExamPlansFetcher {
	public static ArrayList<ExamPlan> fetchExamPlans(int userId) {
		ArrayList<ExamPlan> examPlanArray = new ArrayList<ExamPlan>();
		
		// コネクションのインスタンスを取得
		Connection conn = DBConnection.getConnection();
		
		// 試験スケジュール情報取得のための SQL を定義
		// あとで情報を取得するため列に AS で別名を付与
		String sql = "SELECT c.certification_name AS c_name, ep.application_status AS status, ep.exam_date AS e_date\n"
				+ "FROM exam_plan ep INNER JOIN certification c USING(certification_code)\n"
				+ "WHERE ? = ep.user_id\n"
				+ "ORDER BY ep.exam_date";
		
		// DBに接続
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// SQL にユーザー名とパスワードを設定して実行
			pstmt.setInt(1, userId);
			
			ResultSet result = pstmt.executeQuery();
			
			
			// 取得した情報を CertifiedInfo の配列にプロット
			while (result.next()) {
				String examName = result.getString("c_name");
				LocalDate examDate = result.getDate("e_date").toLocalDate();
				int applications_status = result.getInt("status");
				examPlanArray.add(new ExamPlan(examName, examDate, applications_status));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return examPlanArray;
	}
}

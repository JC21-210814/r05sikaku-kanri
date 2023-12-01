package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.db.DBConnection;

public class CertifiedInfomationsFetcher {
	public static ArrayList<CertifiedInfo> fetchCertifiedInfo(int userId) {
		ArrayList<CertifiedInfo> certifiedInfoArray = new ArrayList<CertifiedInfo>();
		
		// コネクションのインスタンスを取得
		Connection conn = DBConnection.getConnection();
		
		// 合格情報取得ための SQL を定義
		// あとで情報を取得するために列に AS で別名を付与
		String sql = "SELECT c.certification_name AS c_name, ci.certified_date AS c_date\n"
				+ "FROM certified_info ci INNER JOIN certification c USING(certification_code)\n"
				+ "WHERE ? = ci.user_id\n"
				+ "ORDER BY ci.certified_date;";
		
		// DBに接続
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// SQL にユーザー名とパスワードを設定して実行
			pstmt.setInt(1, userId);
			
			ResultSet result = pstmt.executeQuery();
			
			
			// 取得した情報を CertifiedInfo の配列にプロット
			while (result.next()) {
				String certificationName = result.getString("c_name");
				LocalDate certifiedDate = result.getDate("c_date").toLocalDate();
				certifiedInfoArray.add(new CertifiedInfo(certificationName, certifiedDate));
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
		
		return certifiedInfoArray;
	}
}

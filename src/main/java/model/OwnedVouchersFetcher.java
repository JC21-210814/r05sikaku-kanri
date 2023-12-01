package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.db.DBConnection;

public class OwnedVouchersFetcher {
	public static ArrayList<Voucher> fetchOwnedVouchers(int userId) {
		ArrayList<Voucher> ownedVoucherArray = new ArrayList<Voucher>();
		
		// コネクションのインスタンスを取得
		Connection conn = DBConnection.getConnection();
		
		// 試験スケジュール情報取得のための SQL を定義
		// あとで情報を取得するため列に AS で別名を付与
		String sql = "SELECT v.voucher_name AS v_name, ovi.limit_date AS l_date\n"
				+ "FROM owned_voucher_info ovi INNER JOIN voucher v USING(voucher_code)\n"
				+ "WHERE ? = ovi.user_id\n"
				+ "ORDER BY ovi.limit_date";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// SQL にユーザー名とパスワードを設定して実行
			pstmt.setInt(1, userId);
			
			ResultSet result = pstmt.executeQuery();
			
			
			// 取得した情報を CertifiedInfo の配列にプロット
			while (result.next()) {
				String voucherName = result.getString("v_name");
				LocalDate limitDate = result.getDate("l_date").toLocalDate();
				ownedVoucherArray.add(new Voucher(voucherName, limitDate));
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
		
		return ownedVoucherArray;
	}
}

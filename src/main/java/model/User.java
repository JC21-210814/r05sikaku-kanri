package model;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.DBConnection;

public class User {
	private String name;
	private int id;
	private int flag;
	private boolean isVerified;
	
	public User(String name, String password) 
			throws IllegalArgumentException{
		this.name = name;
		String hashedPassword = null;
		
		try {
			// 入力されたパスワードをハッシュ化(MD5)
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md5_result = md5.digest(password.getBytes());
			
			
			hashedPassword = String.format("%020x", new BigInteger(1, md5_result));			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// コネクションのインスタンスを取得
		Connection conn = DBConnection.getConnection();
		
		// ユーザー情報取得ための SQL を定義
		String sql = "SELECT user_id, flag FROM users WHERE ? = user_name AND ? = pass_hash;";
		
		// DBへのアクセス
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// SQL にユーザー名とパスワードを設定して実行
			pstmt.setString(1, name);
			pstmt.setString(2, hashedPassword);
			
			ResultSet result = pstmt.executeQuery();
			
			
			// 結果が0件の場合には例外をスロー
			if (!result.isBeforeFirst()) {
				throw new IllegalArgumentException("ユーザーが存在しません");
			}
			
			// 取得した情報でクラス変数を設定
			result.next();
			this.id = result.getInt("user_id");
			this.flag = result.getInt("flag");
			this.isVerified = true;
			
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
	}
	
	public boolean isVerified() {
		return isVerified;
	}
	
	public int getUserType() {
		return flag;
	}
	
	public String getUserName() {
		return name;
	}
	
	public int getUserId() {
		return id;
	}
}

package servlets;

import java.sql.*;

public class SQLConnector {

	public static Connection conexion() {
		Connection con = null;
		String url = "jdbc:postgresql://localhost/homework-6?user=postgres&password=root";
		try {
			con = DriverManager.getConnection(url);
			Class.forName("org.postgresql.Driver");
			if (con != null) {
				System.out.println("Conexión exitosa");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return con;
	}

	public static void ejecuta(String sql) {
		Connection con = null;
		try {
			con = conexion();
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			// con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public static ResultSet ejecutaQ(String sql) {
		ResultSet rs = null;
		try {
			Connection con = conexion();
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void main(String[] args) {
		conexion();
		try {
			ResultSet rs = ejecutaQ("select * from type_contact;");
			while(rs.next()) {
				System.out.println(rs.getString(1)+", "+rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

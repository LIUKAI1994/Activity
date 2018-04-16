package database;

import java.sql.*;
import java.util.*;

public class ActivityDBAO {
	Connection con;
	
	public ActivityDBAO() throws Exception {
		try {
			DB db = new DB();
			con = db.getConnection();
		} catch (Exception e) {
			System.out.println("Exception in ActivityDBAO: " + e);
			throw new Exception("Couldn't open connection to database: "
					+ e.getMessage());
		}
	}
	
	public List<Activity> getActivitiesByCategory(String category) throws SQLException {
		ArrayList<Activity> activities = new ArrayList<>();
		try {
			String sqlStatement = "select a.*, u.name from activities as a inner join users as u on a.creatorId = u.id where category = ?";
			PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
			prepStmt.setString(1, category);
			
			ResultSet rs = prepStmt.executeQuery();
			
			while(rs.next()) {
				User user = new User(rs.getInt(11), rs.getString(12), "", "", "", "");
				Activity activity = new Activity(rs.getInt(1), 
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7),
						rs.getString(8), rs.getInt(9), rs.getInt(10), user.getId(), user);
				activities.add(activity);
			}
			prepStmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return activities;
	}
	
	public Activity getActivityById(String id) throws SQLException {
		Activity activity = null;
		try {
			String sqlStatement = "select a.*, u.name from activities as a inner join users as u on a.creatorId = u.id where a.id = ?";
			PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
			prepStmt.setString(1, id);
			
			ResultSet rs = prepStmt.executeQuery();
			
			while(rs.next()) {
				User user = new User(rs.getInt(11), rs.getString(12), "", "", "", "");
				activity = new Activity(rs.getInt(1), 
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7),
						rs.getString(8), rs.getInt(9), rs.getInt(10), user.getId(), user);
			}
			prepStmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return activity;
	}
	
	public void createActivity(Activity activity) throws SQLException {
		try {
			String sqlStatement = "insert into activities(name, `desc`, startTime, endTime, category, count, image, creatorId) values "
					+ "(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(sqlStatement);
			preparedStatement.setString(1, activity.getName());
			preparedStatement.setString(2, activity.getDesc());
			preparedStatement.setString(3, activity.getStartTime());
			preparedStatement.setString(4, activity.getEndTime());
			preparedStatement.setString(5, activity.getCategory());
			preparedStatement.setInt(6, activity.getCount());
			preparedStatement.setString(7, activity.getImage());
			preparedStatement.setInt(8, activity.getCreatorId());
			
			preparedStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}

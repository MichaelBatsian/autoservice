package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ForumBean;
import dao.ForumDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlForumDAO implements ForumDAO {

	
	public void createTopic(String topic) {
		
		int maxUserId = UtilsDAO.getMaxTableId("topic_id", "forum");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO forum(topic_id, topic) VALUES(?,?)");
			ps.setInt(1, maxUserId);
			ps.setString(2, topic);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void deleteTopic(int topicId) {
		
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM forum WHERE topic_id=?");
			ps.setInt(1, topicId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void updateTopic(int topicId, String newTopic) {
		
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE forum SET topic = ? WHERE topic_id = ?");
			ps.setString(1, newTopic);
			ps.setInt(2, topicId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public ArrayList<ForumBean> getForumTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ForumBean bean;
		ArrayList<ForumBean> forumTable = new ArrayList<ForumBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM forum");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createForumBean(rs);
				forumTable.add(bean);
			}
			return forumTable;
		} catch (SQLException  e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

}

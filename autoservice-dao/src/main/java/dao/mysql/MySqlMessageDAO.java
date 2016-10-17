package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.MessagesBean;
import dao.MessagesDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlMessageDAO implements MessagesDAO {

	
	public void createMessage(int user_id, String message, int topicId) {
		System.out.println("user_id при создании сообщения "+user_id);
		int maxUserId = UtilsDAO.getMaxTableId("message_id", "messages");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO messages(message_id, fk_user_id_, message, fk_topic_id) VALUES(?,?,?,?)");
			ps.setInt(1, maxUserId);
			ps.setInt(2, user_id);
			ps.setString(3, message );
			ps.setInt(4, topicId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public ArrayList<MessagesBean> getMessageTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MessagesBean bean;
		ArrayList<MessagesBean> messageTable = new ArrayList<MessagesBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM messages");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createMessagesBean(rs);
				messageTable.add(bean);
			}
			return messageTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	
	public ArrayList<MessagesBean> getMessageNameTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MessagesBean bean;
		ArrayList<MessagesBean> messageTable = new ArrayList<MessagesBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT messages.message_id,users.login,messages.message,forum.topic,roles.role, "
					+ "messages.message_datetime,usersdata.fullname FROM messages,forum,roles,usersdata,users WHERE "
					+ "messages.fk_user_id_=users.user_id AND messages.fk_user_id_=usersdata.data_user_id AND users.role_id=roles.role_id "
					+ "AND messages.fk_topic_id=forum.topic_id");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createMessagesNameBean(rs);
				messageTable.add(bean);
			}
			return messageTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	

	
	public void updateMessage(int messageId, String newMessage) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE messages SET message = ? WHERE message_id = ?");
			ps.setString(1, newMessage);
			ps.setInt(2, messageId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void deleteMessage(int messageId) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE messages SET message = 'сообщение было удалено' WHERE message_id = ?");
			ps.setInt(1, messageId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public ArrayList<MessagesBean> getMessagesForTopic(int topicId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MessagesBean bean;
		ArrayList<MessagesBean> messageTable = new ArrayList<MessagesBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT messages.message_id, users.login,roles.role, messages.message, forum.topic, "
					+ "usersdata.fullname, messages.message_datetime "
					+ "FROM messages, forum, users,roles,usersdata WHERE messages.fk_user_id_=users.user_id AND messages.fk_topic_id=forum.topic_id "
					+ "AND roles.role_id=users.role_id AND messages.fk_user_id_=usersdata.data_user_id AND forum.topic_id=? "
					+ "ORDER BY messages.message_datetime DESC");
			ps.setInt(1, topicId);
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createMessagesNameBean(rs);
				messageTable.add(bean);
			}
			return messageTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

}

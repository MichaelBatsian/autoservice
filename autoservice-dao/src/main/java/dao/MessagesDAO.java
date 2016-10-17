package dao;

import java.util.ArrayList;

import beans.MessagesBean;

public interface MessagesDAO {
	
	public void createMessage(int userId, String message, int topicId);
	public ArrayList<MessagesBean> getMessageTable();
	public void updateMessage (int messageId, String newMessage);
	public void deleteMessage(int messageId);
	public ArrayList<MessagesBean> getMessageNameTable();
	public ArrayList<MessagesBean> getMessagesForTopic(int topicId);
	

}

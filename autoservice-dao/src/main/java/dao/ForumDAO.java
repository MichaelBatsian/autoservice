package dao;

import java.util.ArrayList;

import beans.ForumBean;

public interface ForumDAO {
	
	public void createTopic(String topic);
	public void deleteTopic(int topicId);
	public void updateTopic(int topicId, String newTopic);
	public ArrayList<ForumBean> getForumTable();

}

package dao;

import java.util.ArrayList;

import beans.UserBean;

public interface UsersDAO {

	public UserBean getUser(String login);
	public void updateLogin(int userId, String newLogin);
	public void updatePassword(int userId, String newPassword);
	public void updateRole(int userId, int newRoleId);
	public void updateStatus(int userId, String status);
	public void createUser(String login, String password, int roleId);
	public int getMaxUserId();
	public void deleteUser(int userId);
	public String getRole(int userId);
	public String getBlockedStatus(int userId);
	public boolean isUser(String login, String password);
	public ArrayList<UserBean> getUserTable();
	public ArrayList<UserBean> getUserNameTable();
	
	
}

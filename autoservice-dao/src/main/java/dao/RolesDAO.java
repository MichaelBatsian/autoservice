package dao;

import java.util.ArrayList;

import beans.RoleBean;

public interface RolesDAO {
	
	public RoleBean getUser(int roleId);
	public void updateRole(int roleId, String newRole);
	public void createRole(String role);
	public void deleteRole(int roleId);
	public ArrayList<RoleBean> getRolesTable();

}

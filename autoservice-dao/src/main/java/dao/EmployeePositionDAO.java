package dao;

import java.util.ArrayList;

import beans.EmployeePositionBean;

public interface EmployeePositionDAO {
	 public void createPosition(String position);
	 public void updatePosition(int positionId, String position);
	 public void deletePosition(int positionId);
	 public ArrayList<EmployeePositionBean> getPositionTable();
}

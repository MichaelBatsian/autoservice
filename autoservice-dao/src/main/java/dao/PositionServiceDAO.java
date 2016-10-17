package dao;

import java.util.ArrayList;
import beans.PositionServiceBean;


public interface PositionServiceDAO {
	
	 public ArrayList<Integer> getAllPositionsIdtoServiceStation(int serviceId, int stationId);
	 public void createPositionService(int positionId, int serviceId);
	 public void updatePosition(int positionServiceId, int positionId);
	 public void updateService(int positionServiceId, int serviceId);
	 public void deletePositionService(int positionServiceId);
	 public ArrayList<PositionServiceBean> getPositionServiceTable();
}

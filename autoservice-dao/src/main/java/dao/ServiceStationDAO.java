package dao;

import java.util.ArrayList;

import beans.ServiceStationBean;

public interface ServiceStationDAO {
	
	public void createStation(String stationName, String location);
	public void updateStationName(int stationId, String newNqme);
	public void updateStationLocation(int stationId, String newLocation);
	public void deleteStation(int stationId);
	public ArrayList<ServiceStationBean> getStationsTable();

}

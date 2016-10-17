package dao;

import java.util.ArrayList;

import beans.ServicesToStationsBean;

public interface ServicesToStationsDAO {
	
	public ArrayList<ServicesToStationsBean> getServicesToStationsTable();
	public void deleteServiceToStation(int serviceStationId);
	public void createServiceToStation(int serviceId, int stationId);
	public ArrayList<ServicesToStationsBean> getServicesToStationsNameTable();
	public ArrayList<ServicesToStationsBean> getStationsToService(int stationId);
	public int getServicesToStationsId(int serviceId, int stationId);
	public void updateService(int serviceStationId,int serviceId);
	public void updateStation(int serviceStationId,int stationId);
	public void deleteServicesToStations(int serviceStationId);
}

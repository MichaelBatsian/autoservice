package dao;

import java.util.ArrayList;

import beans.ServicesBean;

public interface ServicesDAO {
	
	public void createService(String service, int manHours, int price);
	public void updateService(int serviceId, String service);
	public void updateServiceHours(int serviceId, int manHours);
	public void updateServicePrice(int serviceId, int price);
	public void deleteService(int serviceid);
	public ArrayList<ServicesBean> getServiceTable();
	public int getManHoursToService(int serviceId);
	public int getManHoursToService(String service);
	public int getServicePrice(String service);
}

package services;

public interface AdminActionService {
	
	public void updateUser(int userId, String login, String password, String role, String blockedStatus);
	public void deleteUser(int userId);
	public void createUser(String login, String password, String role);
	
	public void updateRole(int roleId,String role);
	public void deleteRole(int roleId);
	public void createRole(String role);
	
	public void updateDiscount(String discountId, String discount, String totalsum);
	public void deleteDiscount(int discountId);
	public void createDiscount(float discount, int totalsum);
	
	public void updateClientDiscount(String clientDiscountId, String login, String discount, String totalSum);
	public void deleteClientDiscount(String discountId);
	public void createClientDiscount(String login, String discount, String totalSum);
	
	public void updateForum(String topicId, String topic);
	public void deleteForum(String topicId);
	public void createForum(String topic);
	
	public void updateMessage(String messageId, String newMessage);
	public void deleteMessage(String messageId);
	public void createMessage(String loginMessage, String message);
	
	public void updateService(String serviceId, String service, String manHours, String price);
	public void deleteService(String serviceId);
	public void createService(String service, String manHours, String price);
	
	public void updateStation(String stationId, String station, String location);
	public void deleteStation(String stationId);
	public void createStation(String station, String location);
	
	public void updateUsersdata(String usersdataId, String login, String fullname, String birthday, String adress, String phone, String gender);
	public void deleteUsersdata(String userdataId);
	public void createUsersdata(String login, String fullname, String birthday, String adress, String phone, String gender);
	
	public void createPosition(String position);
	public void updatePosition(String positionId, String position);
	public void deletePosition(String positionId);
	
	public void createEmployee(String login, String position, String location);
	public void updateEmployee(String employeeId,String login, String position, String location);
	public void deleteEmployee(String employeeId);
	
	public void createTimeTable(String employeeIdTimeTable, String dateTimeTable, String time,String orderId);
	public void updateTimeTable(String timeTableId,String employeeIdTimeTable, String dateTimeTable, String timeTimeTable,String orderId);
	public void deleteTimeTable(String timeTableId);
	
	public void updateOrder(String orderId, String status);
	
	public void createServicesToSttions(String stationSTS, String serviceSTS);
	public void updateServicesToSttions(String servicesToStationsId, String stationSTS, String serviceSTS);
	public void deleteServicesToSttions(String servicesToStationsId);
	
	public void createPositionService(String positionPS, String servicePS);
	public void updatePositionService(String positionServiceId, String positionPS, String servicePS);
	public void deletePositionService(String positionServiceId);
	


}

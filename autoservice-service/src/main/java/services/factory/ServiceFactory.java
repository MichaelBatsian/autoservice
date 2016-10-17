package services.factory;

import services.AdminActionService;
import services.AuthService;
import services.EmailService;
import services.UsersService;

public abstract class ServiceFactory {
	
	public ServiceFactory(){
		
	}

	public abstract AuthService getAuthServiceImpl();
	public abstract UsersService getUsersServiceImpl();
	public abstract AdminActionService getAdminActionServiceImpl();
	public abstract EmailService getEmailService();
	
	public static ServiceFactory getFactory(){
		return new ServiceFactoryImpl();
	}
	
}

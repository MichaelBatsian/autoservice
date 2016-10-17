package services.factory;

import services.AdminActionService;
import services.AuthService;
import services.EmailService;
import services.UsersService;
import services.impl.AdminActionServiceImpl;
import services.impl.AuthServiceImpl;
import services.impl.EmailServiceImpl;
import services.impl.UsersServiceImpl;

public class ServiceFactoryImpl extends ServiceFactory {
	
	public ServiceFactoryImpl(){
		
	}
	
	public AuthService getAuthServiceImpl(){
		return new AuthServiceImpl();
		
	}
	
	public UsersService getUsersServiceImpl(){
		return new UsersServiceImpl();
	}

	@Override
	public AdminActionService getAdminActionServiceImpl() {
		return new AdminActionServiceImpl();
	}

	@Override
	public EmailService getEmailService() {
		return new EmailServiceImpl();
	}

	

}

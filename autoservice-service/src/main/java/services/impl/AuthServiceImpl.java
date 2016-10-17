package services.impl;

import dao.UsersDAO;
import dao.factory.DAOFactory;
import services.AuthService;


public class AuthServiceImpl implements AuthService {

	public AuthServiceImpl() {

	}

	public String getAuthorzation(String login, String password) {
		int userId;
		String role = "wrong";
		UsersDAO user = DAOFactory.getFactory().getUserDAO();
		System.out.println("Login and password: "+ user.isUser(login, password));
		if (user.isUser(login, password) == true) {
			userId = user.getUser(login).getUserId();
			role = user.getRole(userId);
			String blockedStaus  = user.getBlockedStatus(userId);
			if(blockedStaus.equals("заблокирован")){
				role = "blocked";
			}
		}
		System.out.println("роль в методе авторизации "+role);
		return role;
	}

}

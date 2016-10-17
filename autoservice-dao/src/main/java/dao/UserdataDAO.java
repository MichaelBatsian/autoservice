package dao;

import java.sql.Date;
import java.util.ArrayList;


import beans.UserdataBean;

public interface UserdataDAO {
	
	public void createUserdata(int dataUserId, String fullname, Date birthday,String adress,String phone, String gender);
	public void updateDataUserId(int userdataId, int dataUserId);
	public void updateUserdataFullname(int userdataId, String fullname);
	public void updateUserdataAdress(int userdataId,String adress);
	public void updateUserdataBirthday(int userdataId,Date birthday);
	public void updateUserdataGender(int userdataId,String gender);
	public void updateUserdataPhone(int userdataId,String phone);
	public void deleteUserdata(int userdataId); 	
	public ArrayList<UserdataBean> getUserdataNameTable();
	public String getUserName(String login);

}
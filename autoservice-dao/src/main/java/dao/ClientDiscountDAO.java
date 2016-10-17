package dao;

import java.util.ArrayList;

import beans.ClientDiscountBean;


public interface ClientDiscountDAO {
	
	public ClientDiscountBean getClientDiscount (int clientDiscount);
	public void updateClientTotalSum(int clientDiscountId,int clientTotalSum);
	public void updateClientDiscount(int discountId, int userId);
	public void createClientDiscount( int userIdClientDiscount, int discountId, int clientTotalsum);
	public void deleteClientDiscount(int clientDiscountId);
	public ClientDiscountBean getUserDiscount(int userId);
	public ArrayList<ClientDiscountBean> getClientDiscountTable();
	public ArrayList<ClientDiscountBean> getClientDiscountNameTable();
	public void updateClientAdmin(int clientDiscountId,int userId);
	public void updateClientTotalSumAdmin(int clientDiscountId,int clientTotalSum);
	public void updateDiscountAdmin(int clientDiscountId,int discountId);
	
}

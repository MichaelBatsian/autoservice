package dao;

import java.util.ArrayList;

import beans.DiscountsBean;

public interface DiscountsDAO {
	
	public ArrayList<DiscountsBean> getDiscountsTable();
	public void updateDiscount(int discountId, float newDiscount);
	public void createDiscount(float discount, int totalSum);
	public void deleteDiscount(int discountId);
	public void updateDiscountTotalSum (int discountId, int newTotalsum);
	public DiscountsBean getDiscountBean(int discountId);
}

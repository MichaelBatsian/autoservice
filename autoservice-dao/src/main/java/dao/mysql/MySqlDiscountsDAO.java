package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.DiscountsBean;
import dao.DiscountsDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlDiscountsDAO implements DiscountsDAO {

	
	public ArrayList<DiscountsBean> getDiscountsTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DiscountsBean bean;
		ArrayList<DiscountsBean> discountTable = new ArrayList<DiscountsBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM discounts");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createDiscountsBean(rs);
				discountTable.add(bean);
			}
			return discountTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	
	public DiscountsBean getDiscountBean(int discountId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DiscountsBean bean=null;
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM discounts WHERE discount_id=?");
			ps.setInt(1, discountId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				bean = ResultSetConverter.createDiscountsBean(rs);
				
			}
			
			return bean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void updateDiscount(int discountId, float newDiscount) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE discounts SET discount = ? WHERE discount_id = ?");
			ps.setFloat(1, newDiscount);
			ps.setInt(2, discountId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void updateDiscountTotalSum (int discountId, int newTotalsum) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE discounts SET totalsum = ? WHERE discount_id = ?");
			ps.setInt(1, newTotalsum);
			ps.setInt(2, discountId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	
	
	

	
	public void createDiscount(float discount, int totalSum) {
		int maxId = UtilsDAO.getMaxTableId("discount_id", "discounts");
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO discounts(discount_id, discount, totalsum) VALUES(?,?,?)");
			ps.setInt(1, maxId);
			ps.setFloat(2, discount);
			ps.setInt(3, totalSum);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void deleteDiscount(int discountId) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM discounts WHERE discount_id=?");
			ps.setInt(1, discountId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

}

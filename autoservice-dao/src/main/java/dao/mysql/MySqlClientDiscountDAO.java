package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ClientDiscountBean;
import dao.ClientDiscountDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlClientDiscountDAO implements ClientDiscountDAO {

	public ClientDiscountBean getClientDiscount (int clientDiscount){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ClientDiscountBean bean=null;
		try {
			cn= ConnectionPool.getConnection();
			ps=cn.prepareStatement("SELECT user_id, discount_id, client_totalsum FROM client_discount where"
					+ "client_discount_id="+clientDiscount);
			rs=ps.executeQuery();
			if(rs.next()){
			bean = ResultSetConverter.createClientDiscountBean(rs);
			}
			return bean;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public void updateClientTotalSum(int clientDiscountId,int clientTotalSum){
		Connection cn = null;
		PreparedStatement ps = null;
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE client_discount SET client_totalsum=client_totalsum+? WHERE client_discount_id=?");
			ps.setInt(1, clientTotalSum);
			ps.setInt(2,clientDiscountId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public void updateClientTotalSumAdmin(int clientDiscountId,int clientTotalSum){
		Connection cn = null;
		PreparedStatement ps = null;
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE client_discount SET client_totalsum=? WHERE client_discount_id=?");
			ps.setInt(1, clientTotalSum);
			ps.setInt(2,clientDiscountId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	
	public void updateDiscountAdmin(int clientDiscountId,int discountId){
		Connection cn = null;
		PreparedStatement ps = null;
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE client_discount SET discount_id=? WHERE client_discount_id=?");
			ps.setInt(1, discountId);
			ps.setInt(2,clientDiscountId);
			ps.executeUpdate(); 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public void updateClientAdmin(int clientDiscountId,int userId){
		Connection cn = null;
		PreparedStatement ps = null;
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE client_discount SET user_id=? WHERE client_discount_id=?");
			ps.setInt(1, userId);
			ps.setInt(2,clientDiscountId);
			ps.executeUpdate(); 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			ConnectionPool.closeConnection(cn);
		}
	}	
		
	public void createClientDiscount( int userIdClientDiscount, int discountId, int clientTotalsum){
		System.out.println("userIdClientDiscount in method createClientDiscount "+userIdClientDiscount);
		Connection cn = null;
		PreparedStatement ps = null;
		int clientDiscountId = UtilsDAO.getMaxTableId("client_discount_id", "client_discount");
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO autoservice_db.client_discount (client_discount_id, user_id, discount_id, client_totalsum)"
					+ " VALUES (?,?,?,?)");
			ps.setInt(1,clientDiscountId);
			ps.setInt(2, userIdClientDiscount);
			ps.setInt(3, discountId);
			ps.setInt(4, clientTotalsum);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	public void deleteClientDiscount(int clientDiscountId){
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn=ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM client_discount WHERE client_discount_id=?");
			ps.setInt(1, clientDiscountId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public ArrayList<ClientDiscountBean> getClientDiscountTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ClientDiscountBean bean;
		ArrayList<ClientDiscountBean> clientDiscountTable = new ArrayList<ClientDiscountBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM client_discount");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createClientDiscountBean(rs);
				clientDiscountTable.add(bean);
			}
			return clientDiscountTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public ArrayList<ClientDiscountBean> getClientDiscountNameTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ClientDiscountBean bean;
		ArrayList<ClientDiscountBean> clientDiscountTable = new ArrayList<ClientDiscountBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT client_discount_id, login, discount, client_totalsum FROM client_discount,users,discounts "
					+ "WHERE client_discount.user_id=users.user_id AND client_discount.discount_id=discounts.discount_id");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createClientDiscountNameBean(rs);
				clientDiscountTable.add(bean);
			}
			return clientDiscountTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void updateClientDiscount(int discountId, int userId) {
		Connection cn = null;
		PreparedStatement ps = null;
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE client_discount SET discount_id=? WHERE user_id=?");
			ps.setFloat(1, discountId);
			ps.setInt(2,userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	
	public ClientDiscountBean getUserDiscount(int userId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ClientDiscountBean clientDiscountBean = new ClientDiscountBean();
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT client_discount.discount_id, discounts.discount, client_discount.client_totalsum FROM client_discount, discounts "
					+ "WHERE client_discount.discount_id=discounts.discount_id AND user_id=?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				float discount = rs.getFloat("discounts.discount");
				int sum  =  rs.getInt("client_discount.client_totalsum");
				int discountId = rs.getInt("client_discount.discount_id");
				System.out.println("clientDiscountBean "+discount+"  "+sum);
				clientDiscountBean.setDiscount(discount);
				clientDiscountBean.setClientTotalSum(sum);
				clientDiscountBean.setDiscountId(discountId);
			}
			return clientDiscountBean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

}

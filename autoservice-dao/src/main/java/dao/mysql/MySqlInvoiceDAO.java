package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.InvoiceBean;
import dao.InvoiceDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlInvoiceDAO implements InvoiceDAO{

	
	public void createInvoice(boolean status) {
		int maxUserId = UtilsDAO.getMaxTableId("invoice_id", "invoice");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO invoice(invoice_id, status) VALUES(?,?)");
			ps.setInt(1, maxUserId);
			ps.setBoolean(2, status);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateInvoice(boolean status) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE invoice SET status = ? WHERE invoice_id = ?");
			ps.setBoolean(1, status);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void deleteInvoice(int invoiceId) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM invoice WHERE invoice_id=?");
			ps.setInt(1, invoiceId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public ArrayList<InvoiceBean> getInvoiceTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		InvoiceBean bean;
		ArrayList<InvoiceBean> invoiceTable = new ArrayList<InvoiceBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM invoice");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createInvoiceBean(rs);
				invoiceTable.add(bean);
			}
			return invoiceTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
			}
	}
}

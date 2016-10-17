package dao;

import java.util.ArrayList;

import beans.InvoiceBean;


public interface InvoiceDAO {
	
	public void createInvoice(boolean status);
	public void updateInvoice(boolean status);
	public void deleteInvoice(int invoiceId);
	ArrayList<InvoiceBean> getInvoiceTable();
	
}

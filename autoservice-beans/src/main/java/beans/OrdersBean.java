package beans;

import java.sql.Date;
import java.sql.Time;

public class OrdersBean {
	
	int orderId;
	int userIdOrder;
	int serviceIdOrder;
	String status;
	int currentTotalsum;
	int discountPrice;
	int invoiceId;
	String role;
	String login;
	
	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}


	String fullname;
	String stationLocation;
	String service;
	int price;
	Time time;
	int manHours;
	Date date;
	int timeTableIdOrder;
	
	public int getCurrentTotalsum() {
		return currentTotalsum;
	}

	public void setCurrentTotalsum(int currentTotalsum) {
		this.currentTotalsum = currentTotalsum;
	}
	
	public int getManHours() {
		return manHours;
	}

	public void setManHours(int manHours) {
		this.manHours = manHours;
	}


	public Time addIntHoursToTime(){
		String [] timeStr = time.toString().split(":");
		int hours = Integer.parseInt(timeStr[0]);
		String minutes = timeStr[1];
		String seconds = timeStr[2];
		int newHours=hours+manHours;
		StringBuilder sumHours = new StringBuilder();
		sumHours.append(newHours);
		sumHours.append(":");
		sumHours.append(minutes);
		sumHours.append(":");
		sumHours.append(seconds);
		time = Time.valueOf(sumHours.toString());
		return time;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public OrdersBean(){
		
	}
	public String getStationLocation() {
		return stationLocation;
	}


	public void setStationLocation(String stationLocation) {
		this.stationLocation = stationLocation;
	}


	
	

	@Override
	public String toString() {
		return "OrdersBean [orderId=" + orderId + ", userIdOrder=" + userIdOrder + ", serviceIdOrder=" + serviceIdOrder
				+ ", status=" + status + ", currentTotalsum=" + currentTotalsum + ", discountPrice=" + discountPrice
				+ ", invoiceId=" + invoiceId + ", role=" + role + ", login=" + login + ", fullname=" + fullname
				+ ", stationLocation=" + stationLocation + ", service=" + service + ", price=" + price + ", time="
				+ time + ", manHours=" + manHours + ", date=" + date + ", timeTableIdOrder=" + timeTableIdOrder + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentTotalsum;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + discountPrice;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + invoiceId;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + manHours;
		result = prime * result + orderId;
		result = prime * result + price;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + serviceIdOrder;
		result = prime * result + ((stationLocation == null) ? 0 : stationLocation.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + timeTableIdOrder;
		result = prime * result + userIdOrder;
		return result;
	}


	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdersBean other = (OrdersBean) obj;
		if (currentTotalsum != other.currentTotalsum)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (discountPrice != other.discountPrice)
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (invoiceId != other.invoiceId)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (manHours != other.manHours)
			return false;
		if (orderId != other.orderId)
			return false;
		if (price != other.price)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (serviceIdOrder != other.serviceIdOrder)
			return false;
		if (stationLocation == null) {
			if (other.stationLocation != null)
				return false;
		} else if (!stationLocation.equals(other.stationLocation))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (timeTableIdOrder != other.timeTableIdOrder)
			return false;
		if (userIdOrder != other.userIdOrder)
			return false;
		return true;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getUserIdOrder() {
		return userIdOrder;
	}


	public void setUserIdOrder(int userIdOrder) {
		this.userIdOrder = userIdOrder;
	}


	public int getServiceIdOrder() {
		return serviceIdOrder;
	}


	public void setServiceIdOrder(int serviceIdOrder) {
		this.serviceIdOrder = serviceIdOrder;
	}


	public int getTimeTableIdOrder() {
		return timeTableIdOrder;
	}


	public void setTimeTableIdOrder(int timeTableIdOrder) {
		this.timeTableIdOrder = timeTableIdOrder;
	}


	public int getInvoiceId() {
		return invoiceId;
	}


	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


	public Time getTime() {
		return time;
	}


	public void setTime(Time time) {
		this.time = time;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

}

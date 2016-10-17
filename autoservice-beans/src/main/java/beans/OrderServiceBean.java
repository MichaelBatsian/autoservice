package beans;

import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;

public class OrderServiceBean {

	int orderId;
	String service;
	int price;
	Date date;
	Time time;
	Time endTime;
	String fullname;
	int manHours;
	String location;
	float discount;
	int currentTotalSum;
	
	public OrderServiceBean() {
		
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
	
	public int getDiscountPrice(){
		int discountPrice = 0;
		discountPrice = (int) (price-price*discount);
		return discountPrice;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getManHours() {
		return manHours;
	}

	public void setManHours(int manHours) {
		this.manHours = manHours;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getCurrentTotalSum() {
		return currentTotalSum;
	}

	public void setCurrentTotalSum(int currentTotalSum) {
		this.currentTotalSum = currentTotalSum;
	}
	
	public String getPercentFormat(){
		DecimalFormat df =new DecimalFormat("0.0%");
		return df.format(discount);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentTotalSum;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + Float.floatToIntBits(discount);
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + manHours;
		result = prime * result + orderId;
		result = prime * result + price;
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderServiceBean other = (OrderServiceBean) obj;
		if (currentTotalSum != other.currentTotalSum)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Float.floatToIntBits(discount) != Float.floatToIntBits(other.discount))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (manHours != other.manHours)
			return false;
		if (orderId != other.orderId)
			return false;
		if (price != other.price)
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderServiceBean [orderId=" + orderId + ", service=" + service + ", price=" + price + ", date=" + date
				+ ", time=" + time + ", endTime=" + endTime + ", fullname=" + fullname + ", manHours=" + manHours
				+ ", location=" + location + ", discount=" + discount + ", currentTotalSum=" + currentTotalSum + "]";
	}
	
	
	
	

}

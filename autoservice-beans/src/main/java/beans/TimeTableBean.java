package beans;
import java.sql.Date;
import java.sql.Time;

public class TimeTableBean {
	
	int timeTableId;
	int employeeId;
	String fullname;
	Date date;
	Time time;
	int orderId;
	String location;
	String position;
	
	
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Time getTimeForOrder() {
		return timeForOrder;
	}

	public void setTimeForOrder(Time timeForOrder) {
		this.timeForOrder = timeForOrder;
	}

	Time timeForOrder;
	
	public TimeTableBean(){
		
	}

	public int getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(int timeTableId) {
		this.timeTableId = timeTableId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getStringOrder(){
		if(orderId>1){
			return String.valueOf(orderId);
		}
		return "свободен";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + orderId;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((timeForOrder == null) ? 0 : timeForOrder.hashCode());
		result = prime * result + timeTableId;
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
		TimeTableBean other = (TimeTableBean) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (employeeId != other.employeeId)
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
		if (orderId != other.orderId)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (timeForOrder == null) {
			if (other.timeForOrder != null)
				return false;
		} else if (!timeForOrder.equals(other.timeForOrder))
			return false;
		if (timeTableId != other.timeTableId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TimeTableBean [timeTableId=" + timeTableId + ", employeeId=" + employeeId + ", fullname=" + fullname
				+ ", date=" + date + ", time=" + time + ", orderId=" + orderId + ", location=" + location
				+ ", position=" + position + ", timeForOrder=" + timeForOrder + "]";
	}

	
	
	
	
	
	
	
	
	
	

}

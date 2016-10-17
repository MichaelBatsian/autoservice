package beans;

public class ServicesBean {

	int serviceId;
	String serviceType;
	int manHours; 
	int price;


	public ServicesBean() {
		// TODO Auto-generated constructor stub
	}


	public int getServiceId() {
		return serviceId;
	}


	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}


	public String getServiceType() {
		return serviceType;
	}


	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public int getManHours() {
		return manHours;
	}


	public void setManHours(int manHours) {
		this.manHours = manHours;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + manHours;
		result = prime * result + price;
		result = prime * result + serviceId;
		result = prime * result + ((serviceType == null) ? 0 : serviceType.hashCode());
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
		ServicesBean other = (ServicesBean) obj;
		if (manHours != other.manHours)
			return false;
		if (price != other.price)
			return false;
		if (serviceId != other.serviceId)
			return false;
		if (serviceType == null) {
			if (other.serviceType != null)
				return false;
		} else if (!serviceType.equals(other.serviceType))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ServicesBean [serviceId=" + serviceId + ", serviceType=" + serviceType + ", manHours=" + manHours
				+ ", price=" + price + "]";
	}


	
	
	

}

package beans;

public class PositionServiceBean {

	int positionServiceId;
	int positionId;
	int serviceId;
	String position;
	String service;
	
	public PositionServiceBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


	public int getPositionServiceId() {
		return positionServiceId;
	}
	public void setPositionServiceId(int positionServiceId) {
		this.positionServiceId = positionServiceId;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionid) {
		this.positionId = positionid;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + positionServiceId;
		result = prime * result + positionId;
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + serviceId;
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
		PositionServiceBean other = (PositionServiceBean) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (positionServiceId != other.positionServiceId)
			return false;
		if (positionId != other.positionId)
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (serviceId != other.serviceId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "positionServiceBean [positionServiceId=" + positionServiceId + ", positionid=" + positionId
				+ ", serviceId=" + serviceId + "]";
	}
	
	

}

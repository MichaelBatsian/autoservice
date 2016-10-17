package beans;

public class ServicesToStationsBean {
	
	int serviceToStationId;
	int stationIdStS;
	int serviceIdStS;
	String location;
	String stationName;
	String service;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public ServicesToStationsBean() {
		// TODO Auto-generated constructor stub
	}

	public int getServiceToStationId() {
		return serviceToStationId;
	}

	public void setServiceToStationId(int serviceToStationId) {
		this.serviceToStationId = serviceToStationId;
	}

	public int getStationIdStS() {
		return stationIdStS;
	}

	public void setStationIdStS(int stationIdStS) {
		this.stationIdStS = stationIdStS;
	}

	public int getServiceIdStS() {
		return serviceIdStS;
	}

	public void setServiceIdStS(int serviceIdStS) {
		this.serviceIdStS = serviceIdStS;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + serviceIdStS;
		result = prime * result + serviceToStationId;
		result = prime * result + stationIdStS;
		result = prime * result + ((stationName == null) ? 0 : stationName.hashCode());
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
		ServicesToStationsBean other = (ServicesToStationsBean) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (serviceIdStS != other.serviceIdStS)
			return false;
		if (serviceToStationId != other.serviceToStationId)
			return false;
		if (stationIdStS != other.stationIdStS)
			return false;
		if (stationName == null) {
			if (other.stationName != null)
				return false;
		} else if (!stationName.equals(other.stationName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServicesToStationsBean [serviceToStationId=" + serviceToStationId + ", stationIdStS=" + stationIdStS
				+ ", serviceIdStS=" + serviceIdStS + ", location=" + location + ", stationName=" + stationName
				+ ", service=" + service + "]";
	}
	

}

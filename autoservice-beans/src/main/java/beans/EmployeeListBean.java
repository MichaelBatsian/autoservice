package beans;

public class EmployeeListBean {
	
	int employeeId;
	int userdataId;
	int userId;
	int stationId;
	int positionId;
	String fullname;
	String position;
	String login;
	String stationName;
	String location;
	

	public EmployeeListBean() {
		
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public int getUserdataId() {
		return userdataId;
	}


	public void setUserdataId(int userdataId) {
		this.userdataId = userdataId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getStationId() {
		return stationId;
	}


	public void setStationId(int stationId) {
		this.stationId = stationId;
	}


	public int getPositionId() {
		return positionId;
	}


	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getStationName() {
		return stationName;
	}


	public void setStationName(String stationName) {
		this.stationName = stationName;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + positionId;
		result = prime * result + stationId;
		result = prime * result + ((stationName == null) ? 0 : stationName.hashCode());
		result = prime * result + userId;
		result = prime * result + userdataId;
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
		EmployeeListBean other = (EmployeeListBean) obj;
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
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (positionId != other.positionId)
			return false;
		if (stationId != other.stationId)
			return false;
		if (stationName == null) {
			if (other.stationName != null)
				return false;
		} else if (!stationName.equals(other.stationName))
			return false;
		if (userId != other.userId)
			return false;
		if (userdataId != other.userdataId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "EmployeeListBean [employeeId=" + employeeId + ", userdataId=" + userdataId + ", userId=" + userId
				+ ", stationId=" + stationId + ", positionId=" + positionId + ", fullname=" + fullname + ", position="
				+ position + ", login=" + login + ", stationName=" + stationName + ", location=" + location + "]";
	}

	
	
	

}

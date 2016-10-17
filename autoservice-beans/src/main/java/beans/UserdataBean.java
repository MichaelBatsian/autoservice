package beans;

import java.sql.Date;

public class UserdataBean {

		int userdataId;
		int dataUserId;
		String login;
		String fullname;
		Date birthday;
		String adress;
		String phone;
		String gender;
		
		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public UserdataBean(){
			
		}

		public int getUserdataId() {
			return userdataId;
		}

		public void setUserdataId(int userdataId) {
			this.userdataId = userdataId;
		}

		public int getDataUserId() {
			return dataUserId;
		}

		public void setDataUserId(int dataUserId) {
			this.dataUserId = dataUserId;
		}

		public String getFullname() {
			return fullname;
		}

		public void setFullname(String fullname) {
			this.fullname = fullname;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public String getAdress() {
			return adress;
		}

		public void setAdress(String adress) {
			this.adress = adress;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((adress == null) ? 0 : adress.hashCode());
			result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
			result = prime * result + dataUserId;
			result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
			result = prime * result + ((gender == null) ? 0 : gender.hashCode());
			result = prime * result + ((login == null) ? 0 : login.hashCode());
			result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
			UserdataBean other = (UserdataBean) obj;
			if (adress == null) {
				if (other.adress != null)
					return false;
			} else if (!adress.equals(other.adress))
				return false;
			if (birthday == null) {
				if (other.birthday != null)
					return false;
			} else if (!birthday.equals(other.birthday))
				return false;
			if (dataUserId != other.dataUserId)
				return false;
			if (fullname == null) {
				if (other.fullname != null)
					return false;
			} else if (!fullname.equals(other.fullname))
				return false;
			if (gender == null) {
				if (other.gender != null)
					return false;
			} else if (!gender.equals(other.gender))
				return false;
			if (login == null) {
				if (other.login != null)
					return false;
			} else if (!login.equals(other.login))
				return false;
			if (phone == null) {
				if (other.phone != null)
					return false;
			} else if (!phone.equals(other.phone))
				return false;
			if (userdataId != other.userdataId)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "UserdataBean [userdataId=" + userdataId + ", dataUserId=" + dataUserId + ", login=" + login
					+ ", fullname=" + fullname + ", birthday=" + birthday + ", adress=" + adress + ", phone=" + phone
					+ ", gender=" + gender + "]";
		}
		
		
		
		
	

}

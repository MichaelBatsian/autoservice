package beans;

import java.text.DecimalFormat;

public class ClientDiscountBean {

	int clientDiscountId;
	int userId;
	int discountId;
	int clientTotalSum;
	int bonus;
	String login;
	float discount;
	
	public ClientDiscountBean() {
		
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public float getDiscount() {
		return discount;
	}
	public String getPercentFormat(){
		DecimalFormat df =new DecimalFormat("0.0%");
		return df.format(discount);
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	
	public int getClientDiscountId() {
		return clientDiscountId;
	}

	public void setClientDiscountId(int clientDiscountId) {
		this.clientDiscountId = clientDiscountId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}

	public int getClientTotalSum() {
		return clientTotalSum;
	}

	public void setClientTotalSum(int clientTotalSum) {
		this.clientTotalSum = clientTotalSum;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bonus;
		result = prime * result + clientDiscountId;
		result = prime * result + clientTotalSum;
		result = prime * result + Float.floatToIntBits(discount);
		result = prime * result + discountId;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + userId;
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
		ClientDiscountBean other = (ClientDiscountBean) obj;
		if (bonus != other.bonus)
			return false;
		if (clientDiscountId != other.clientDiscountId)
			return false;
		if (clientTotalSum != other.clientTotalSum)
			return false;
		if (Float.floatToIntBits(discount) != Float.floatToIntBits(other.discount))
			return false;
		if (discountId != other.discountId)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientDiscountBean [clientDiscountId=" + clientDiscountId + ", userId=" + userId + ", discountId="
				+ discountId + ", clientTotalSum=" + clientTotalSum + ", bonus=" + bonus + ", login=" + login
				+ ", discount=" + discount + "]";
	}
	
	

}

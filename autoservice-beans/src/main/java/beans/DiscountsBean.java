package beans;

import java.text.DecimalFormat;

public class DiscountsBean {
	
	int discountId;
	float discount;
	int totalSum;
	
	public DiscountsBean() {
		}

	
	
	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId (int discountId) {
		this.discountId = discountId;
	}
	

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getPercentFormat(){
		DecimalFormat df =new DecimalFormat("0.0%");
		return df.format(discount);
	}

	public int getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(int totalSum) {
		this.totalSum = totalSum;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(discount);
		result = prime * result + discountId;
		result = prime * result + totalSum;
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
		DiscountsBean other = (DiscountsBean) obj;
		if (Float.floatToIntBits(discount) != Float.floatToIntBits(other.discount))
			return false;
		if (discountId != other.discountId)
			return false;
		if (totalSum != other.totalSum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "discountsBean [discountId=" + discountId + ", discount=" + discount + ", totalSum=" + totalSum + "]";
	}
	
}

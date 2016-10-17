package beans;

public class topicGroup {

	int topicGroupId;
	String topicGroup;
	
	public topicGroup() {
		// TODO Auto-generated constructor stub
	}

	public int getTopicGroupId() {
		return topicGroupId;
	}

	public void setTopicGroupId(int topicGroupId) {
		this.topicGroupId = topicGroupId;
	}

	public String getTopicGroup() {
		return topicGroup;
	}

	public void setTopicGroup(String topicGroup) {
		this.topicGroup = topicGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((topicGroup == null) ? 0 : topicGroup.hashCode());
		result = prime * result + topicGroupId;
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
		topicGroup other = (topicGroup) obj;
		if (topicGroup == null) {
			if (other.topicGroup != null)
				return false;
		} else if (!topicGroup.equals(other.topicGroup))
			return false;
		if (topicGroupId != other.topicGroupId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "topicGroup [topicGroupId=" + topicGroupId + ", topicGroup=" + topicGroup + "]";
	}
	
	

}

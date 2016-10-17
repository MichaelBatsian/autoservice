package beans;

import java.sql.Date;
import java.sql.Time;

public class MessagesBean {
	
	int messageId;
	int userIdMessage;
	String message;
	int topicIdMessage;
	String login;
	String fullname;
	String role;
	Date messageDateTime;
	Time messageTime;
	
	 
	public Time getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Time messageTime) {
		this.messageTime = messageTime;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getMessageDateTime() {
		return messageDateTime;
	}

	public void setMessageDateTime(Date messageDateTime) {
		this.messageDateTime = messageDateTime;
	}

	String topic;
	
	public MessagesBean() {
		
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getUserIdMessage() {
		return userIdMessage;
	}

	public void setUserIdMessage(int userIdMessage) {
		this.userIdMessage = userIdMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTopicIdMessage() {
		return topicIdMessage;
	}

	public void setTopicIdMessage(int topicIdMessage) {
		this.topicIdMessage = topicIdMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((messageDateTime == null) ? 0 : messageDateTime.hashCode());
		result = prime * result + messageId;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		result = prime * result + topicIdMessage;
		result = prime * result + userIdMessage;
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
		MessagesBean other = (MessagesBean) obj;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageDateTime == null) {
			if (other.messageDateTime != null)
				return false;
		} else if (!messageDateTime.equals(other.messageDateTime))
			return false;
		if (messageId != other.messageId)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		if (topicIdMessage != other.topicIdMessage)
			return false;
		if (userIdMessage != other.userIdMessage)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MessagesBean [messageId=" + messageId + ", userIdMessage=" + userIdMessage + ", message=" + message
				+ ", topicIdMessage=" + topicIdMessage + ", login=" + login + ", fullname=" + fullname + ", role="
				+ role + ", messageDateTime=" + messageDateTime + ", topic=" + topic + "]";
	}
	
	

}

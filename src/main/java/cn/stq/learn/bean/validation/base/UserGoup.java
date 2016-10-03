package cn.stq.learn.bean.validation.base;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class UserGoup {
	@NotBlank
	private String groupName;
	@Valid	//级联验证
	@NotNull
	private List<User> userGroup ;
	
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public List<User> getUserGroup() {
		return userGroup;
	}


	public void setUserGroup(List<User> userGroup) {
		this.userGroup = userGroup;
	}


	@Override
	public String toString() {
		return "UserGoup [groupName=" + groupName + ", userGroup=" + userGroup + "]";
	}
	
	
}

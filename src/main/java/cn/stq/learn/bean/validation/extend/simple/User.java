package cn.stq.learn.bean.validation.extend.simple;

public class User {
	
	@Password(pattern="\\w{10,20}",message="密码长度在10-20位之间")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

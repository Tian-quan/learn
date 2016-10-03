package cn.stq.learn.bean.validation.base;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
/**
 *
 * @update 
 * @create 2016年10月3日 下午3:42:42
 * @author tianquan.shi<tianquan.shi@msxf.com>
 */
public class User {

	@NotBlank(message = "用户名不能为空")
	private String username;

	@NotBlank
	@Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}",message="密码必须是8位字母和数字组成")
	private String password;

	@Min(value = 10, message = "年龄的最小值为10",groups={AgeValid.class})
	private int age;

	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", age=" + age + "]";
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getAge() {
		return age;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

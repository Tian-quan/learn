package cn.stq.learn.annotation.base;

/**
 * 
 * Description: <br/>
 * 水果 - 苹果
 * <br/><br/>Author: tianquan.shi<tianquan.shi@msxf.com> 
 * <br/>Create Time: 2016年10月3日 上午11:59:38
 */
public class Apple {
	
	@FruitName("apple")
	private String name;
	@FruitColor
	private String color;
	@FruitProvider(id=1,name="西安红富士",address="西安市碑林区")
	private String provider;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	@Override
	public String toString() {
		return "Apple [name=" + name + ", color=" + color + ", provider=" + provider + "]";
	}
}

package cn.stq.learn.bean.validation.extend.map;

import java.util.Map;

import cn.stq.learn.bean.validation.extend.map.MapExist.MapExistEntry;

public class User {
	
	@MapExist
	({
		@MapExistEntry(key="key1",message="必须存在key1"),
		@MapExistEntry(key="key2",message="必须存在key2")
	})
	private Map<String,String> map;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
}

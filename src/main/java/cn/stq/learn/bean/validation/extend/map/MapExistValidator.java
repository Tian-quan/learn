package cn.stq.learn.bean.validation.extend.map;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cn.stq.learn.bean.validation.extend.map.MapExist.MapExistEntry;


public class MapExistValidator implements ConstraintValidator<MapExistEntry, Map<String,String>> {
	private MapExistEntry entry;
	
	@Override
	public void initialize(MapExistEntry constraintAnnotation) {
		entry=constraintAnnotation;
	}

	@Override
	public boolean isValid(Map<String,String> valueMap, ConstraintValidatorContext context) {
		return valueMap.keySet().contains(entry.key());
	}

}

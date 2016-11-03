package cn.stq.learn.bean.validation.extend.map;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cn.stq.learn.bean.validation.extend.map.MapExist.Entry;


public class MapExistEntryValidator implements ConstraintValidator<Entry, Map<String,String>> {
	private Entry entry;
	
	@Override
	public void initialize(Entry constraintAnnotation) {
		entry=constraintAnnotation;
	}

	@Override
	public boolean isValid(Map<String,String> valueMap, ConstraintValidatorContext context) {
		return valueMap.keySet().contains(entry.key());
	}

}

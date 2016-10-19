package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Module;
@Component
@Transactional
public class ModuleToStringConverter implements Converter<Module, String> {
	@Override
	public String convert(Module course) {
		String result;
		if (course == null) {
			result = null;
		} else {
			result = String.valueOf(course.getId());
		}
		return result;
	}
}

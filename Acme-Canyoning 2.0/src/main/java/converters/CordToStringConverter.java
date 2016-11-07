package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Cord;

@Component
@Transactional
public class CordToStringConverter implements Converter<Cord, String> {
	@Override
	public String convert(Cord cord) {
		String result;
		if (cord == null) {
			result = null;
		} else {
			result = String.valueOf(cord.getId());
		}
		return result;
	}

}

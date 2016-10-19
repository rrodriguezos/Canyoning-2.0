package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Canyon;

@Component
@Transactional
public class CanyonToStringConverter implements Converter<Canyon, String> {

	@Override
	public String convert(Canyon canyon) {
		String result;
		if (canyon == null) {
			result = null;
		} else {
			result = String.valueOf(canyon.getId());
		}
		return result;
	}

}

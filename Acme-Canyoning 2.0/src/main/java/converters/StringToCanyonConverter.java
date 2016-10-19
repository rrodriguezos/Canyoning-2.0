package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CanyonRepository;

import domain.Canyon;

@Component 
@Transactional 
public class StringToCanyonConverter implements Converter<String, Canyon> {

	@Autowired
	CanyonRepository canyonRepository;

	@Override
	public Canyon convert(String text) {
		Canyon result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = canyonRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}

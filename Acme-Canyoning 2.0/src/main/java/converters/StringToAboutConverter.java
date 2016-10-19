package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AboutRepository;
import domain.About;

@Component
@Transactional
public class StringToAboutConverter implements Converter<String, About> {

	@Autowired	AboutRepository aboutRepository;

	@Override
	public About convert(String text) {
		About result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = aboutRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}

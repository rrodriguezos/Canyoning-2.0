package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import repositories.KayakRepository;


import domain.Kayak;

@Component
@Transactional
public class StringToKayakConverter implements Converter<String, Kayak> {
	
	@Autowired	KayakRepository kayakRepository;

	@Override
	public Kayak convert(String text) {
		Kayak result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = kayakRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}


}

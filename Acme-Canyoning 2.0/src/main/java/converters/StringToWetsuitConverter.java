package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.WetsuitRepository;

import domain.Wetsuit;

@Component
@Transactional
public class StringToWetsuitConverter implements Converter<String, Wetsuit> {
	@Autowired
	WetsuitRepository wetsuitRepository;

	@Override
	public Wetsuit convert(String text) {
		Wetsuit result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = wetsuitRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}

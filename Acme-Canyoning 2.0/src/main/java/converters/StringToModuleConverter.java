package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ModuleRepository;
import domain.Module;

@Component
@Transactional
public class StringToModuleConverter implements Converter<String, Module> {
	@Autowired
	ModuleRepository moduleRepository;

	@Override
	public Module convert(String text) {
		Module result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = moduleRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}

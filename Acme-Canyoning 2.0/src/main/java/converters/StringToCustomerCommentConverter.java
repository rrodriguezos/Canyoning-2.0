package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomerCommentRepository;
import domain.CustomerComment;

@Component
@Transactional
public class StringToCustomerCommentConverter implements
		Converter<String, CustomerComment> {

	@Autowired
	CustomerCommentRepository customerCommentRepository;

	@Override
	public CustomerComment convert(String text) {
		CustomerComment result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = customerCommentRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}

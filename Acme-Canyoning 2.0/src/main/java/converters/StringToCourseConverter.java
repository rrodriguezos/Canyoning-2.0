package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CourseRepository;
import domain.Course;

@Component
@Transactional
public class StringToCourseConverter implements Converter<String, Course> {
	@Autowired
	CourseRepository courseRepository;

	@Override
	public Course convert(String text) {
		Course result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = courseRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}

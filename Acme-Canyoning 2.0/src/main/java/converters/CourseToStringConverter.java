package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Course;

@Component
@Transactional
public class CourseToStringConverter implements Converter<Course, String> {
	@Override
	public String convert(Course course) {
		String result;
		if (course == null) {
			result = null;
		} else {
			result = String.valueOf(course.getId());
		}
		return result;
	}
}

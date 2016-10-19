package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.TrainerCommentRepository;
import domain.TrainerComment;

@Component
@Transactional
public class StringToTrainerCommentConverter implements
		Converter<String, TrainerComment> {
	@Autowired
	TrainerCommentRepository trainerCommentRepository;

	@Override
	public TrainerComment convert(String text) {
		TrainerComment result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = trainerCommentRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.TrainerComment;
@Component
@Transactional
public class TrainerCommentToStringConverter implements Converter<TrainerComment, String> {

	

	@Override 
	public String convert(TrainerComment organiserComment){ 
		String result; 
		if(organiserComment == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(organiserComment.getId()); 
		} 
		return result; 
	} 
}

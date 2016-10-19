package converters;

import org.springframework.core.convert.converter.Converter;

import domain.OrganiserComment;

public class OrganiserCommentToStringConverter implements Converter<OrganiserComment, String> {

	

	@Override 
	public String convert(OrganiserComment organiserComment){ 
		String result; 
		if(organiserComment == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(organiserComment.getId()); 
		} 
		return result; 
	} 
}

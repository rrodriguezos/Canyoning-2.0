package converters;

import org.springframework.core.convert.converter.Converter;

import domain.CustomerComment;

public class CustomerCommentToStringConverter implements Converter<CustomerComment, String> {

	
	@Override 
	public String convert(CustomerComment customerComment){ 
		String result; 
		if(customerComment == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(customerComment.getId()); 
		} 
		return result; 
	} 
}

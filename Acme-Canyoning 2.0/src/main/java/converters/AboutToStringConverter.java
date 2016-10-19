package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.About;

@Component 
@Transactional 
public class AboutToStringConverter implements Converter<About, String>{ 
	
	@Override 
	public String convert(About about){ 
		String result; 
		if(about == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(about.getId()); 
		} 
		return result; 
	} 

}

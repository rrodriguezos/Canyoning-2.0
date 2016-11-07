package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import domain.Kayak;

@Component 
@Transactional 
public class KayakToStringConverter implements Converter<Kayak, String>{ 
	@Override 
	public String convert(Kayak kayak){ 
		String result; 
		if(kayak == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(kayak.getId()); 
		} 
		return result; 
	} 

}

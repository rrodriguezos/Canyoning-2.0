package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Kayak;
import domain.Wetsuit;

@Component 
@Transactional
public class WetsuitToStringConverter implements Converter<Wetsuit, String>{ 
	
	@Override 
	public String convert(Wetsuit wetsuit){ 
		String result; 
		if(wetsuit == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(wetsuit.getId()); 
		} 
		return result; 
	} 

}

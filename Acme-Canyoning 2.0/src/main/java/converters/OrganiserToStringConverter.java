package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Organiser;

@Component
@Transactional
public class OrganiserToStringConverter implements Converter<Organiser, String>{

	@Override
	public String convert(Organiser user){
		String result;
		
		if(user == null){
			result = null;
		}else{
			result = String.valueOf(user.getId());
		}
		
		return result;
	}
}

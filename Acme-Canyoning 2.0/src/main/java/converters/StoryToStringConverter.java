package converters;

import org.springframework.core.convert.converter.Converter;

import domain.Story;

public class StoryToStringConverter implements Converter<Story, String>{ 
	
	@Override 
	public String convert(Story story){ 
		String result; 
		if(story == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(story.getId()); 
		} 
		return result; 
	} 

}


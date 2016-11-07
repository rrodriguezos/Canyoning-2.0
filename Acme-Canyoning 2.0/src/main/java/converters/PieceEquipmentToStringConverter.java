package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.PieceEquipment;

@Component 
@Transactional 
public class PieceEquipmentToStringConverter implements Converter<PieceEquipment, String>{ 
	
	@Override 
	public String convert(PieceEquipment pieceEquipment){ 
		String result; 
		if(pieceEquipment == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(pieceEquipment.getId()); 
		} 
		return result; 
	} 
	

}

package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PieceEquipmentRepository;
import domain.PieceEquipment;

@Component
@Transactional
public class StringToPieceEquipmentConverter implements
		Converter<String, PieceEquipment> {

	@Autowired
	PieceEquipmentRepository pieceEquipmentRepository;

	@Override
	public PieceEquipment convert(String text) {
		PieceEquipment result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = pieceEquipmentRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}

package com.gdtel.common.core.convert;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

@Log4j2
public class DateConverter implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		log.info("-----------------convert---------------");
		log.info("-----------------convert---------------");
		log.info("convert Date:{}",source);
		if (StringUtils.isBlank(source)) {
			return null;
		}
		source = source.trim();
		
		try {
			return DateUtils.parseDate(source);
		} catch (ParseException e) {
			log.error("", e);
			throw new RuntimeException(String.format("parser %s to Date fail", source));
		}
	}

}
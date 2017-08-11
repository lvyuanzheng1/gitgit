package cn.itcast.action.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 *S: source 源
 *T: target 目标 
 *
 */
public class CustomStringToDateConverter implements Converter<String,Date> {

	@Override
	public Date convert(String dateStr) {
		// TODO Auto-generated method stub
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

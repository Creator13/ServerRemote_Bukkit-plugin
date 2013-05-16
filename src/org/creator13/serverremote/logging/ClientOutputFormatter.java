package org.creator13.serverremote.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ClientOutputFormatter extends Formatter {

	private static final String SPACE = " ";
	
	@Override
	public String format(LogRecord rec) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(calcDate(rec.getMillis()));
		sb.append(SPACE);
		sb.append(rec.getLevel().getLocalizedName());
		sb.append(SPACE);
		sb.append(rec.getMessage());
		
		return sb.toString();
		
	}
	
	private String calcDate(long millisecs) {
		SimpleDateFormat date_format = new SimpleDateFormat("HH:mm:ss");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
		
	}
	
}

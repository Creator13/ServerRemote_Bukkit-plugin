package org.creator13.serverremote.logging;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ClientOutputHandler extends Handler {

	private PrintWriter out;
	
	public ClientOutputHandler(OutputStream out) {
		this.out = new PrintWriter(out);
		setFormatter(new ClientOutputFormatter());
		
	}
	
	@Override
	public void close() throws SecurityException {
		out.close();
		
	}

	@Override
	public void flush() {
		out.flush();
		
	}

	@Override
	public void publish(LogRecord record) {
		out.println(getFormatter().format(record));
		
	}

}

package com.sac.network.programing.nio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IO_basedClient {
	public static void whois(String query, String server) throws IOException {
		Socket sock = new Socket(server, 13);
		int c = 0;

		OutputStream os = sock.getOutputStream();
		InputStream is = sock.getInputStream();
		query += "\r\n";
		os.write(query.getBytes("iso8859_1"));

		while (c != -1) {
			c = is.read();
			if (c != -1)
				System.out.println((char) c);
		}
	}

	public static void main(String[] args) throws Exception {
		String hostname = "localhost";
		whois("Hello Server", hostname);
	}
}
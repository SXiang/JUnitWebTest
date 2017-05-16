package common.source;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


public class PingUtility {
	public static boolean isEndPointAlive(String host, Integer port, Integer timeout) {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(host, port), timeout);
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
}

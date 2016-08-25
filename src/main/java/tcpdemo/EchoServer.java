package tcpdemo;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author rasmu
 */
public class EchoServer {

    static String ip = "localhost";
    static int port = 8080;

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }

        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        System.out.println("Server started, listening on " + port + ", bound to " + ip);

        Socket socket = ss.accept();
        handleClient(socket);
        System.out.println("New client connected");
    }

    public static void handleClient(Socket s) {
        try {
            Scanner scan = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            String msg = "";
            while (!msg.equals("STOP")) {
                msg = scan.nextLine();
                pw.println(msg.toUpperCase());
            }
            scan.close();
            pw.close();
            s.close();
        } catch (IOException x) {
            System.out.println("blabla");
        }

    }

}

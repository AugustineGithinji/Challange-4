import java.net.*;
import java.io.*;

public class DiscoveryServer {
    
    public static void main(String[] args) throws Exception {
        int port = 8080;
        
        System.out.println("Listening on port " + port + "...");
        ServerSocket serverSocket = new ServerSocket(port);
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            String clientIP = clientSocket.getInetAddress().getHostAddress();
            System.out.println("\n=== New Connection ===");
            System.out.println("From IP: " + clientIP);
            System.out.println("From Port: " + clientSocket.getPort());
            
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            
            if (bytesRead > 0) {
                System.out.println("Received " + bytesRead + " bytes:");
                
                // Print as hex
                System.out.print("HEX: ");
                for (int i = 0; i < bytesRead; i++) {
                    System.out.printf("%02X ", buffer[i]);
                }
                System.out.println();
                
                // Print as ASCII
                System.out.print("ASCII: ");
                for (int i = 0; i < bytesRead; i++) {
                    char c = (char) buffer[i];
                    System.out.print(Character.isISOControl(c) ? '.' : c);
                }
                System.out.println();
            } else {
                System.out.println("No data received - they may just be probing the port");
            }
            
            clientSocket.close();
        }
    }
}

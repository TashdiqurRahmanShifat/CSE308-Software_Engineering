import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

class ThreadServer implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;

    public ThreadServer(Socket socket) {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                String msg = bufferedReader.readLine();
                if (msg == null) break;
                else System.out.println(msg);
            }

        } catch (SocketException e) {
            System.out.println("Server disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 22225);
            ThreadServer threadServer = new ThreadServer(socket);
            Thread t = new Thread(threadServer);
            t.start();

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            Scanner scn = new Scanner(System.in);
            while (scn.hasNextLine()) {
                String str = scn.nextLine();
                printWriter.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
import jdk.internal.util.xml.impl.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.in;

public class Server {

    private ServerSocket serverSocket;
    private Socket acceptSocket;
    private PrintStream output;
    private BufferedReader input;
    public static void main(String []args) throws IOException {
        Server main = new Server();
        main.run();

    }

    void run(){

        try {
            serverSocket = new ServerSocket(9999);
            acceptSocket =serverSocket.accept();
            output =  new PrintStream(acceptSocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(acceptSocket.getInputStream()));

            Scanner scanner = new Scanner(System.in);


            Thread thread = new Thread(){
                public void run(){
                  //  System.out.println("Thread Running");
                    while (acceptSocket.isConnected()){
                        String reply = scanner.next();
                    //    System.out.println("Server = "+reply);

                        output.println(reply);
                    }

                }
            };

            thread.start();







            Thread thread1 = new Thread(){
                public void run(){
                    //   System.out.println("Thread Running");
                    while (acceptSocket.isConnected()) {
                        try {
                            System.out.println("Server = " +input.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


                }
            };

            thread1.start();




        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

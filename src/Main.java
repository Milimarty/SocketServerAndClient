import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private Socket clientSocket;
    private PrintStream output;
    private BufferedReader input;


public static void main(String []args) throws IOException {
Main main = new Main();
main.run();

}

void run() throws IOException {

    clientSocket = new Socket("localhost" , 9999);
    Scanner scanner =new Scanner(System.in);

    output = new PrintStream(clientSocket.getOutputStream());
    input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

 //   output.println("Hellow Server");

    Thread thread1 = new Thread(){
        public void run(){
         //   System.out.println("Thread Running");
            while (clientSocket.isConnected()){
                String reply = scanner.next();
              //  System.out.println("client = "+reply);

                output.println(reply);
            }


        }
    };

    thread1.start();
    Thread thread = new Thread(){
        public void run(){
            //   System.out.println("Thread Running");
            while (clientSocket.isConnected()) {
                try {
                    System.out.println("Server = " +input.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
    };

    thread.start();
/*
    while (clientSocket.isConnected()){
        String message = input.readLine();
        System.out.println("Server = " +message);


    }*/

}

}

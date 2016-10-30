import java.io.*;
import java.util.*;
import java.net.*;
import java.lang.*;
import java.text.SimpleDateFormat;
//import javax.swing.Timer;
class Rcmdd {
  public static void main(String args[]) throws Exception {

    DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt(args[0])); //constructing a DatagramSocket
    int delay, execount, i = 0, length;
    long t;

    System.out.println("Server IP is" + InetAddress.getLocalHost() + "\n Waiting for packet\n");
    while (true) {
      String[] p = new String[4];
      byte[] receiveData = new byte[1024]; //initializing array to receive and send data for datagram
      byte[] sendData = new byte[1024];
      i = 0; // i should be made zero 
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      serverSocket.receive(receivePacket); //to receive packet from socket

      InetAddress IPAddress = receivePacket.getAddress(); // getting IP address from received packet
      int port = receivePacket.getPort(); // getting port of the client(using received packet)
      String s = new String(receivePacket.getData()); // getting the received data
      //System.out.println("output string received "+s);//sucess
      String comand = new String();
      String outserver = new String();
      StringTokenizer st = new StringTokenizer(s, " "); //used to convert thestring(received data) and splitting for getting parameters
      while (st.hasMoreTokens()) {
        p[i] = (st.nextToken());
        i++;
      }
      //System.out.println("value of i "+i);// check length
      //String ex=new String();// remove null
      if (i == 4) //if command is of two words
      {
        //System.out.println("extra"+p[3]);
        p[2] = p[2] + " " + p[3].trim();
      }
      execount = Integer.parseInt(p[0]);
      delay = Integer.parseInt(p[1]);

      comand = p[2];

      //System.out.println("the details execution time "+execount+"\nDelay "+delay+"\ncommand"+comand);
      for (i = 0; i < execount; i++) //loop as server sends datagram- execount times 
      {

        // console run and return
        System.out.println("Command from client :" + p[2]);
        Process out = Runtime.getRuntime().exec(comand); // console(comand);
        out.waitFor();
        BufferedReader r = new BufferedReader(new InputStreamReader(out.getInputStream()));
        String c = new String();
        String line = new String("");
        while ((c = r.readLine()) != null) {
          //System.out.println(c); 
          line = line + "\n" + c;
        }
        Calendar cal = Calendar.getInstance();

        //cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // time of server before sending packet
        //System.out.println( "Time (in HH:mm:ss) "+sdf.format(cal.getTime()) );
        line = line + "\nTime (in HH:mm:ss) " + sdf.format(cal.getTime());
        //System.out.println(line);//success!!:)

        sendData = line.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); // sending datagram to client
        serverSocket.send(sendPacket);

        Thread.sleep(delay); // delay
      }

    }
  }
}
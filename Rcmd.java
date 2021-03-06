import java.io.*;
import java.util.*;
import java.net.*;
import java.text.SimpleDateFormat;

class Rcmd {
  public static void main(String args[]) throws Exception {
    BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
    int portn, execount, i = 0, j;
    DatagramSocket clientSocket = new DatagramSocket();
    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];
    String send = new String();
    String s = new String(inputUser.readLine());
    String[] p = new String[6]; //for splitting the string received by user
    StringTokenizer st = new StringTokenizer(s, " ");
    while (st.hasMoreTokens()) {
      p[i] = (st.nextToken());
      i++;
    }

    InetAddress IPAddress = InetAddress.getByName(p[0]);
    portn = Integer.parseInt(p[1]);
    execount = Integer.parseInt(p[2]);
    /*System.out.println("length "+i);//check length*/ //command
    String g = new String();
    if (i == 6) //if command length is 2
    {
      g = p[4] + " " + p[5];
      //System.out.println("command "+g);//check length
      send = p[2] + " " + p[3] + " " + g;
    } else
      send = p[2] + " " + p[3] + " " + p[4];
    sendData = send.getBytes();
    //System.out.println("Bytes "+sendData);//check
    Calendar cal = Calendar.getInstance();
    cal.getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // time of client before sending packet
    System.out.println("Time (in HH:mm:ss) " + sdf.format(cal.getTime()));
    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, portn);
    //System.out.println("String "+sendData.toString());    //check 
    clientSocket.send(sendPacket);

    for (j = 0; j < execount; j++) //loop as server sends datagram- execount times 
    {
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      String m = new String(receivePacket.getData());
      System.out.println("\n\nFROM SERVER: \n" + m);
    }
    clientSocket.close(); //closing socket after getting request

  }
}
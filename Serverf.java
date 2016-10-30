import java.io.*;
import java.util.*;
import java.net.*;
import java.lang.*;
import java.text.SimpleDateFormat;
//import javax.swing.Timer;
class Serverf {
  public static void main(String args[]) throws Exception {

    DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt(args[0]));
    int delay, execount, i = 0, length;
    long t;
    /*byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];*/
    //System n=new System();0

    System.out.println("Server IP is" + InetAddress.getLocalHost() + "\n Waiting for packet\n");
    while (true) {
      String[] p = new String[4];
      byte[] receiveData = new byte[1024];
      byte[] sendData = new byte[1024];
      i = 0;
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      serverSocket.receive(receivePacket);

      InetAddress IPAddress = receivePacket.getAddress();
      int port = receivePacket.getPort();
      String s = new String(receivePacket.getData());
      //System.out.println("output string received "+s);//sucess
      String comand = new String();
      String outserver = new String();
      StringTokenizer st = new StringTokenizer(s, " ");
      while (st.hasMoreTokens()) {
        p[i] = (st.nextToken());
        i++;
      }
      //System.out.println("value of i "+i);// check length
      //String ex=new String();// remove null
      if (i == 4) {
        //System.out.println("extra"+p[3]);
        p[2] = p[2] + " " + p[3].trim();
      }
      execount = Integer.parseInt(p[0]);
      delay = Integer.parseInt(p[1]);

      /*t=cal.getTimeInMillis();
      while(cal.getTimeInMillis()<(t+delay))//adding delay
      {}*/
      /*Calendar timed = new Calendar.getInstance();// class timer
      t=timed.getTimeInMillis();
      */
      //length=p[2].length();
      //for(i=0;i<length;i++)
      comand = p[2];

      //System.out.println("the details execution time "+execount+"\nDelay "+delay+"\ncommand"+comand);
      for (i = 0; i < execount; i++) {

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
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        serverSocket.send(sendPacket);

        Thread.sleep(delay);
      }

    }
  }
}
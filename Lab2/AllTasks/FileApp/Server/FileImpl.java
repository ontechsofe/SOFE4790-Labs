import java.io.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class FileImpl extends UnicastRemoteObject
  implements FileInterface {

   private String name;

   public FileImpl(String s) throws RemoteException{
      super();
      name = s;
   }

   public byte[] downloadFile(String fileName){
      try {
         System.out.println("MESSAGE: Client ip is " + getClientHost());
         File file = new File(fileName);
         byte buffer[] = new byte[(int)file.length()];
         BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
         input.read(buffer,0,buffer.length);
         input.close();
         return(buffer);
      } catch(Exception e){
         System.out.println("FileImpl: "+e.getMessage());
         e.printStackTrace();
         return(null);
      }
   }

   public void uploadFile(byte[] content){
      try {
         System.out.println("MESSAGE: Client ip is " + getClientHost());
         File file = new File("FromClient.txt");
         BufferedOutputStream output = new
            BufferedOutputStream(new FileOutputStream(file.getName()));
         output.write(content,0,content.length);
         output.flush();
         System.out.println("MESSAGE: File Sucessfully Downloaded!");
         output.close();
      } catch(Exception e){
         System.out.println("FileImpl: "+e.getMessage());
         e.printStackTrace();
      }
   }
}
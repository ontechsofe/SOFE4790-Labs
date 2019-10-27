import java.io.*; 
import java.rmi.*;

public class FileClient{
   public static void main(String argv[]) {
      if(argv.length != 3) {
        System.out.println("Usage: java FileClient fileName machineName typeOfInteraction");
        System.exit(0);
      }
      try {
         String name = "//" + argv[1] + "/FileServer";
         FileInterface fi = (FileInterface) Naming.lookup(name);
         if (argv[2].equals("download")){
            byte[] filedata = fi.downloadFile(argv[0]);
            File file = new File(argv[0]);
            BufferedOutputStream output = new
              BufferedOutputStream(new FileOutputStream(file.getName()));
            output.write(filedata,0,filedata.length);
            output.flush();
            System.out.println("MESSAGE: File Sucessfully Downloaded!");
            output.close();
         }
         else if (argv[2].equals("upload")){
            File file = new File(argv[0]);
            byte buffer[] = new byte[(int)file.length()];
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(argv[0]));
            input.read(buffer,0,buffer.length);
            input.close();
            fi.uploadFile(buffer);
         } else {
            System.out.println("I dont know what you want.");
         }        

      } catch(Exception e) {
         System.err.println("FileServer exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }
}
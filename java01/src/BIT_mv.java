import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BIT_mv {

  public static void main(String[] args) throws IOException {
    
    int b = 0;
    
    FileReader in = new FileReader("temp/" + args[0]);
    
    FileWriter out = new FileWriter("" + args[1] + args[0]);
    
    while ((b = in.read()) != -1){
    out.write(b);
    }
    
    in.close();
    out.close();
    
    File f = new File("temp/" + args[0]);
    
    f.delete();
    
  }

}

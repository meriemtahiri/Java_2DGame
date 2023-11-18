package object
import java.io.IOExeption;
import javax.imageio.ImageIO;
public class OBJ_key extends SuperObject{
    public OBJ_key(){
        name="key";
        try {
            image=imageIO.read(getClass().getResourceAsStream("/objects/key.png"))
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
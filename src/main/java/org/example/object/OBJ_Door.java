public class OBJ_Door extends SuperObject {
    public OBJ_Door(){
        name="Door";
        try {
            image=imageIO.read(getClass().getResourceAsStream("/objects/door.png"))
        }catch (IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}
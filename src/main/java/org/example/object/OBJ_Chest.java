public class OBJ_Chest extends SuperObject{
    public OBJ_Chest(){
        name="Chest";
        try {
            image=imageIO.read(getClass().getResourceAsStream("/objects/chest.png"))
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
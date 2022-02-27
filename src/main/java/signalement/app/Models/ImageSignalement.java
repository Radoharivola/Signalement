package signalement.app.Models;
import java.sql.Connection;

public class ImageSignalement  extends Fonctions{
    Integer id;
    Integer idSignalement;
    String imageSignalement;


    public ImageSignalement() {
    }

   
    public void set_Id(Object i){
        id=Integer.valueOf(String.valueOf(i));
    }

    public void set_IdSignalement(Object id){
        idSignalement=Integer.valueOf(String.valueOf(id));
    }

   
   public Integer get_IdSignalement(){
       return idSignalement;
   }

    public Integer get_Id(){
       return id;
   }

   public String get_ImageSignalement(){
       return imageSignalement;
   }

   public void set_ImageSignalement(Object im){
       imageSignalement=String.valueOf(im);
   }

   

}

package signalement.app.Models;
import java.sql.Connection;

public class Termine  extends Fonctions{
    Integer id;
    Integer idSignalement;

    public Termine() {
    }

   
    public void set_Id(Object i){
        id=Integer.valueOf(String.valueOf(i));
    }

    public void set_IdSignalement(Object id){
        idSignalement=Integer.valueOf(String.valueOf(id));
    }

    public Object[] getSignalement(){
        Signalement sign=new Signalement();
        Object[] signs=new Object[1];
        try{
            sign.set_Id(this.idSignalement);
            Log log=new Log();
            Connection con=log.getCon();
            signs=sign.pgFind(con);
         
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return signs;
   }

   public Integer get_IdSignalement(){
       return idSignalement;
   }

    public Integer get_Id(){
       return id;
   }

}

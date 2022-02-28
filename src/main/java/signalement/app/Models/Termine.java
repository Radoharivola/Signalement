package signalement.app.Models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.text.*;
import java.time.*;
import java.time.format.*;
public class Termine  extends Fonctions{
    Integer id;
    Integer idSignalement;
    Date dateTermine;
    Signalement sign;

    public Termine() {
    }

   
    public void set_Id(Object i){
        id=Integer.valueOf(String.valueOf(i));
    }

    public void set_IdSignalement(Object id){
        idSignalement=Integer.valueOf(String.valueOf(id));
        this.sign=(Signalement)this.getSignalement();
    }

    public Object getSignalement(){
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
        return signs[0];
   }

   public Integer get_IdSignalement(){
       return idSignalement;
   }

    public Integer get_Id(){
       return id;
   }

     public Date get_DateTermine() {
        return this.dateTermine;
    }

    public void set_DateTermine(String dateSignalement) throws ParseException {
        this.dateTermine = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(dateSignalement); ;
    }

    public void setDate() throws Exception{
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime dt=LocalDateTime.now();

        this.set_DateTermine(dtf.format(dt));
    }


    public Termine[] getTermineRegion(Connection con,String id){
        Termine[] retour=null;
        try{

            java.sql.Statement stmt = con.createStatement();

            
            ResultSet resTaille = stmt.executeQuery("select termine.* from termine,signalement where termine.idSignalement=signalement.id and signalement.idRegion="+id);


            int taille = 0;
            int indice = 0;
            while (resTaille.next()) {
                taille++;
            }

            retour=new Termine[taille];

            ResultSet res = stmt.executeQuery("select termine.* from termine,signalement where termine.idSignalement=signalement.id and signalement.idRegion="+id);
            int i=0;
            while (res.next()) {
               retour[i]=new Termine();
               retour[i].set_Id(res.getString(0));
               retour[i].set_IdSignalement(res.getString(1));
                retour[i].set_DateTermine(res.getString(2));

               i++;
            }

            // stmt.executeQuery("commit");
            stmt.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return retour;
    }
}

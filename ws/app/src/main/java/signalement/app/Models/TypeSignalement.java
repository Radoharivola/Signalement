package signalement.app.Models;
import java.sql.*;

public class TypeSignalement  extends Fonctions{
    Integer id;
    String nom;
    Integer etat;

    public TypeSignalement() {
        etat=1;
    }

    public String get_Nom() {
        return this.nom;
    }

    public void set_Nom(String nom) {
        this.nom = nom;
    }

    public void set_Id(Object id){
        this.id=Integer.valueOf(String.valueOf(id));

    }

    public Integer get_Id(){
        return id;
    }

    public void set_Etat(Object etat){
        this.etat=Integer.valueOf(String.valueOf(etat));

    }

    public Integer get_Etat(){
        return etat;
    }


      public void update(Connection con){
        try{

            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate("Update typeSignalement set nom='"+this.get_Nom()+"' where id='"+this.get_Id()+"'");
            stmt.executeUpdate("commit");
            stmt.close();
        }
        catch(Exception e ){
            e.printStackTrace();
        }
      }

      public void deleteData(Connection con){
        try{

            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate("Update typeSignalement set etat=0 where id='"+this.get_Id()+"'");
            stmt.executeUpdate("commit");
            stmt.close();
        }
        catch(Exception e ){
            e.printStackTrace();
        }
    }

}

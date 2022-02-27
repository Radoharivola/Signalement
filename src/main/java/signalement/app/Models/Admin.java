package signalement.app.Models;
import java.sql.*;
public class Admin extends Fonctions{
    Integer id;
    String nom;
    String email;
    String idRegion;
    String mdp;
    Integer etat=1;
    Object[] regions;
    public Integer get_Id(){
        return id;
    }
    public void set_Id(Object newValue){
        id=Integer.valueOf(String.valueOf(newValue));
    }

     public Integer get_Etat(){
        return etat;
    }
    public void set_Etat(Object newValue){
        etat=Integer.valueOf(String.valueOf(newValue));
    }

    public String get_IdRegion(){
        return idRegion;
    }
    public void set_IdRegion(String newValue){
        idRegion=newValue;
        regions=getRegion(newValue);
        if(regions.length==0){
            this.etat=0;
        }
    }

     public String get_Nom(){
        return nom;
    }
    public void set_Nom(String newValue){
        nom=newValue;
    }
   
    public String get_Email(){
        return email;
    }
    public void set_Email(String newValue){
        email=newValue;
    }

    public String get_Mdp(){
        return mdp;
    }
    public void set_Mdp(String newValue){
        mdp=newValue;
    }
    public Admin(){
        etat=1;
    }
    // public Admin(Integer id,String nom,String email,String idRegion,String mdp){
    //     this.id=id;
    //     this.nom=nom;
    //     this.idRegion=idRegion;
    //     this.email=email;
    //     this.mdp=mdp;
    // }

    public Object[] getRegion(String id){
        Object[] type = null;
        try {
            Region typ = new Region();
            typ.set_Id(id);
            Log log = new Log();
            Connection con = log.getCon();
            type = typ.pgFind(con);
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

     public void update(Connection con){
        try{

            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate("Update admin set nom='"+this.get_Nom()+"',email='"+this.get_Email()+"',mdp='"+this.get_Mdp()+"',idRegion='"+this.get_IdRegion()+"' where id='"+this.get_Id()+"'");
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
            stmt.executeUpdate("Update admin set etat=0 where id='"+this.get_Id()+"'");
            stmt.executeUpdate("commit");
            stmt.close();
        }
        catch(Exception e ){
            e.printStackTrace();
        }
    }
}


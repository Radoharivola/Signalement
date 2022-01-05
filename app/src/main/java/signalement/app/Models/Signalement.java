package signalement.app.Models;
import java.sql.Connection;
import java.util.*;
public class Signalement  extends Fonctions{
    Integer id;
    Integer idUser;
    Integer idType;
    Integer idRegion;
    Double longitude;
    Double latitude;
    String descriptionSignalement;
    Date datesignalement;
    Object[] images;
    Object[] type;
    Object[] region;
     

     public Signalement(){
         
     }

    //  public Signalement(Object id,Object idU,Object idT,Object img,Object longitude,Object latitude,Object descri){
    //      set_Id(id);
    //      set_IdUser(idU);
    //  }

    public Integer get_Id(){
        return this.id;
    }

  

    public Integer get_IdUser(){
        return this.idUser;
    }

    public Integer get_IdType(){
        return idType;
    }

    public Double get_Longitude(){
        return longitude;
    }

      public Double get_Latitude(){
        return latitude;
    }

    public Integer get_IdRegion(){
        return idRegion;
    }

    public String get_DescriptionSignalement(){
        return descriptionSignalement;
    }

    public Date get_DateSignalement(){
        return datesignalement;
    }

    public void set_Id(Object idS){
        id=Integer.valueOf(String.valueOf(idS));
        images=this.Images(idS);
    }

    public void set_IdRegion(Object idR){
        idRegion=Integer.valueOf(String.valueOf(idR));
        region=this.Region(idR);
    }

   
    public void set_IdType(Object idT){
        idType=Integer.valueOf(String.valueOf(idT));
        type=this.Type(idT);
    }
    public void set_IdUser(Object idU){
        idUser=Integer.valueOf(String.valueOf(idU));
    }
    public void set_Latitude(Object lat){
        latitude=Double.valueOf(String.valueOf(lat));
    }
    public void set_Longitude(Object lon){
        longitude=Double.valueOf(String.valueOf(lon));  
    }
  
    public void set_DescriptionSignalement(Object descri){
        descriptionSignalement=String.valueOf(descri);
    }

    public void set_DateSignalement(String newValue){
        this.datesignalement=new Date(newValue);
    }

    public Object[] Images(Object id){
        Object[] imgs=null;
        try{
            ImageSignalement img=new ImageSignalement();
            img.set_IdSignalement(id);
            Log log=new Log();
            Connection con=log.getCon();
            imgs=img.pgFind(con);
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return imgs;
    }


    public Object[] Type(Object id){
        Object[] type=null;
        try{
            TypeSignalement typ=new TypeSignalement();
            typ.set_Id(id);
            Log log=new Log();
            Connection con=log.getCon();
            type=typ.pgFind(con);
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return type;
    } 

    public Object[] Region(Object id){
        Object[] regs=null;
        try{
            Region region=new Region();
            region.set_Id(id);
            Log log=new Log();
            Connection con=log.getCon();
            regs=region.pgFind(con);
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return regs;
    } 

}

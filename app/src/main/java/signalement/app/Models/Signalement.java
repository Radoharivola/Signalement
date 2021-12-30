package signalement.app.Models;
public class Signalement  extends Fonctions{
    Integer id;
    Integer idUser;
    Integer idType;
    String imageSignalement;
    Double longitude;
    Double latitude;
    String descriptionSignalement;
     

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

    public String get_ImageSignalement(){
        return imageSignalement;
    }

    public String get_DescriptionSignalement(){
        return descriptionSignalement;
    }

    public void set_Id(Object idS){
        id=Integer.valueOf(String.valueOf(idS));
    }
    public void set_IdType(Object idT){
        idType=Integer.valueOf(String.valueOf(idT));
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
    public void set_ImageSignalement(Object img){
        imageSignalement=String.valueOf(img); 
    }
    public void set_DescriptionSignalement(Object descri){
        descriptionSignalement=String.valueOf(descri);
    }
}

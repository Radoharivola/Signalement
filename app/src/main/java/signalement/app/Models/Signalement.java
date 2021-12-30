package signalement.app.Models;
public class Signalement  extends Fonctions{
    int id;
    int idUser;
    int idType;
    String imageSignalement;
    double longitude;
    double latitude;
    String descriptionSignalement;
     

    public int get_Id(){
        return id;
    }

    public int get_IdUser(){
        return idUser;
    }

    public int get_IdType(){
        return idType;
    }

    public double get_Longitude(){
        return longitude;
    }

      public double get_Latitude(){
        return latitude;
    }

    public String get_ImageSignalement(){
        return imageSignalement;
    }

    public String get_DescriptionSignalement(){
        return descriptionSignalement;
    }

    public void set_Id(int idS){
        id=idS;
    }
    public void set_IdType(int idT){
        idType=idT;
    }
    public void set_IdUser(int idU){
        idUser=idU;
    }
    public void set_Latitude(double lat){
        latitude=lat;
    }
    public void set_Longitude(double lon){
        longitude=lon;
        
    }
    public void set_ImageSignalement(String img){
        imageSignalement=img; 
    }
    public void set_DescriptionSignalement(String descri){
        descriptionSignalement=descri;  
    }
}

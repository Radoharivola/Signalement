package signalement.app.Models;
public class Stat extends Fonctions{
    Integer idType;
    String nom;
    Double pourcentage;
    public Stat(){

    }
    public Stat(Integer idType,String nom,Double pourcentage){
        this.idType=idType;
        this.nom=nom;
        this.pourcentage=pourcentage;
    }
    public Integer getIdType(){
        return idType;
    }
    public String getNom(){
        return nom;
    }
    public Double getPourcentage(){
        return pourcentage;
    }

    public void setIdType(Object newValue){
        this.idType=Integer.valueOf(String.valueOf(newValue));
    }
    public void setPourcenntage(Object newValue){
        this.pourcentage=Double.valueOf(String.valueOf(newValue));
    }
    public void setNom(String newValue){
        this.nom=newValue;
    }
}
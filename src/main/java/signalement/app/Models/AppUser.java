package signalement.app.Models;
public class AppUser extends Fonctions{
    Integer id;
    String nom;
    String prenom;
    String email;
    String mdp;
    public Integer get_Id(){
        return id;
    }
    public void set_Id(Object newValue){
        id=Integer.valueOf(String.valueOf(newValue));
    }

    public String get_Nom(){
        return nom;
    }
    public void set_Nom(String newValue){
        nom=newValue;
    }

    public String get_Prenom(){
        return prenom;
    }
    public void set_Prenom(String newValue){
        prenom=newValue;
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
    public AppUser(){

    }
    public AppUser(Integer id,String nom,String prenom,String email,String mdp){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.mdp=mdp;
    }
}
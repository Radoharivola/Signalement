package signalement.app.Models;

public class TypeSignalement  extends Fonctions{
    Integer id;
    String nom;

    public TypeSignalement() {
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


}

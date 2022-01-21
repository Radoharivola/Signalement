package signalement.app.Models;

public class Test  extends Fonctions{
    String nom;

    public Test() {
    }

    public Test(String nom) {
        this.nom = nom;
    }

    public String get_Nom() {
        return this.nom;
    }

    public void set_Nom(String nom) {
        this.nom = nom;
    }

}

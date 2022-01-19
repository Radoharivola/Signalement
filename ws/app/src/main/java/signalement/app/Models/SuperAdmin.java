package signalement.app.Models;

public class SuperAdmin extends Fonctions{
    Integer id;
    String email;
    String mdp;

    public SuperAdmin() {
    }

    public SuperAdmin(Integer id, String email, String mdp) {
        this.id = id;
        this.email = email;
        this.mdp = mdp;
    }

    public Integer get_Id() {
        return this.id;
    }

    public void set_Id(Object id) {
        this.id = Integer.valueOf(String.valueOf(id));
    }

    public String get_Email() {
        return this.email;
    }

    public void set_Email(String email) {
        this.email = email;
    }

    public String get_Mdp() {
        return this.mdp;
    }

    public void set_Mdp(String mdp) {
        this.mdp = mdp;
    }
}

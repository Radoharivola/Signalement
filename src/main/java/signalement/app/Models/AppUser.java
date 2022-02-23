package signalement.app.Models;

import java.util.regex.Pattern;

public class AppUser extends Fonctions {
    Integer id;
    String email;
    String mdp;

    public Integer get_Id() {
        return id;
    }

    public void set_Id(Object newValue) {
        id = Integer.valueOf(String.valueOf(newValue));
    }

    public String get_Email() {
        return email;
    }

    public void set_Email(String newValue) {
        email = newValue;
    }

    public String get_Mdp() {
        return mdp;
    }

    public void set_Mdp(String newValue) {
        mdp = newValue;
    }

    public AppUser() {

    }

    public AppUser(Integer id, String email, String mdp) {
        this.id = id;
        this.email = email;
        this.mdp = mdp;
    }

    public void verifyMdp(String newValue) throws Exception {
        if (newValue.length() < 8) {
            throw new Exception("Le mot de passe doit au moins comporter huit caractères.");
        } else if (newValue.matches(".*[éèàâêûîôù].*")) {
            throw new Exception("Le mot de passe ne doit pas contenir d'accent.");
        }
    }

    public void verifyEmail(String emailAddress) throws Exception {
        
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches()) {
                    throw new Exception("Email invalide");
        }
    }
}
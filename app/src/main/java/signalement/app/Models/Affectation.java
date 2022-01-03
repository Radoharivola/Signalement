package signalement.app.Models;

import java.util.Date;

public class Affectation extends Fonctions{
    Integer id;
    Date dateAffectation;
    Integer idRegion;
    Integer idSignalement; 

    public Affectation() {

    }

    public Affectation(Integer id, Date dateAffectation, Integer idRegion, Integer idSignalement) {
        this.id = id;
        this.dateAffectation = dateAffectation;
        this.idRegion = idRegion;
        this.idSignalement = idSignalement;
    }

    public Integer get_Id() {
        return this.id;
    }

    public void set_Id(Object id) {
        this.id = Integer.valueOf(String.valueOf(id));
    }

    public Date get_DateAffectation() {
        return this.dateAffectation;
    }

    public void set_DateAffectation(Object dateAffectation) {
        this.dateAffectation = new Date(String.valueOf(dateAffectation));
    }

    public Integer get_IdRegion() {
        return this.idRegion;
    }

    public void set_IdRegion(Object idRegion) {
        this.idRegion = Integer.valueOf(String.valueOf(idRegion));
    }

    public Integer get_IdSignalement() {
        return this.idSignalement;
    }

    public void set_IdSignalement(Object idSignalement) {
        this.idSignalement = Integer.valueOf(String.valueOf(idSignalement));
    }

}

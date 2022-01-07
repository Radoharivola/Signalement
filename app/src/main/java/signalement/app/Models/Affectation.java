package signalement.app.Models;

public class Affectation {
    Integer idSignalement;
    Integer idRegion;

    public Affectation() {
    }

    public Affectation(Integer idSignalement, Integer idRegion) {
        this.idSignalement = idSignalement;
        this.idRegion = idRegion;
    }

    public Integer getIdSignalement() {
        return this.idSignalement;
    }

    public void setIdSignalement(Integer idSignalement) {
        this.idSignalement = idSignalement;
    }

    public Integer getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }
}

public class Affectation {
    Integer id;
    Date dateAffectation;
    Integer idRregion;
    Integer idSignalement; 

    public Affectation() {

    }

    public Affectation(Integer id, Date dateAffectation, Integer idRregion, Integer idSignalement) {
        this.id = id;
        this.dateAffectation = dateAffectation;
        this.idRregion = idRregion;
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
        this.dateAffectation = Date.valueOf(String.valueOf(dateAffectation));
    }

    public Integer get_IdRregion() {
        return this.idRregion;
    }

    public void set_IdRregion(Integer idRregion) {
        this.idRregion = idRregion;
    }

    public Integer get_IdSignalement() {
        return this.idSignalement;
    }

    public void set_IdSignalement(Integer idSignalement) {
        this.idSignalement = idSignalement;
    }

    public Affectation id(Integer id) {
        setId(id);
        return this;
    }

    public Affectation dateAffectation(Date dateAffectation) {
        setDateAffectation(dateAffectation);
        return this;
    }

    public Affectation idRregion(Integer idRregion) {
        setIdRregion(idRregion);
        return this;
    }

    public Affectation idSignalement(Integer idSignalement) {
        setIdSignalement(idSignalement);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Affectation)) {
            return false;
        }
        Affectation affectation = (Affectation) o;
        return Objects.equals(id, affectation.id) && Objects.equals(dateAffectation, affectation.dateAffectation) && Objects.equals(idRregion, affectation.idRregion) && Objects.equals(idSignalement, affectation.idSignalement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateAffectation, idRregion, idSignalement);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dateAffectation='" + getDateAffectation() + "'" +
            ", idRregion='" + getIdRregion() + "'" +
            ", idSignalement='" + getIdSignalement() + "'" +
            "}";
    }

}

package signalement.app.Models;

public class NewSignalement {
    Double longitude;
    Double latitude;
    String descriptionSignalement;
    Integer idType;
    String image;
    String token;


    public NewSignalement() {
    }

    public NewSignalement(Double longitude, Double latitude, String descriptionSignalement, Integer idType, String image, String token) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.descriptionSignalement = descriptionSignalement;
        this.idType = idType;
        this.image = image;
        this.token = token;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getDescriptionSignalement() {
        return this.descriptionSignalement;
    }

    public void setDescriptionSignalement(String descriptionSignalement) {
        this.descriptionSignalement = descriptionSignalement;
    }

    public Integer getIdType() {
        return this.idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public NewSignalement longitude(Double longitude) {
        setLongitude(longitude);
        return this;
    }

    public NewSignalement latitude(Double latitude) {
        setLatitude(latitude);
        return this;
    }

    public NewSignalement descriptionSignalement(String descriptionSignalement) {
        setDescriptionSignalement(descriptionSignalement);
        return this;
    }

    public NewSignalement idType(Integer idType) {
        setIdType(idType);
        return this;
    }

    public NewSignalement image(String image) {
        setImage(image);
        return this;
    }

    public NewSignalement token(String token) {
        setToken(token);
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " longitude='" + getLongitude() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", descriptionSignalement='" + getDescriptionSignalement() + "'" +
            ", idType='" + getIdType() + "'" +
            ", image='" + getImage() + "'" +
            ", token='" + getToken() + "'" +
            "}";
    }
    

}

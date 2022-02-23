package signalement.app.Models;

// import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="UserToken")
public class UserToken extends Fonctions{
    Integer idUser;
    String token;

    public UserToken() {
    }

    public UserToken(Integer idUser, String token) {
        this.idUser = idUser;
        this.token = token;
    }

    public Integer getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserToken idUser(Integer idUser) {
        setIdUser(idUser);
        return this;
    }

    public UserToken token(String token) {
        setToken(token);
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " idUser='" + getIdUser() + "'" +
            ", token='" + getToken() + "'" +
            "}";
    }
    
}
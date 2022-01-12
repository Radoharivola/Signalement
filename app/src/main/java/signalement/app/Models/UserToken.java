package signalement.app.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Document(collection ="UserToken")
public class UserToken extends Fonctions{
    @Id
    Integer idUser;
    String token;
    public Integer getIdUser(){
        return idUser;
    }
    public String getToken(){
        return token;
    }
    public void setIdUser(Object newValue){
        idUser=Integer.valueOf(String.valueOf(newValue));
    }
    public void setToken(String newValue){
        token=newValue;
    }
    public UserToken(){

    }
    public UserToken(Integer idUser,String token){
        this.idUser=idUser;
        this.token=token;
    }
}
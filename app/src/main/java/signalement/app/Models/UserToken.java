package signalement.app.Models;
public class UserToken extends Fonctions{
    Integer idUser;
    String token;
    public Integer get_IdUser(){
        return idUser;
    }
    public String get_Token(){
        return token;
    }
    public void set_IdUser(Object newValue){
        idUser=Integer.valueOf(String.valueOf(newValue));
    }
    public void set_Token(String newValue){
        token=newValue;
    }
    public UserToken(){

    }
    public UserToken(Integer idUser,String token){
        this.idUser=idUser;
        this.token=token;
    }
}
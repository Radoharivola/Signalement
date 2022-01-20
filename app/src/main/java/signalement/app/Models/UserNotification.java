package signalement.app.Models;
import java.util.*;
public class UserNotification extends Fonctions{
    Integer id;
    Date dateNotification;
    Integer idAppUser;
    Integer idSignalement;
    String notificationDetail;
    public UserNotification(){

    }
    public UserNotification(Integer id,Date dateNotification,Integer idAppUser,Integer idSignalement,String notificationDetail){
        this.id=id;
        this.dateNotification=dateNotification;
        this.idAppUser=idAppUser;
        this.idSignalement=idSignalement;
        this.notificationDetail=notificationDetail;
    }

    public Integer get_Id(){
        return this.id;
    }
    public Date get_DateNotification(){
        return this.dateNotification;
    }
    public Integer get_idAppUser(){
        return this.idAppUser;
    }
    public Integer get_IdSignalement(){
        return this.idSignalement;
    }
    public String get_NotificationDetail(){
        return this.notificationDetail;
    }

    public void set_Id(Object newValue){
        this.id=Integer.valueOf(String.valueOf(newValue));
    }
    public void set_DateNotification(String newValue){
        this.dateNotification=new Date(newValue);
    }
    public void set_IdAppUser(Object newValue){
        this.idAppUser=Integer.valueOf(String.valueOf(newValue));
    }
    public void set_IdSignalement(Object newValue){
        this.idSignalement=Integer.valueOf(String.valueOf(newValue));
    }
    public void set_notificationDetail(String newValue){
        this.notificationDetail=newValue;
    }
}
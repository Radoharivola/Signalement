package signalement.app.Models;
import java.text.SimpleDateFormat;
import java.util.*;
public class UserNotification extends Fonctions{
    Integer id;
    Date dateNotification;
    Integer idAppUser;
    Integer idSignalement;
    String notificationDetail;
    String notificationTitle;

    public UserNotification() {
    }

    public UserNotification(Integer id, Date dateNotification, Integer idAppUser, Integer idSignalement, String notificationDetail, String notificationTitle) {
        this.id = id;
        this.dateNotification = dateNotification;
        this.idAppUser = idAppUser;
        this.idSignalement = idSignalement;
        this.notificationDetail = notificationDetail;
        this.notificationTitle = notificationTitle;
    }

    public Integer get_Id() {
        return this.id;
    }

    public void set_Id(Object id) {
        this.id = Integer.valueOf(String.valueOf(id));
    }

    public Date get_DateNotification() {
        return this.dateNotification;
    }

    public void set_DateNotification(String dateNotification) throws Exception{
        this.dateNotification = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(dateNotification);
    }

    public Integer get_IdAppUser() {
        return this.idAppUser;
    }

    public void set_IdAppUser(Object idAppUser) {
        this.idAppUser = Integer.valueOf(String.valueOf(idAppUser));
    }

    public Integer get_IdSignalement() {
        return this.idSignalement;
    }

    public void set_IdSignalement(Object idSignalement) {
        this.idSignalement = Integer.valueOf(String.valueOf(idSignalement));
    }

    public String get_NotificationDetail() {
        return this.notificationDetail;
    }

    public void set_NotificationDetail(String notificationDetail) {
        this.notificationDetail = notificationDetail;
    }

    public String get_NotificationTitle() {
        return this.notificationTitle;
    }

    public void set_NotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }


}


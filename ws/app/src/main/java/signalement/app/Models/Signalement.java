package signalement.app.Models;

import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Signalement extends Fonctions {
    Integer id;
    Integer idUser;
    Integer idType;
    Integer idRegion;
    Double longitude;
    Double latitude;
    String descriptionSignalement;
    Date dateSignalement;
    Object[] images;
    Object[] type;
    Object[] region;
    Object[] user;
    public Signalement() {
    }

    public Signalement(Integer id, Integer idUser, Integer idType, Integer idRegion, Double longitude, Double latitude,
            String descriptionSignalement, Date datesignalement) {
        this.id = id;
        this.idUser = idUser;
        this.idType = idType;
        this.idRegion = idRegion;
        this.longitude = longitude;
        this.latitude = latitude;
        this.descriptionSignalement = descriptionSignalement;
        this.dateSignalement = datesignalement;
    }

    public Integer get_Id() {
        return this.id;
    }

    public void set_Id(Object id) throws Exception {
        this.id = Integer.valueOf(String.valueOf(id));
        images = this.getImages(id);
    }

    public Integer get_IdUser() {
        return this.idUser;
    }

    public void set_IdUser(Object idUser) {
        this.idUser = Integer.valueOf(String.valueOf(idUser));
        this.user=this.getUser(idUser);

    }

    public Integer get_IdType() {
        return this.idType;
    }

    public void set_IdType(Object idType) throws Exception {
        this.idType = Integer.valueOf(String.valueOf(idType));
        type=getType(idType);
    }

    public Integer get_IdRegion() {
        return this.idRegion;
    }

    public void set_IdRegion(Object idRegion) throws Exception {
        this.idRegion = Integer.valueOf(String.valueOf(idRegion));
        this.region=getRegion(idRegion);
    }

    public Double get_Longitude() {
        return this.longitude;
    }

    public void set_Longitude(Object longitude) {
        this.longitude = Double.valueOf(String.valueOf(longitude));
    }

    public Double get_Latitude() {
        return this.latitude;
    }

    public void set_Latitude(Object latitude) {
        this.latitude = Double.valueOf(String.valueOf(latitude));
    }

    public String get_DescriptionSignalement() {
        return this.descriptionSignalement;
    }

    public void set_DescriptionSignalement(String descriptionSignalement) {
        this.descriptionSignalement = descriptionSignalement;
    }

    
    public Object[] getImages(Object id) throws Exception {
        Object[] imgs = null;
        try {
            ImageSignalement img = new ImageSignalement();
            img.set_IdSignalement(id);
            Log log = new Log();
            Connection con = log.getCon();
            imgs = img.pgFind(con);
            con.close();
        } catch (Exception e) {
            throw e;
        }
        return imgs;
    }

    public Object[] getType(Object id) throws Exception{
        Object[] type = null;
        try {
            TypeSignalement typ = new TypeSignalement();
            typ.set_Id(id);
            Log log = new Log();
            Connection con = log.getCon();
            type = typ.pgFind(con);
            con.close();
        }
        catch (Exception e) {
            throw e;
        }
        return type;
    } 

    public Object[] getUser(Object id) throws Exception{
        Object[] type = null;
        try {
            AppUser typ = new AppUser();
            typ.set_Id(id);
            Log log = new Log();
            Connection con = log.getCon();
            type = typ.pgFind(con);
            con.close();
        }
        catch (Exception e) {
            throw e;
        }
        return type;
    } 

    public Object[] getRegion(Object id) throws Exception{
        Object[] type = null;
        try {
            Region typ = new Region();
            typ.set_Id(id);
            Log log = new Log();
            Connection con = log.getCon();
            type = typ.pgFind(con);
            con.close();
        }
        catch (Exception e) {
            throw e;
        }
        return type;
    } 


    public Date get_DateSignalement() {
        return this.dateSignalement;
    }

    public void set_DateSignalement(String dateSignalement) throws ParseException {
        this.dateSignalement = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(dateSignalement); ;
    }


}

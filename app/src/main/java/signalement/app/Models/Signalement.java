package signalement.app.Models;

import java.util.Date;

import com.google.gson.Gson;

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
    public Signalement() {
    }

    public Signalement(Integer id, Integer idUser, Integer idType, Integer idRegion, Double longitude, Double latitude,
            String descriptionSignalement, String date) throws Exception {
        this.id = id;
        this.idUser = idUser;
        this.idType = idType;
        this.idRegion = idRegion;
        this.longitude = longitude;
        this.latitude = latitude;
        this.descriptionSignalement = descriptionSignalement;
        set_DateSignalement(date);
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
        try{
            this.idRegion = Integer.valueOf(String.valueOf(idRegion));
            this.region=getRegion(idRegion);
        }catch(Exception e){
            
        }
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

    public void set_DateSignalement(String dateSignalement) throws Exception {
        this.dateSignalement = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(dateSignalement); ;
    }
    public String getNASignalement() throws Exception{
        Gson json = new Gson();
        Object[]result=null;
        String query="select*from signalement where idRegion is null";
        Log log=null;
        Signalement s=null;
        int indice=0;
        try {
            log=new Log();
            Connection con=log.getCon();
            java.sql.Statement stmt = con.createStatement();
            ResultSet resTaille = stmt.executeQuery(query);
            int taille = 0;
            while (resTaille.next()) {
                taille++;
            }
            result=new Object[taille];
            ResultSet res=stmt.executeQuery(query);

            while (res.next()) {
                Integer id=res.getInt("id");
                Integer idUser=res.getInt("idUser");
                Integer idType=res.getInt("idType");
                Integer idRegion=res.getInt("idRegion");
                Double longitude=res.getDouble("longitude");
                Double latitude=res.getDouble("latitude");
                String descriptionSignalement=res.getString("descriptionSignalement");
                String dateSignalement=res.getString("dateSignalement");
                s=new Signalement(id,idUser,idType,idRegion,longitude,latitude,descriptionSignalement,dateSignalement);
                result[indice]=s;
                indice++;
            }
        }catch (Exception e){
            throw e;
        }finally {
            log.close();
        }

        return json.toJson(result);

    }

}

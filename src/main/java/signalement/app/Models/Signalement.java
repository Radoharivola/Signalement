package signalement.app.Models;

import java.util.Base64;
import java.util.Date;

import com.google.gson.Gson;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.*;
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
        try {
            this.id = Integer.valueOf(String.valueOf(id));
            images = this.getImages(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("oops");
            throw e;
        }
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
        type = getType(idType);
    }

    public Integer get_IdRegion() {
        return this.idRegion;
    }

    public void set_IdRegion(Object idRegion) {
        try {
            this.idRegion = Integer.valueOf(String.valueOf(idRegion));
            this.region = getRegion(idRegion);
        } catch (Exception e) {
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

    public void setImages(Object[] imgs) {
        this.images = imgs;
    }

    public Object[] getImages(Object id) throws Exception {
        Object[] imgs = null;
        Object[] result = null;
        String UPLOAD_DIR = new FileSystemResource("/images").getFile().getAbsolutePath();
        // String UPLOAD_DIR="https://imagestorage.c1.biz/image/";
        try {
            // System.out.println(images);
            // if(this.images.length!= 0){
            // return this.images;
            // }
            ImageSignalement img = new ImageSignalement();
            img.set_IdSignalement(id);
            Log log = new Log();
            Connection con = log.getCon();
            imgs = img.find(con);
            result = new Object[imgs.length];
            for (int i = 0; i < imgs.length; i++) {
                ImageSignalement temp = (ImageSignalement) imgs[i];
                String imagePath =  temp.get_ImageSignalement();
                try {
                    byte[] data = Files.readAllBytes(Paths.get(imagePath));
                    result[i] = "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(data);
                } catch (NoSuchFileException e) {
                    result[i]="tsy ita ee";
                    // result[i]="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";
                }
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public Object[] getType(Object id) throws Exception {
        Object[] type = null;
        try {
            TypeSignalement typ = new TypeSignalement();
            typ.set_Id(id);
            Log log = new Log();
            Connection con = log.getCon();
            type = typ.pgFind(con);
            con.close();
        } catch (Exception e) {
            throw e;
        }
        return type;
    }

    public Object[] getRegion(Object id) throws Exception {
        Object[] type = null;
        try {
            Region typ = new Region();
            typ.set_Id(id);
            Log log = new Log();
            Connection con = log.getCon();
            type = typ.pgFind(con);
            con.close();
        } catch (Exception e) {
            throw e;
        }
        return type;
    }

    public Date get_DateSignalement() {
        return this.dateSignalement;
    }

    public void set_DateSignalement(String dateSignalement) throws Exception {
        this.dateSignalement = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(dateSignalement);
        
    }

    public String getNASignalement() throws Exception {
        Gson json = new Gson();
        Object[] result = null;
        String query = "select*from signalement where idRegion is null";
        Log log = null;
        Signalement s = null;
        int indice = 0;
        try {
            log = new Log();
            Connection con = log.getCon();
            java.sql.Statement stmt = con.createStatement();
            ResultSet resTaille = stmt.executeQuery(query);
            int taille = 0;
            while (resTaille.next()) {
                taille++;
            }
            result = new Object[taille];
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                Integer id = res.getInt("id");
                Integer idUser = res.getInt("idUser");
                Integer idType = res.getInt("idType");
                Integer idRegion = res.getInt("idRegion");
                Double longitude = res.getDouble("longitude");
                Double latitude = res.getDouble("latitude");
                String descriptionSignalement = res.getString("descriptionSignalement");
                String dateSignalement = res.getString("dateSignalement");
                s = new Signalement(id, idUser, idType, idRegion, longitude, latitude, descriptionSignalement,
                        dateSignalement);
                s.set_Id(id);
                result[indice] = s;
                indice++;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            log.close();
        }

        return json.toJson(result);

    }

}

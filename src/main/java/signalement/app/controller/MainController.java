package signalement.app.controller;

import java.sql.*;
import java.util.List;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import signalement.app.Models.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MainController {
    @Autowired
    private UserTokenRepository userTokenRepository;

    // @CrossOrigin(origins = "")
    @GetMapping("/userTokens")
    List<UserToken> userTokens() {
        return userTokenRepository.findAll();
    }

    // @CrossOrigin(origins = "")
    @RequestMapping("/EnCours")
    public String enCours(@RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {

            try {
                Log log = new Log();
                Connection con = log.getCon();
                EnCours eC = new EnCours();
                Object[] enCours = eC.find(con);
                Object[] signs = new Object[enCours.length];
                for (int i = 0; i < enCours.length; i++) {
                    EnCours temp = new EnCours();
                    temp = (EnCours) enCours[i];
                    System.out.println(temp.get_IdSignalement());
                    Object[] t = temp.getSignalement();
                    signs[i] = t[0];
                }
                retour = gson.toJson(signs);
                log.close();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, null));
        }
        return retour;

    }

    // @CrossOrigin(origins = "")
    @RequestMapping("/Termine")
    public String termine(@RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        Gson gson = new Gson();
        String retour = null;
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Termine eC = new Termine();
                Object[] termines = eC.find(con);
                Object[] signs = new Object[termines.length];
                for (int i = 0; i < termines.length; i++) {
                    Termine temp = new Termine();
                    temp = (Termine) termines[i];
                    System.out.println(temp.get_IdSignalement());
                    Object[] t = temp.getSignalement();
                    signs[i] = t[0];
                }
                retour = gson.toJson(signs);
                log.close();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;

    }

    // @CrossOrigin(origins = "")
    @GetMapping("/Signalements")
    public String signalements(@RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        Gson gson = new Gson();
        String retour = null;
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Signalement sign = new Signalement();
                Object[] signs = sign.find(con);
                retour = gson.toJson(signs);
                log.close();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;

    }

    // @CrossOrigin(origins = "")
    @GetMapping("/NASignalements")
    public String nASignalements(@RequestHeader("token") String token) throws Exception {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            Signalement sign = new Signalement();
            result = sign.getNASignalement();
        } else {
            result = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return result;
    }

    // @CrossOrigin(origins = "")
    @PostMapping("/EnCours")
    String newEC(@RequestBody EnCours EC, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                EC.insert(con);
                result = gson.toJson(new ReturnMessage(token, "success", true, true, true));
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return result;
    }

    // @CrossOrigin(origins = "")
    @PostMapping("/Termine")
    String newTerm(@RequestBody Termine term, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                term.insert(con);
                result = gson.toJson(new ReturnMessage(token, "success", true, true, true));
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return result;
    }

    // @CrossOrigin(origins = "")
    @PostMapping("/Regions")
    String newReg(@RequestBody Region regi, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                regi.insert(con);
                result = gson.toJson(new ReturnMessage(token, "success", true, true, true));
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return result;
    }

    // @CrossOrigin(origins = "")
    @RequestMapping("/Regions")
    public String getReg(@RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Region sign = new Region();
                // sign.set_Etat(1);
                Object[] signs = sign.find(con);
                retour = gson.toJson(signs);
                log.close();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;
    }

    // @CrossOrigin(origins = "")
    @GetMapping("/Regions/{id}")
    public String getSimpleReg(@PathVariable Long id, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Region sign = new Region();
                sign.set_Id(id);
                // sign.set_Etat(1);
                Object[] signs = sign.find(con);
                retour = gson.toJson(signs);
                log.close();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;
    }

    // @CrossOrigin(origins = "")
    @PutMapping("/Regions")
    String updateRegion(@RequestBody Region reg, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                reg.update(con);
                retour = gson.toJson("Success");
                // sign.delete(con);
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;
    }

    // @CrossOrigin(origins = "")
    @DeleteMapping("Regions/{id}")
    String del(@PathVariable Long id, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        ReturnMessage result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Region sign = new Region();
                sign.set_Id(id);
                sign.deleteData(con);
                result = new ReturnMessage(token, "success", true, true, true);
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = (new ReturnMessage(null, "invalid token", false, false, false));
        }
        return gson.toJson(result);
    }

    /// Admins

    // @CrossOrigin(origins = "")
    @PostMapping("/Admins")
    String newAdmin(@RequestBody Admin admin, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                admin.insert(con);
                result = gson.toJson(new ReturnMessage(token, "success", true, true, true));
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return result;
    }

    // @CrossOrigin(origins = "")
    @RequestMapping("/Admins")
    public String getAdmin(@RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Admin admin = new Admin();
                // admin.set_Etat(1);
                Object[] admins = admin.find(con);

                retour = gson.toJson(admins);
                log.close();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;
    }

    // @CrossOrigin(origins = "")
    @GetMapping("/Admins/{id}")
    public String getSingleAdmin(@PathVariable Long id, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Admin admin = new Admin();
                admin.set_Id(id);
                // admin.set_Etat(1);
                Object[] signs = admin.find(con);
                retour = gson.toJson(signs);
                log.close();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;
    }

    // @CrossOrigin(origins = "")
    @PutMapping("/Admins")
    String updateAdmin(@RequestBody Admin reg, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                reg.update(con);
                retour = gson.toJson(new ReturnMessage(token, "success", true, true, true));
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;
    }

    // @CrossOrigin(origins = "")
    @DeleteMapping("/Admins/{id}")
    String delAdmin(@PathVariable Long id, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        ReturnMessage result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Admin sign = new Admin();
                sign.set_Id(id);
                sign.deleteData(con);
                result = new ReturnMessage(token, "success", true, true, true);
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = (new ReturnMessage(null, "invalid token", false, false, false));
        }
        return gson.toJson(result);
    }

    /// End Admin

    // @CrossOrigin(origins = "")
    @PostMapping("/TypeSignalements")
    String newType(@RequestBody TypeSignalement regi, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                regi.insert(con);
                result = gson.toJson(new ReturnMessage(token, "success", true, true, true));
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return result;
    }

    // @CrossOrigin(origins = "")
    @RequestMapping("/TypeSignalements")
    public String getType(@RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                TypeSignalement sign = new TypeSignalement();
                Object[] signs = sign.find(con);
                result = gson.toJson(signs);
                log.close();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            result = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return result;
    }

    // @CrossOrigin(origins = "")
    @PutMapping("/TypeSignalements")
    String updateRegion(@RequestBody TypeSignalement reg, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                reg.update(con);
                retour = gson.toJson(new ReturnMessage(token, "success", true, true, true));
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;
    }

    // @CrossOrigin(origins = "")
    @DeleteMapping("/TypeSignalements/{id}")
    String delType(@PathVariable Long id, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        ReturnMessage result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                TypeSignalement sign = new TypeSignalement();
                sign.set_Id(id);
                sign.deleteData(con);
                result = new ReturnMessage(token, "success", true, true, true);
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = (new ReturnMessage(null, "invalid token", false, false, false));
        }
        return gson.toJson(result);
    }

    @PostMapping("/Signalements")
    String insertSignalement(@RequestBody NewSignalement newSignalement, @RequestHeader("token") String token)
            throws Exception {

        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        ReturnMessage retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Signalement sign = new Signalement();
                sign.set_DescriptionSignalement(newSignalement.getDescriptionSignalement());
                sign.set_DateSignalement(Fonctions.pgDateNow());
                System.out.println(newSignalement.getLongitude() + " erg " + newSignalement.getLatitude());
                sign.set_Longitude(newSignalement.getLongitude());
                sign.set_Latitude(newSignalement.getLatitude());
                sign.set_IdType(newSignalement.getIdType());
                sign.set_IdUser((Integer) letMeIn.getData());
                Log log = new Log();
                Connection con = log.getCon();
                sign.insert(con);
                // get id last sign
                // Object[]tests=new Signalement().find(con);
                sign = (Signalement) sign.findOne(con)[0];
                // System.out.println("last id: ");
                // fin
                // Object[] imgs = sign.getImages(con);
                String imageName = String.valueOf(sign.get_IdUser()) + Fonctions.dateNow() + ".jpg";
                System.out.println(imageName);
                // String UPLOAD_DIR = new ClassPathResource("/image").getFile().getAbsolutePath();
                String UPLOAD_DIR ="https://imagestorage.c1.biz/image" ;
                File myFile = new File(UPLOAD_DIR + "/" + imageName);
                byte[] imageByte = Base64.getDecoder().decode(newSignalement.getImage().split(",")[1]);
                FileOutputStream fos = new FileOutputStream(myFile);
                fos.write(imageByte);
                fos.close();
                ImageSignalement imageSignalement = new ImageSignalement();
                imageSignalement.set_IdSignalement(sign.get_Id());
                imageSignalement.set_ImageSignalement(imageName);
                imageSignalement.insert(con);
                // }
                // // file upload code...

                // retour = new ReturnMessage(token, "success", true, true, token);

                log.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            retour = (new ReturnMessage(null, "invalid token", false, false, false));
        }
        return gson.toJson(retour);
    }

    // @CrossOrigin(origins = "")
    @GetMapping("/Signalements/User")
    String signs(@RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Signalement sign = new Signalement();
                System.out.println(letMeIn.getData());
                sign.set_IdUser((Integer) letMeIn.getData());
                retour = gson.toJson(sign.pgFind(con));
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;
    }
    // @CrossOrigin(origins = "")

    @GetMapping("/Signalements/Regions/{id}")
    public String signalements(@PathVariable Long id) {
        String retour = null;
        try {
            Log log = new Log();
            Connection con = log.getCon();
            Gson gson = new Gson();
            Admin admin = new Admin();
            Signalement sign = new Signalement();
            sign.set_IdRegion(id);
            Object[] signs = sign.find(con);
            retour = gson.toJson(signs);
            log.close();

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return retour;

    }

    // @CrossOrigin(origins = "")
    @GetMapping("/Signalements/{id}")
    String fiche(@PathVariable Long id, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Signalement sign = new Signalement();
                sign.set_Id(id);
                retour = gson.toJson(sign.pgFind(con));
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;
    }
    // notifications debut

    // @CrossOrigin(origins = "")
    @GetMapping("/notifications")
    String getAllNotifications(@RequestHeader("token") String token) throws Exception {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        Log log = null;
        Gson json = new Gson();
        Object[] all = null;
        if (letMeIn.getConnectionStatus()) {
            try {
                log = new Log();
                UserNotification filter = new UserNotification();
                filter.set_IdAppUser((Integer) letMeIn.getData());
                all = filter.find(log.getCon());
            } catch (Exception e) {
                throw e;
            } finally {
                log.close();
            }
        } else {
            return json.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return json.toJson(all);
    }
    // notifications fin

    // stats debut
    // @CrossOrigin(origins = "")
    @GetMapping("/typeGlobalStat/{year}")
    String typeGlobalStat(@PathVariable Integer year) throws Exception {
        Log log = null;
        Gson json = new Gson();
        Stat[] stat = null;
        try {
            log = new Log();
            Connection con = log.getCon();
            Signalement sign = new Signalement();
            Object[] all = sign.find(con);
            String query = "select typesignalement.id,typeSignalement.nom,(count(idType)*100/" + all.length
                    + ") as pourcentage from typesignalement join signalement on signalement.idtype=typesignalement.id where extract(year from datesignalement)="
                    + String.valueOf(year) + " group by typesignalement.id;";
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            TypeSignalement allTypeFilter = new TypeSignalement();
            Object[] allType = allTypeFilter.find(con);
            TypeSignalement t = null;
            stat = new Stat[allType.length];
            for (int i = 0; i < allType.length; i++) {
                t = (TypeSignalement) allType[i];
                stat[i] = new Stat(t.get_Id(), t.get_Nom(), 0.0);
            }
            while (res.next()) {
                Integer id = Integer.valueOf(String.valueOf(res.getString("id")));
                Double pourcentage = Double.valueOf(String.valueOf(res.getString("pourcentage")));
                for (int i = 0; i < stat.length; i++) {
                    if (stat[i].getIdType() == id) {
                        stat[i].setPourcenntage(pourcentage);
                        break;
                    }
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            log.close();
        }
        return json.toJson(stat);
    }

    // @CrossOrigin(origins = "")
    @GetMapping("/typeGlobalStat/{month}/{year}")
    String typeGlobalStat(@PathVariable Integer month, @PathVariable Integer year) throws Exception {
        Log log = null;
        Gson json = new Gson();
        Stat[] stat = null;
        try {
            log = new Log();
            Connection con = log.getCon();
            Signalement sign = new Signalement();
            Object[] all = sign.find(con);
            String query = "select typesignalement.id,typeSignalement.nom,(count(idType)*100/" + all.length
                    + ") as pourcentage from typesignalement join signalement on signalement.idtype=typesignalement.id where extract(year from datesignalement)="
                    + String.valueOf(year) + " and extract(month from datesignalement)=" + String.valueOf(month)
                    + " group by typesignalement.id;";
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            TypeSignalement allTypeFilter = new TypeSignalement();
            Object[] allType = allTypeFilter.find(con);
            TypeSignalement t = null;
            stat = new Stat[allType.length];
            for (int i = 0; i < allType.length; i++) {
                t = (TypeSignalement) allType[i];
                stat[i] = new Stat(t.get_Id(), t.get_Nom(), 0.0);
            }
            while (res.next()) {
                Integer id = Integer.valueOf(String.valueOf(res.getString("id")));
                Double pourcentage = Double.valueOf(String.valueOf(res.getString("pourcentage")));
                for (int i = 0; i < stat.length; i++) {
                    if (stat[i].getIdType() == id) {
                        stat[i].setPourcenntage(pourcentage);
                        break;
                    }
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            log.close();
        }
        return json.toJson(stat);
    }

    // @CrossOrigin(origins = "")
    @GetMapping("/signalementRegionStat/{year}")
    String signalementRegionStat(@PathVariable Integer year) throws Exception {
        Log log = null;
        Gson json = new Gson();
        Stat[] stat = null;
        try {
            log = new Log();
            Connection con = log.getCon();
            String query = "select region.id,region.nom,(count(signalement.idRegion)*100/count(signalement.id)) as pourcentage from signalement join region on region.id=signalement.idRegion where extract(year from datesignalement)="
                    + String.valueOf(year) + " group by region.id;";
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            Region allRegionFilter = new Region();
            Object[] allType = allRegionFilter.find(con);
            Region t = null;
            stat = new Stat[allType.length];
            for (int i = 0; i < allType.length; i++) {
                t = (Region) allType[i];
                stat[i] = new Stat(t.get_Id(), t.get_Nom(), 0.0);
            }
            while (res.next()) {
                Integer id = Integer.valueOf(String.valueOf(res.getString("id")));
                Double pourcentage = Double.valueOf(String.valueOf(res.getString("pourcentage")));
                for (int i = 0; i < stat.length; i++) {
                    if (stat[i].getIdType() == id) {
                        stat[i].setPourcenntage(pourcentage);
                        break;
                    }
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            log.close();
        }
        return json.toJson(stat);
    }

    // @CrossOrigin(origins = "")
    @GetMapping("/signalementRegionStat/{year}/{month}")
    String signalementRegionStatym(@PathVariable Integer year, @PathVariable Integer month) throws Exception {
        Log log = null;
        Gson json = new Gson();
        Stat[] stat = null;
        try {
            log = new Log();
            Connection con = log.getCon();
            String query = "select region.id,region.nom,(count(signalement.idRegion)*100/count(signalement.id)) as pourcentage from signalement join region on region.id=signalement.idRegion where extract(year from datesignalement)="
                    + String.valueOf(year) + " and extract(month from datesignalement)=" + String.valueOf(month)
                    + " group by region.id;";
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            Region allRegionFilter = new Region();
            Object[] allType = allRegionFilter.find(con);
            Region t = null;
            stat = new Stat[allType.length];
            for (int i = 0; i < allType.length; i++) {
                t = (Region) allType[i];
                stat[i] = new Stat(t.get_Id(), t.get_Nom(), 0.0);
            }
            while (res.next()) {
                Integer id = Integer.valueOf(String.valueOf(res.getString("id")));
                Double pourcentage = Double.valueOf(String.valueOf(res.getString("pourcentage")));
                for (int i = 0; i < stat.length; i++) {
                    if (stat[i].getIdType() == id) {
                        stat[i].setPourcenntage(pourcentage);
                        break;
                    }
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            log.close();
        }
        return json.toJson(stat);
    }

    // stats fin

    // affectation début
    // @CrossOrigin(origins = "")
    @PostMapping("/affectations")
    public String affect(@RequestBody Affectation aff, @RequestHeader("token") String token) throws Exception {
        // Token verification
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        ReturnMessage result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            Log log = null;
            try {
                log = new Log();
                Connection con = log.getCon();
                String query = "update signalement set idRegion=" + String.valueOf(aff.getIdRegion()) + " where id="
                        + String.valueOf(aff.getIdSignalement());
                java.sql.Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
                System.out.println(query);
                stmt.executeUpdate("commit");
                stmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.getMessage();
            } finally {
                log.close();
            }
            result = new ReturnMessage(token, "success", true, true, null);
        } else {
            result = new ReturnMessage(null, "invalid token", false, false, false);
        }
        return gson.toJson(result);
    }
    // affectation fin

    // @CrossOrigin(origins = "")
    @PostMapping("/Login")
    String login(@RequestBody AppUser user) {
        Gson json = new Gson();
        ReturnMessage result = null;
        try {
            Log log = new Log();
            Connection con = log.getCon();
            String mdp = Fonctions.encrypt(user.get_Mdp());
            String email = user.get_Email();
            AppUser eUser = new AppUser(null, email, null);
            AppUser emUser = new AppUser(null, email, mdp);
            Object[] eUserResult = eUser.find(con);
            if (eUserResult.length != 0) {
                Object[] emUserResult = emUser.find(con);
                if (emUserResult.length != 0) {
                    Integer idUser = ((AppUser) emUserResult[0]).get_Id();
                    UserToken token = new UserToken(idUser, Fonctions.createToken(email));
                    // token.insert(con);
                    userTokenRepository.save(token);
                    result = new ReturnMessage(token.getToken(), "success", true, true, null);
                    // jereo fonction mamorona token anaty pc an Kenny....ana mo zany e
                } else {
                    result = new ReturnMessage(null, "Invalid password!", false, false, null);
                }

            } else {
                result = new ReturnMessage(null, "This user does not exist!", false, false, null);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toJson(result);
    }

    // @CrossOrigin(origins = "")
    @PostMapping("/SULogin")
    String suLogin(@RequestBody SuperAdmin user) throws Exception {
        Gson json = new Gson();
        ReturnMessage result = null;
        if (user.get_Email() != null && user.get_Email() != null) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                String mdp = user.get_Mdp();
                String email = user.get_Email();
                SuperAdmin eUser = new SuperAdmin(null, email, null);
                SuperAdmin emUser = new SuperAdmin(null, email, mdp);
                Object[] eUserResult = eUser.find(con);
                if (eUserResult.length != 0) {
                    Object[] emUserResult = emUser.find(con);
                    if (emUserResult.length != 0) {
                        Integer idUser = ((SuperAdmin) emUserResult[0]).get_Id();
                        UserToken token = new UserToken(idUser, Fonctions.createToken(email));
                        userTokenRepository.insert(token);
                        // token.insert(con);
                        result = new ReturnMessage(token.getToken(), "success", true, true, null);
                        // jereo fonction mamorona token anaty pc an Kenny....ana mo zany e
                    } else {
                        result = new ReturnMessage(null, "Invalid password!", false, false, null);
                    }

                } else {
                    result = new ReturnMessage(null, "This user does not exist!", false, false, null);
                }
                con.close();
            } catch (Exception e) {
                throw e;
            }
        } else {
            result = new ReturnMessage(null, "This user does not exist!", false, false, null);
        }
        return json.toJson(result);
    }

    // @CrossOrigin(origins = "")
    @PostMapping("/ALogin")
    String aLogin(@RequestBody Admin user) throws Exception {
        Gson json = new Gson();
        ReturnMessage result = null;
        if (user.get_Email() != null && user.get_Email() != null) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                String mdp = user.get_Mdp();
                String email = user.get_Email();
                Admin eUser = new Admin();
                eUser.set_Email(email);
                Admin emUser = new Admin();
                emUser.set_Email(email);
                emUser.set_Mdp(mdp);
                Object[] eUserResult = eUser.find(con);
                if (eUserResult.length != 0) {
                    Object[] emUserResult = emUser.find(con);
                    if (emUserResult.length != 0) {
                        Integer idUser = ((Admin) emUserResult[0]).get_Id();
                        UserToken token = new UserToken(idUser, Fonctions.createToken(email));
                        userTokenRepository.insert(token);
                        // token.insert(con);
                        result = new ReturnMessage(token.getToken(), "success", true, true, null);
                        // jereo fonction mamorona token anaty pc an Kenny....ana mo zany e
                    } else {
                        result = new ReturnMessage(null, "Invalid password!", false, false, null);
                    }

                } else {
                    result = new ReturnMessage(null, "This user does not exist!", false, false, null);
                }
                con.close();
            } catch (Exception e) {
                throw e;
            }
        } else {
            result = new ReturnMessage(null, "This user does not exist!", false, false, null);
        }
        return json.toJson(result);
    }

    @PostMapping("/inscription")
    public String inscription(@RequestBody AppUser user) {
        String message = null;
        Gson gson = new Gson();
        Log log = null;
        String token = null;
        boolean conStatus = false;
        boolean tokenStatus = false;
        try {

            user.verifyMdp(user.get_Mdp());
            user.verifyEmail(user.get_Email());
            log = new Log();
            Connection con = log.getCon();
            user.set_Mdp(Fonctions.encrypt(user.get_Mdp()));
            user.insert(con);
            conStatus = true;
            tokenStatus = true;
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        return gson.toJson(new ReturnMessage(token, message, conStatus, tokenStatus, null));
    }

    // @CrossOrigin(origins = "")
    @GetMapping("/testToken")
    String testToken() {
        Gson gson = new Gson();
        return gson.toJson(Fonctions.verifyToken("4b1b120e05e68f6820a1c973842f6436a44a4fc5", userTokenRepository));
    }
    // @RequestMapping("/Signalements/idSignalement")
    // public String signalements(){
    // String retour=null;
    // try{
    // Log log=new Log();
    // Connection con=log.getCon();
    // Gson gson=new Gson();
    // Signalement sign=new Signalement();
    // sign.set_Id(idSignalement);

    // Object[]signs=sign.pgFindLike(con);
    // retour=gson.toJson(signs);
    // log.close();
    // }catch(Exception e){
    // e.printStackTrace();
    // return e.getMessage();
    // }
    // return retour;

    // }

    // @PostMapping("/upload-file")
    // public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile
    // file) {

    // try {
    // // validation
    // if (file.isEmpty()) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request
    // must contain file");
    // }
    // // file upload code...
    // boolean f = fileUploadHelper.uploadFile(file);

    // if (f) {
    // return ResponseEntity.ok("File is successfully uploaded");
    // }

    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // return
    // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went
    // wrong! Try again");
    // }

}

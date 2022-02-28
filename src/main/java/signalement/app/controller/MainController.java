package signalement.app.controller;

import java.sql.*;
import java.util.List;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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

    @GetMapping("/userTokens")
    List<UserToken> userTokens() {
        return userTokenRepository.findAll();
    }

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
                // ec.set_IdRegion((Integer)letMeIn.getData());

                Object[] enCours = eC.getEncoursRegion(con,(String)letMeIn.getData());
                retour = gson.toJson(enCours);
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
                // ec.set_IdRegion((Integer)letMeIn.getData());
                Object[] termines = eC.getTermineRegion(con,(String)letMeIn.getData());

                retour = gson.toJson(termines);
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

    @PostMapping("/EnCours")
    String newEC(@RequestBody EnCours EC, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Termine term = new Termine();

                term.set_IdSignalement(EC.get_IdSignalement());
                Signalement sign = (Signalement) term.getSignalement();

                UserNotification notif = new UserNotification();
                notif.set_DateNotification(Fonctions.pgDateNow());
                notif.set_IdAppUser(sign.get_IdUser());
                notif.set_IdSignalement(sign.get_Id());
                notif.set_NotificationDetail("Votre signalement concernant : " + sign.getTy().get_Nom() + " le "
                        + sign.get_DateSignalement() + " est en cours de traitement.");
                notif.set_NotificationTitle("Signalement en Cours ");
                notif.set_Lue(0);

                if (EC.find(con).length != 0 || term.find(con).length != 0) {
                    return gson.toJson("Error");
                } else {
                    EC.setDate();
                    EC.insert(con);
                    notif.insert(con);
                }

                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return result;
    }

    @PostMapping("/Termine")
    String newTerm(@RequestBody Termine term, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                term.setDate();
                term.insert(con);
                EnCours eC = new EnCours();
                eC.set_IdSignalement(term.get_IdSignalement());
                eC.delete(con);
                Signalement sign = (Signalement) term.getSignalement();
                UserNotification notif = new UserNotification();
                notif.set_DateNotification(Fonctions.pgDateNow());
                notif.set_IdAppUser(sign.get_IdUser());
                notif.set_IdSignalement(sign.get_Id());
                notif.set_NotificationDetail("Votre signalement concernant : " + sign.getTy().get_Nom() + " le "
                        + sign.get_DateSignalement() + " a ete traite avec succes! Merci pour votre cooperation.");
                notif.set_NotificationTitle("Signalement Termine ");
                notif.set_Lue(0);

                notif.insert(con);
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return "success";
    }

    @PostMapping("/Regions")
    String newReg(@RequestBody Region regi, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                if (regi.get_Nom().compareTo("") == 0) {
                    return gson.toJson("Error : vide");
                }
                // regi.set_Etat(1);
                regi.insert(con);
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

    @PutMapping("/Regions")
    String updateRegion(@RequestBody Region reg, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                if (reg.get_Nom().compareTo("") == 0) {
                    return gson.toJson("Erreur: vide");
                }

                reg.update(con);

                // temp.set_Id(reg.get_Id());
                // Object[] temps=temp.find(con);
                // temp=(Region)temps[0];
                // System.out.println(temp.get_Nom());

                // System.out.println(reg.get_Nom());

                // System.out.print(reg.get_Id());

                // temp.set(String.valueOf(reg.get_Id()),temp,reg,con);

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

    @PostMapping("/Admins")
    String newAdmin(@RequestBody Admin admin, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();

                if (admin.get_Nom().compareTo("") == 0 && admin.get_Email().compareTo("") == 0
                        && admin.get_IdRegion() != null && admin.get_Mdp() != null) {
                    admin.insert(con);
                    result = gson.toJson(new ReturnMessage(token, "success", true, true, true));

                } else {
                    return gson.toJson("Error: vide");
                }
                // admin.set_Etat(1);

                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return result;
    }

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

    @PutMapping("/Admins")
    String updateAdmin(@RequestBody Admin admin, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();

                if (admin.get_Nom().compareTo("") != 0 && admin.get_Email().compareTo("") != 0
                        && admin.get_IdRegion() != null && admin.get_Mdp().compareTo("") != 0) {
                    admin.update(con);
                    retour = gson.toJson(new ReturnMessage(token, "success", true, true, true));
                } else {
                    return gson.toJson("Error: vide");
                }

                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            retour = gson.toJson(new ReturnMessage(null, "invalid token", false, false, false));
        }
        return retour;
    }

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

    @PostMapping("/TypeSignalements")
    String newType(@RequestBody TypeSignalement regi, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String result = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                if (regi.get_Nom().compareTo("") == 0) {
                    return gson.toJson("Error: vide");

                }
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

    @PutMapping("/TypeSignalements")
    String updateRegion(@RequestBody TypeSignalement reg, @RequestHeader("token") String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                if (reg.get_Nom().compareTo("") == 0) {
                    return gson.toJson("Error: vide");
                }
                reg.update(con);
                // sign.delete(con);
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
                String imageName = String.valueOf(sign.get_IdUser()) + Fonctions.imagePathDateNow() + ".jpg";
                System.out.println(imageName);
                String UPLOAD_DIR = new FileSystemResource("/images").getFile().getAbsolutePath();
                System.out.println(UPLOAD_DIR);
                // String UPLOAD_DIR ="https://imagestorage.c1.biz/image" ;
                File myFile = new File(imageName);
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

    @GetMapping("/Signalements/Regions")
    public String signalements(@RequestHeader('token') String token) {
        ReturnMessage letMeIn = Fonctions.verifyToken(token, userTokenRepository);
        String retour = null;
        
        Gson gson = new Gson();
        if (letMeIn.getConnectionStatus()) {
            try {
                Log log = new Log();
                Connection con = log.getCon();
                Admin admin = new Admin();
                Signalement sign = new Signalement();
                sign.set_IdRegion((Integer)letMeIn.getData());
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
                        Integer idUser = Integer.valueOf(((Admin) emUserResult[0]).get_IdRegion());
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

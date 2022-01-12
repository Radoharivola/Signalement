package signalement.app.controller;

import java.sql.*;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.*;

import signalement.app.Models.*;

@RestController
public class MainController {

    @RequestMapping("/EnCours")
    public String enCours() {
        String retour = null;
        try {
            Log log = new Log();
            Connection con = log.getCon();
            Gson gson = new Gson();
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
        return retour;

    }

    @RequestMapping("/Termine")
    public String termine() {
        String retour = null;
        try {
            Log log = new Log();
            Connection con = log.getCon();
            Gson gson = new Gson();
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
        return retour;

    }

    @RequestMapping("/Signalements")
    public String signalements() {
        String retour = null;
        try {
            Log log = new Log();
            Connection con = log.getCon();
            Gson gson = new Gson();
            Signalement sign = new Signalement();
            Object[] signs = sign.find(con);
            retour = gson.toJson(signs);
            log.close();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return retour;

    }

    @PostMapping("/EnCours")
    EnCours newEC(@RequestBody EnCours EC) {
        try {
            Log log = new Log();
            Connection con = log.getCon();
            EC.insert(con);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EC;
    }

    @PostMapping("/Termine")
    Termine newTerm(@RequestBody Termine term) {
        try {
            Log log = new Log();
            Connection con = log.getCon();
            term.insert(con);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return term;
    }

    @PostMapping("/Region")
    Region newReg(@RequestBody Region regi) {
        try {
            Log log = new Log();
            Connection con = log.getCon();
            regi.insert(con);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regi;
    }

    @RequestMapping("/Region")
    public String getReg() {
        String retour = null;
        try {
            Log log = new Log();
            Connection con = log.getCon();
            Gson gson = new Gson();
            Region sign = new Region();
            Object[] signs = sign.find(con);
            retour = gson.toJson(signs);
            log.close();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return retour;
    }

    @DeleteMapping("Region/{id}")
    void del(@PathVariable Long id) {
        try {
            Log log = new Log();
            Connection con = log.getCon();
            Region sign = new Region();
            sign.set_Id(id);
            sign.delete(con);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/TypeSignalement")
    TypeSignalement newType(@RequestBody TypeSignalement regi) {
        try {
            Log log = new Log();
            Connection con = log.getCon();
            regi.insert(con);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regi;
    }

    @RequestMapping("/TypeSignalement")
    public String getType() {
        String retour = null;
        try {
            Log log = new Log();
            Connection con = log.getCon();
            Gson gson = new Gson();
            TypeSignalement sign = new TypeSignalement();
            Object[] signs = sign.find(con);
            retour = gson.toJson(signs);
            log.close();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return retour;
    }

    @DeleteMapping("TypeSignalement/{id}")
    void delType(@PathVariable Long id) {
        try {
            Log log = new Log();
            Connection con = log.getCon();
            TypeSignalement sign = new TypeSignalement();
            sign.set_Id(id);
            sign.delete(con);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/Signalements")
    Signalement newSign(@RequestBody Signalement sign) {
        try {
            Log log = new Log();
            Connection con = log.getCon();
            sign.insert(con);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    @GetMapping("/Signalements/Users/{id}")
    String signs(@PathVariable Long id) {
        String retour = null;
        try {
            Log log = new Log();
            Connection con = log.getCon();
            Signalement sign = new Signalement();
            Gson gson = new Gson();
            sign.set_IdUser(id);
            retour = gson.toJson(sign.pgFind(con));
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retour;
    }

    @GetMapping("/Signalements/{id}")
    String fiche(@PathVariable Long id) {
        String retour = null;
        try {
            Log log = new Log();
            Connection con = log.getCon();
            Signalement sign = new Signalement();
            Gson gson = new Gson();
            sign.set_Id(id);
            retour = gson.toJson(sign.pgFind(con));
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retour;
    }
    // notifications debut

    @GetMapping("/notifications/{id}")
    String getAllNotifications(@PathVariable Integer id) throws Exception {
        Log log = null;
        Gson json = new Gson();
        Object[] all = null;
        try {
            log = new Log();
            UserNotification filter = new UserNotification(null, null, id, null, null);
            all = filter.find(log.getCon());
        } catch (Exception e) {
            throw e;
        } finally {
            log.close();
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
            String query = "select typesignalement.id,typeSignalement.nom,(count(idType)*100/count(signalement.id)) as pourcentage from typesignalement join signalement on signalement.idtype=typesignalement.id where extract(year from datesignalement)="
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
            String query = "select typesignalement.id,typeSignalement.nom,(count(idType)*100/count(signalement.id)) as pourcentage from typesignalement join signalement on signalement.idtype=typesignalement.id where extract(year from datesignalement)="
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
    String signalementRegionStatym(@PathVariable Integer year,@PathVariable Integer month) throws Exception{
        Log log=null;
        Gson json=new Gson();
        Stat[]stat=null;
        try{
        log=new Log();
        Connection con=log.getCon();
        String query="select region.id,region.nom,(count(signalement.idRegion)*100/count(signalement.id)) as pourcentage from signalement join region on region.id=signalement.idRegion where extract(year from datesignalement)="+String.valueOf(year)+" and extract(month from datesignalement)="+String.valueOf(month)+" group by region.id;";
        java.sql.Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);
        Region allRegionFilter=new Region();
        Object[]allType=allRegionFilter.find(con);
        Region t=null;
        stat=new Stat[allType.length];
        for(int i=0;i<allType.length;i++){
            t=(Region)allType[i];
            stat[i]=new Stat(t.get_Id(),t.get_Nom(),0.0);
        }
        while(res.next()){
            Integer id=Integer.valueOf(String.valueOf(res.getString("id")));
            Double pourcentage=Double.valueOf(String.valueOf(res.getString("pourcentage")));
            for(int i=0;i<stat.length;i++){
                if(stat[i].getIdType()==id){
                    stat[i].setPourcenntage(pourcentage);
                    break;
                }
            }
        }

        }catch(Exception e){
            throw e;
        }finally{
            log.close();
        }
        return json.toJson(stat);
    }

    // stats fin

    // affectation début
    @PostMapping("/affectations")
    public String affect(@RequestBody Affectation aff) throws Exception {
        Gson gson = new Gson();
        Log log = null;
        Object[] all = null;
        try {
            log = new Log();
            Connection con = log.getCon();
            String query = "update signalement set idRegion=" + String.valueOf(aff.getIdRegion()) + " where id="
                    + String.valueOf(aff.getIdSignalement());
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            stmt.executeUpdate("commit");
            stmt.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        } finally {
            log.close();
        }
        return gson.toJson(new ReturnMessage("token", "ehehe", true, true, all));
    }
    // affectation fin

    @PostMapping("/Login")
    String login(@RequestBody AppUser user) {
        Gson json = new Gson();
        ReturnMessage result = null;
        try {
            Log log = new Log();
            Connection con = log.getCon();
            String mdp = user.get_Mdp();
            String email = user.get_Email();
            AppUser eUser = new AppUser(null, null, null, email, null);
            AppUser emUser = new AppUser(null, null, null, email, mdp);
            Object[] eUserResult = eUser.find(con);
            if (eUserResult.length != 0) {
                Object[] emUserResult = emUser.find(con);
                if (emUserResult.length != 0) {
                    Integer idUser = ((AppUser) emUserResult[0]).get_Id();
                    UserToken token = new UserToken(idUser, Fonctions.createToken(email));
                    token.insert(con);
                    result = new ReturnMessage(token.get_Token(), "success", true, true, null);
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
                    token.insert(con);
                    result = new ReturnMessage(token.get_Token(), "success", true, true, null);
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
        return json.toJson(result);
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

}

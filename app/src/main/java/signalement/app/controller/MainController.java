package signalement.app.controller;

import java.sql.Connection;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.*;

import signalement.app.Models.*;
@RestController
public class MainController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    // @RequestMapping("/vody")
    // public String vody(){
    //     return "vody";
    // }
    // @RequestMapping("/login")
    // public String login(){
    //     String result=null;
    //     try {
    //         result =UserLogin.verifyLogin("jojo","test");
    //     } catch (Exception e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //         return e.getMessage();
    //     }
    //     return result;
    // }
    @RequestMapping("/EnCours")
    public String enCours(){
        String retour=null;
        try{
            Log log=new Log();
            Connection con=log.getCon();
            Gson gson=new Gson();
            EnCours eC=new EnCours();
            Object[] enCours=eC.find(con);
            Object[] signs=new Object[enCours.length];
            for(int i=0;i<enCours.length;i++){
                EnCours temp=new EnCours();
                temp=(EnCours)enCours[i];
                System.out.println(temp.get_IdSignalement());
                Object[] t=temp.getSignalement();
                signs[i]=t[0];
            }
            retour=gson.toJson(signs); 
            log.close();
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return retour;

    }   

    @RequestMapping("/Termine")
    public String termine(){
        String retour=null;
        try{
            Log log=new Log();
            Connection con=log.getCon();
            Gson gson=new Gson();
            Termine eC=new Termine();
            Object[] termines=eC.find(con);
            Object[] signs=new Object[termines.length];
            for(int i=0;i<termines.length;i++){
                Termine temp=new Termine();
                temp=(Termine)termines[i];
                System.out.println(temp.get_IdSignalement());
                Object[] t=temp.getSignalement();
                signs[i]=t[0];
            }
            retour=gson.toJson(signs); 
            log.close();
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return retour;

    }   



    @RequestMapping("/Signalements")
    public String signalements(){
        String retour=null;
        try{
            Log log=new Log();
            Connection con=log.getCon();
            Gson gson=new Gson();
            Signalement sign=new Signalement();
            Object[]signs=sign.find(con);
            retour=gson.toJson(signs); 
            log.close();
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return retour;

    }   

    @PostMapping("/EnCours")
    EnCours newEC(@RequestBody EnCours EC){
        try{
            Log log=new Log();
            Connection con=log.getCon();
            EC.insert(con);
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return EC;
    }

    @PostMapping("/Termine")
    Termine newTerm(@RequestBody Termine term){
        try{
            Log log=new Log();
            Connection con=log.getCon();
            term.insert(con);
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return term;
    }

    @PostMapping("/Signalements")
    Signalement newSign(@RequestBody Signalement sign){
        try{
            Log log=new Log();
            Connection con=log.getCon();
            sign.insert(con);
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return sign;
    }

    @GetMapping("/Signalements/Users/{id}")
    String signs(@PathVariable Long id){
        String retour=null;
         try{
            Log log=new Log();
            Connection con=log.getCon();
            Signalement sign=new Signalement();
            Gson gson=new Gson();
            sign.set_IdUser(id);
            retour=gson.toJson(sign.pgFind(con));
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return retour;
    }

    @GetMapping("/Signalements/{id}")
    String fiche(@PathVariable Long id){
        String retour=null;
         try{
            Log log=new Log();
            Connection con=log.getCon();
            Signalement sign=new Signalement();
            Gson gson=new Gson();
            sign.set_Id(id);
            retour=gson.toJson(sign.pgFind(con));
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return retour;
    }

    

    // @RequestMapping("/Signalements/idSignalement")
    // public String signalements(){
    //     String retour=null;
    //     try{
    //         Log log=new Log();
    //         Connection con=log.getCon();
    //         Gson gson=new Gson();
    //         Signalement sign=new Signalement();
    //         sign.set_Id(idSignalement);
            
    //         Object[]signs=sign.pgFindLike(con);
    //         retour=gson.toJson(signs); 
    //         log.close();
    //     }catch(Exception e){
    //         e.printStackTrace();
    //         return e.getMessage();
    //     }
    //     return retour;

    //     }   
    


    
   
}

package signalement.app.controller;

import java.sql.Connection;
import java.util.Date;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @RequestMapping("/test")
    public String eleves(){
        String result=null;
        try {
            Log log=new Log();
            Connection con=log.getCon();
            Gson gson=new Gson();
            Test filter=new Test();
            Object[]test=filter.find(con);
            result=gson.toJson(test); 
            log.close();
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return result;
    }
    @GetMapping("/test/{name}")
    public String getSpecificMaladies(@PathVariable("name") String name) throws Exception {
        Gson gson = new Gson();
        Log log=null;
        Object[]all=null;
        try {
            log = new Log();
            Connection con = log.getCon();
            Test filter = new Test(name);
            all = filter.pgFindLike(con);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }finally{
            log.close();
        }
        return gson.toJson(new ReturnMessage("token", "ehehe", true, true, all));
    }

    // affectation debut
    @PostMapping("/affectations/{idRegion}/{idSignalement}")
    public String affect(@PathVariable("idRegion") String idRegion, @PathVariable("idSignalement") String idSignalement) throws Exception{
        Gson gson = new Gson();
        Log log=null;
        Object[]all=null;
        try {
            log = new Log();
            Connection con = log.getCon();
            Date date= new Date(Fonctions.dateNow());
            Affectation a= new Affectation(null,date,Integer.valueOf(idRegion),Integer.valueOf(idSignalement));
            a.insert(con);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }finally{
            log.close();
        }
        return gson.toJson(new ReturnMessage("token", "ehehe", true, true, all));
    }
    // affectation fin
}

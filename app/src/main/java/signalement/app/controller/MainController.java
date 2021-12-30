package signalement.app.controller;

import java.sql.Connection;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping("/Signalements")
    public String signalements(){
        String retour=null;
        try{
            Log log=new Log();
            Connection con=log.getCon();
            Gson gson=new Gson();
            Signalement sign=new Signalement();
            Object[]signs=sign.find(con);
            if(signs.length==0){
                                System.out.println(signs.length);

                return gson.toJson("Problems occured.");
            }
            
            retour=gson.toJson(signs); 
            log.close();
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return retour;

        }


    


    
   
}

package signalement.app.controller;

import java.sql.Connection;

import com.google.gson.Gson;

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
    
}

package signalement.app.Models;

import java.sql.*;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.time.*;

public class Fonctions {
    public Fonctions() {

    }

    // insert
    public void insert(Connection con) throws Exception {
        String[] nomChampsSplited = getGetWithoutGet(this);
        Method[] allGet = getGetWithoutId(this);
        String nomTable = getNomTable(this);

        String valeurs = "'" + String.valueOf(allGet[0].invoke(this, (Object[]) null)) + "'";
        if(allGet[0].invoke(this, (Object[]) null)==null){
            valeurs="null";
        }
        String nomChamps = "";
        String virj = "";
        for (int i = 0; i < nomChampsSplited.length; i++) {
            if (nomChampsSplited[i].compareToIgnoreCase("id") != 0) {
                nomChamps = nomChamps + virj + nomChampsSplited[i];
                virj = ",";
            }
        }
        for (int j = 1; j < allGet.length; j++) {

            if(allGet[j].invoke(this, (Object[]) null)==null){
                System.out.println("le truc = "+String.valueOf(allGet[j].invoke(this, (Object[]) null)));

                valeurs = valeurs + ",null";

            }
            else{
                valeurs = valeurs + ",'" + String.valueOf(allGet[j].invoke(this, (Object[]) null)) + "'";

            }
        }
        try {
            String req = "INSERT INTO " + nomTable + "(" + nomChamps + ") VALUES(" + valeurs + ")";
            System.out.println(req);
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate(req);
            stmt.executeUpdate("commit");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate("rollback");
            stmt.close();
        }
    }

    // simple insert
    public void simpleInsert(Connection con, String query) throws Exception {
        try {
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            stmt.executeUpdate("commit");
            stmt.close();
        } catch (Exception e) {
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate("rollback");
            stmt.close();
            throw e;
        }
    }

    // delete
    public void delete(Connection con) throws Exception {
        Method[] allGet = getGet(this);
        String condition = " where ";
        String and = "";
        try {
            int count = 0;
            for (int i = 0; i < allGet.length; i++) {
                if (allGet[i].invoke(this, (Object[]) null) != null) {
                    condition = condition + and + allGet[i].getName().substring(4) + "='"
                            + allGet[i].invoke(this, (Object[]) null) + "'";
                    and = " and ";
                } else {
                    count += 1;
                }
            }
            String nomTable = this.getClass().getSimpleName();
            java.sql.Statement stmt = con.createStatement();
            String query = "";
            if (count == allGet.length) {
                query = "Delete  from " + nomTable;
            } else {
                query = "Delete from " + nomTable + condition;
            }
            stmt.executeUpdate(query);
            stmt.executeUpdate("commit");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // test update
    public void set(String id, Object initial, Object newValue, Connection con) {
        try {
            Method[] allGet = getGetWithoutId(this);
            String condition = " where id='" + id + "'";
            Vector<String> toUpdate = new Vector<String>();
            String virj = "";
            String set = " SET ";
            for (int i = 0; i < allGet.length; i++) {

                if (allGet[i].getReturnType().getSimpleName()
                        .compareToIgnoreCase(newValue.getClass().getSimpleName()) == 0
                        && allGet[i].invoke(this, (Object[]) null).equals(initial.getClass().cast(initial)) == true) {
                    toUpdate.addElement(allGet[i].getName().substring(4));
                }
            }
            Object[] toUpdateArray = toUpdate.toArray();
            for (int j = 0; j < toUpdateArray.length; j++) {
                String c = (String) toUpdateArray[j];
                set = set + virj + c + "='" + newValue.getClass().cast(newValue) + "'";
                virj = ", ";
            }
            String nomTable = getNomTable(this);
            String req = "UPDATE " + nomTable + set + condition;

            java.sql.Statement stmt = con.createStatement();
            System.out.println(req);
            stmt.executeUpdate(req);
            stmt.executeUpdate("commit");

            stmt.close();
        } catch (Exception ee) {
            ee.printStackTrace();
            try {
                java.sql.Statement stmt = con.createStatement();
                stmt.executeQuery("rollback");
                stmt.close();
            } catch (Exception eee) {
                eee.printStackTrace();
            }
        }
    }

    // find
    @SuppressWarnings("deprecation")
    public Object[] find(Connection con) {
        Class<? extends Fonctions> c = this.getClass();
        Object[] resultat = null;
        Method[] allGet = getGet(this);
        Method[] allSet = getSet(this);
        String condition = " where ";
        String and = "";
        try {
            int count = 0;
            for (int i = 0; i < allGet.length; i++) {
                if (allGet[i].invoke(this, (Object[]) null) != null) {
                    condition = condition + and + allGet[i].getName().substring(4) + "='"
                            + allGet[i].invoke(this, (Object[]) null) + "'";
                    and = " and ";
                } else {
                    count += 1;
                }
            }
            String nomTable = this.getClass().getSimpleName();
            java.sql.Statement stmt = con.createStatement();
            String query = "";
            if (count == allGet.length) {
                query = "select* from " + nomTable;
            } else {
                query = "Select * from " + nomTable + condition;
            }
            ResultSet resTaille = stmt.executeQuery(query);
            int taille = 0;
            int indice = 0;
            while (resTaille.next()) {
                taille++;
            }
            resTaille.close();
            resultat = new Object[taille];
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                resultat[indice] = new Object();
                resultat[indice] = c.newInstance();
                for (int x = 0; x < allSet.length; x++) {
                    allSet[x].invoke(resultat[indice], res.getString(allSet[x].getName().substring(4)));
                }
                indice++;
            }
            res.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public Object[] findLike(Connection con) {
        Class<? extends Fonctions> c = this.getClass();
        Object[] resultat = null;
        Method[] allGet = getGet(this);
        Method[] allSet = getSet(this);
        String condition = " where ";
        String and = "";
        try {
            int count = 0;
            for (int i = 0; i < allGet.length; i++) {
                if (allGet[i].invoke(this, (Object[]) null) != null) {
                    condition = condition + and + allGet[i].getName().substring(4) + " LIKE '"
                            + allGet[i].invoke(this, (Object[]) null) + "%'";
                    and = " and ";
                } else {
                    count += 1;
                }
            }
            String nomTable = this.getClass().getSimpleName();
            java.sql.Statement stmt = con.createStatement();
            String query = "";
            if (count == allGet.length) {
                query = "select* from " + nomTable;
            } else {
                query = "Select * from " + nomTable + condition;
            }
            System.out.println(query);
            ResultSet resTaille = stmt.executeQuery(query);
            int taille = 0;
            int indice = 0;
            while (resTaille.next()) {
                taille++;
            }
            resTaille.close();
            resultat = new Object[taille];
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                resultat[indice] = new Object();
                resultat[indice] = c.newInstance();
                for (int x = 0; x < allSet.length; x++) {
                    allSet[x].invoke(resultat[indice], res.getString(allSet[x].getName().substring(4)));
                }
                indice++;
            }
            res.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    // postgres specific
    public Object[] pgFind(Connection con) {
        Class<? extends Fonctions> c = this.getClass();
        Object[] resultat = null;
        Method[] allGet = getGet(this);
        Method[] allSet = getSet(this);
        String condition = " where ";
        String and = "";
        try {
            int count = 0;
            for (int i = 0; i < allGet.length; i++) {
                if (allGet[i].invoke(this, (Object[]) null) != null) {
                    condition = condition + and + allGet[i].getName().substring(4) + "='"
                            + allGet[i].invoke(this, (Object[]) null) + "'";
                    and = " and ";
                } else {
                    count += 1;
                }
            }
            String nomTable = this.getClass().getSimpleName();
            java.sql.Statement stmt = con.createStatement();
            String query = "";
            if (count == allGet.length) {
                query = "select* from " + nomTable;
            } else {
                query = "Select * from " + nomTable + condition;
            }
            ResultSet resTaille = stmt.executeQuery(query);
            int taille = 0;
            int indice = 0;
            while (resTaille.next()) {
                taille++;
            }
            resTaille.close();
            resultat = new Object[taille];
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                resultat[indice] = new Object();
                resultat[indice] = c.newInstance();
                for (int x = 0; x < allSet.length; x++) {
                    allSet[x].invoke(resultat[indice], res.getString(allSet[x].getName().substring(4)));
                }
                indice++;
            }
            res.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public Object[] pgFindLike(Connection con) {
        Class<? extends Fonctions> c = this.getClass();
        Object[] resultat = null;
        Method[] allGet = getGet(this);
        Method[] allSet = getSet(this);
        String condition = " where ";
        String and = "";
        try {
            int count = 0;
            for (int i = 0; i < allGet.length; i++) {
                if (allGet[i].invoke(this, (Object[]) null) != null) {
                    condition = condition + and + allGet[i].getName().substring(4) + " ILIKE '"
                            + allGet[i].invoke(this, (Object[]) null) + "%'";
                    and = " and ";
                } else {
                    count += 1;
                }
            }
            String nomTable = this.getClass().getSimpleName();
            java.sql.Statement stmt = con.createStatement();
            String query = "";
            if (count == allGet.length) {
                query = "select* from " + nomTable;
            } else {
                query = "Select * from " + nomTable + condition;
            }
            ResultSet resTaille = stmt.executeQuery(query);
            int taille = 0;
            int indice = 0;
            while (resTaille.next()) {
                taille++;
            }
            resTaille.close();
            resultat = new Object[taille];
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                resultat[indice] = new Object();
                resultat[indice] = c.newInstance();
                for (int x = 0; x < allSet.length; x++) {
                    allSet[x].invoke(resultat[indice], res.getString(allSet[x].getName().substring(4)));
                }
                indice++;
            }
            res.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    // Fonctions keeeeeely bdb
    public static Method[] getGetWithoutId(Object o) {
        Class<? extends Object> c = o.getClass();
        Method[] all = c.getDeclaredMethods();
        Vector<Method> v = new Vector<Method>();
        for (int i = 0; i < all.length; i++) {
            if (all[i].getName().startsWith("get_")) {
                if (all[i].getName().substring(4).compareToIgnoreCase("id") != 0) {
                    v.addElement(all[i]);
                }
            }
        }
        Object[] toMethArray = v.toArray();
        Method[] resultat = new Method[toMethArray.length];
        for (int x = 0; x < resultat.length; x++) {
            Method m = (Method) toMethArray[x];
            resultat[x] = m;
        }
        return resultat;
    }

    public static Method[] getGet(Object o) {
        Class<? extends Object> c = o.getClass();
        Method[] all = c.getDeclaredMethods();
        Vector<Method> v = new Vector<Method>();
        for (int i = 0; i < all.length; i++) {
            if (all[i].getName().startsWith("get_")) {
                v.addElement(all[i]);
            }
        }
        Object[] toMethArray = v.toArray();
        Method[] resultat = new Method[toMethArray.length];
        for (int x = 0; x < resultat.length; x++) {
            Method m = (Method) toMethArray[x];
            resultat[x] = m;
        }
        return resultat;
    }

    public static Method[] getSet(Object o) {
        Class<? extends Object> c = o.getClass();
        Method[] all = c.getDeclaredMethods();
        Vector<Method> v = new Vector<Method>();
        for (int i = 0; i < all.length; i++) {
            if (all[i].getName().startsWith("set_")) {
                v.addElement(all[i]);
            }
        }
        Object[] toMethArray = v.toArray();
        Method[] resultat = new Method[toMethArray.length];
        for (int x = 0; x < resultat.length; x++) {
            Method m = (Method) toMethArray[x];
            resultat[x] = m;
        }
        return resultat;
    }

    public static String[] getGetWithoutGet(Object o) {
        Method[] m = getGet(o);
        String[] resultat = new String[m.length];
        for (int i = 0; i < m.length; i++) {
            resultat[i] = m[i].getName().substring(4);
        }
        return resultat;
    }

    public static String getNomTable(Object o) {
        return o.getClass().getSimpleName();
    }

    public static String dateNow() {
        LocalDateTime now = LocalDateTime.now();
        String aaa = String.valueOf(now.getDayOfMonth()) + "/" + String.valueOf(now.getMonthValue()) + "/"
                + String.valueOf(now.getYear()) + " " + String.valueOf(now.getHour()) + ":"
                + String.valueOf(now.getMinute()) + ":" + String.valueOf(now.getSecond());
        return aaa;
    }

    // token related debut
    public static String createToken(String newUserId) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        newUserId = newUserId + Fonctions.dateNow();
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(newUserId.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }

    // public static ReturnMessage verifyToken(String newToken, UserTokenRepository userTokenRepository) {
    //     ReturnMessage resultat = null;
    //     UserToken result=userTokenRepository.findByToken(newToken);
    //     if (result.getIdUser()==null) {
    //         resultat = new ReturnMessage(null, "invalid token", false, false, null);
    //     } else {
    //         resultat = new ReturnMessage(newToken, "valid Token", true, true, result.getIdUser());
    //     }
    //     return resultat;
    // }

    // token related fin
    // mdp encryption
    public String encrypt(String mdp) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(mdp.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }


    public String getIdMax(Connection con){
        String retour="";
        try{
            
            String req="select max(id) as id from "+this.getClass().getSimpleName();
              java.sql.Statement stmt = con.createStatement();

            ResultSet res=stmt.executeQuery(req);
            while(res.next()){
                retour=res.getString(1);
            }
            if(retour==null){
                retour="1";
            }
         

            stmt.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return retour;
    }
}
package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Rol;
import com.TBDvoluntariado.ProyectoTBD.models.User;
import com.TBDvoluntariado.ProyectoTBD.models.Vol_habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class UserRepositoryImp implements UserRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public List<User> getAllUser(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM users").executeAndFetch(User.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM users WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(User.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserByToken(String token){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM users WHERE loginToken = :v_token")
                    .addParameter("v_token", token)
                    .executeAndFetchFirst(User.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int biggestId(){
        try(Connection conn = sql2o.open()){
            User temp = conn.createQuery("SELECT * FROM users ORDER BY id DESC").executeAndFetchFirst(User.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public User createUser(User user){
        int id = this.biggestId() + 1;
        int invisible = 0;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO users (id, name, mail, phone, password, logintoken , idrol, invisible) VALUES(:id, :name, :mail, :phone, :password, :logintoken , :idrol, :invisible)", true)
                    .addParameter("id", id)
                    .addParameter("name", user.getName())
                    .addParameter("mail", user.getMail())
                    .addParameter("phone", user.getPhone())
                    .addParameter("password", user.getPassword())
                    .addParameter("logintoken", user.getLoginToken())
                    .addParameter("idrol", user.getIdRol())
                    .addParameter("invisible", invisible)
                    .executeUpdate().getKey();
            user.setId(id);
            return user;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }


    @Override
    public void deleteUser(int id, User user) {
        String deleteSql = "UPDATE users SET invisible=:invisible WHERE id = :idParam";
        try (Connection con = sql2o.open()) {
            User valorAntiguo = con.createQuery("SELECT * FROM users WHERE id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(User.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(user.getInvisible() != null){
                consulta.addParameter("invisible", user.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("The User was delete successfully.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public String logIn(User user) {
        int visible = 0;
        try(Connection conn = sql2o.open()){
            List<User> findUsers = conn.createQuery("select * from users where name=:name and password=:password and invisible=:invisible")
                .addParameter("name", user.getName())
                .addParameter("password", user.getPassword())
                .addParameter("invisible", visible)
                .executeAndFetch(User.class);
            if(findUsers.size() == 1){
                int token = 1;
                try(Connection con = sql2o.open()){
                    con.createQuery("UPDATE users SET name=:name, mail=:mail, phone=:phone, password=:password, logintoken=:logintoken , idrol=:idrol WHERE id=:id ", true)
                            .addParameter("id", findUsers.get(0).getId())
                            .addParameter("name", findUsers.get(0).getName())
                            .addParameter("mail", findUsers.get(0).getMail())
                            .addParameter("phone", findUsers.get(0).getPhone())
                            .addParameter("password", findUsers.get(0).getPassword())
                            .addParameter("logintoken", token)
                            .addParameter("idrol", findUsers.get(0).getIdRol())
                            .executeUpdate().getKey();
                    return "Login Successfully";
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return  "Login Fail";
                }
            }else{
                return "Login Fail";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Login Fail";
        }
    }
    @Override
    public String logOut(User user){
        int visible = 0;
        try(Connection conn = sql2o.open()){
            List<User> findUsers = conn.createQuery("select * from users where name=:name and password=:password and invisible=:invisible")
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword())
                    .addParameter("invisible", visible)
                    .executeAndFetch(User.class);
            if(findUsers.size() == 1){
                int token = 0;
                try(Connection con = sql2o.open()){
                    con.createQuery("UPDATE users SET name=:name, mail=:mail, phone=:phone, password=:password, logintoken=:logintoken , idrol=:idrol WHERE id=:id ", true)
                            .addParameter("id", findUsers.get(0).getId())
                            .addParameter("name", findUsers.get(0).getName())
                            .addParameter("mail", findUsers.get(0).getMail())
                            .addParameter("phone", findUsers.get(0).getPhone())
                            .addParameter("password", findUsers.get(0).getPassword())
                            .addParameter("logintoken", token)
                            .addParameter("idrol", findUsers.get(0).getIdRol())
                            .executeUpdate().getKey();
                    return "LogOut Successfully";
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return  "LogOut Fail";
                }
            }else{
                return "LogOut Fail";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return " Deslogeado Fail";
        }
    }




}

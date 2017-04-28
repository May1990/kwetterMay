package twitter_webservice.backingbeans;

import twitter_webservice.domain.Userr;
import twitter_webservice.service.UserMgr;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Anna-May on 09/04/2017.
 */
@Named("registerLogIn")
@SessionScoped
public class RegisterLogInBean implements Serializable {
    // log in
    private String username;
    private String password;

    // register
    private String regUsername;
    private String regPassword;
    private String regPasswrdCnfrm;
    private String email;
    private String emailCnfrm;
    private String website;
    private String name;

    //@ManagedProperty(value="#{logInUser}")
    private Userr logInUser;

    @Inject
    private UserMgr userMgr;

    //region getterSetter
    public Userr getLogInUser() {
        return logInUser;
    }

    public void setLogInUser(Userr logInUser) {
        this.logInUser = logInUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegUsername() {
        return regUsername;
    }

    public void setRegUsername(String regUsername) {
        this.regUsername = regUsername;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }

    public String getRegPasswrdCnfrm() {
        return regPasswrdCnfrm;
    }

    public void setRegPasswrdCnfrm(String regPasswrdCnfrm) {
        this.regPasswrdCnfrm = regPasswrdCnfrm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCnfrm() {
        return emailCnfrm;
    }

    public void setEmailCnfrm(String emailCnfrm) {
        this.emailCnfrm = emailCnfrm;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    public String registerUser(){
        String nextPage = "";
        if(name == "" ||regUsername == "" || website == "" || regPassword != regPasswrdCnfrm || email != emailCnfrm){
            nextPage = "login_error";
            return nextPage;
        }

        Userr user = userMgr.createUser(regPassword, email, name, regUsername, null, null, null, website);

        if(user == null){
            nextPage = "login_error";
        } else if(user.getId() == null){
            nextPage = "login_error";
        } else {
            nextPage = "index";
            logInUser = user;
        }

        return nextPage;
    }

    public String logInUserByUserName(){
        String nextPage = "";
        if(username!= ""){
            logInUser = userMgr.getUserByUserName(username);
            if(logInUser == null){
                nextPage = "login_error";
            }else{
                nextPage = "index";
            }
        }else{
            nextPage = "login_error";
        }

        return nextPage;
    }
}

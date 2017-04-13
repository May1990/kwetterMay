package twitter_webservice.backingbeans;

import javax.faces.bean.ManagedBean;

/**
 * Created by Anna-May on 09/04/2017.
 */
@ManagedBean(name = "registerLogIn")
public class registerLogInBean {
    // log in
    private String username;
    private String password;

    // register
    private String regUsername;
    private String regPassword;
    private String regPasswrdCnfrm;
    private String email;
    private String emailCnfrm;

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
}

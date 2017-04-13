package twitter_webservice.backingbeans;

import twitter_webservice.domain.Userr;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Anna-May on 10/04/2017.
 */
@Named("test")
@SessionScoped
public class testBean implements Serializable{

    private ExternalContext excontext;
    private Userr user;
    private String name;

    @PostConstruct
    public void init(){

    }

    public void test() {
        excontext = FacesContext.getCurrentInstance().getExternalContext();
        name = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    public Userr getUser() {
        return user;
    }

    public void setUser(Userr user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

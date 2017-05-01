package twitter_webservice.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna-May on 06/04/2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Role {

    @Id
    private String roleName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="USER_ROLE",
            joinColumns = @JoinColumn(name = "roleName",
                    referencedColumnName = "ROLENAME", updatable = false),
            inverseJoinColumns = @JoinColumn(name = "userr_ID",
                    referencedColumnName = "ID", updatable = false))
    private List<Userr> users;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
        this.users = new ArrayList<Userr>();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Userr> getUsers() {
        return users;
    }

    public void setUsers(List<Userr> users) {
        this.users = users;
    }

    public void addUser(Userr user){
        users.add(user);
    }
}

package twitter_webservice.doa;

import twitter_webservice.domain.Userr;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Anna-May on 09/03/2017.
 */
@Local
public interface UsserDAO {
    Userr create(Userr user);
    void edit(Userr user);
    List<Userr> findAll();
    Userr findByUserName(String userName);
    void remove(Userr user);
    List<Userr> findFollowersByUserName(String userName);
    List<Userr> findFollowingByUserName(String userName);
    int findCountFollowingByUsername(Long id);
    int findCountFollowersByUsername(Long id);
    boolean doesUsernameExist(String tempUserName);
}

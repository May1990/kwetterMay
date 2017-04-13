package twitter_webservice.backingbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

/**
 * Created by Anna-May on 08/04/2017.
 */
@ManagedBean(name = "tweetMgr")
public class TweetMgrBean {
    private String searchWord;
    private String newTweet;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getNewTweet() {
        return newTweet;
    }

    public void setNewTweet(String newTweet) {
        this.newTweet = newTweet;
    }

    public void searchWordChanged(ValueChangeEvent vce)
    {
        //searchByTitle((String) vce.getNewValue());
    }

    public void addNewTweet(String tweet){

    }
}

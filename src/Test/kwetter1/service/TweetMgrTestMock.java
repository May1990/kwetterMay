package kwetter1.service;

import org.assertj.core.api.IntegerAssert;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import twitter_webservice.doa.TweetDAO_impl;
import twitter_webservice.domain.Tweet;
import twitter_webservice.domain.Userr;
import twitter_webservice.service.TweetMgr;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;


/**
 * Created by Anna-May on 16/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TweetMgrTestMock {
    @InjectMocks
    TweetMgr tweetMgr;

    @Mock
    TweetDAO_impl tweetDao_impl;

    Tweet tweetOne;
    Tweet tweetTwo;
    Tweet tweetThree;
    Tweet tweetFour;

    public TweetMgrTestMock() {

    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        tweetOne = mock(Tweet.class);
        tweetTwo = mock(Tweet.class);
        tweetThree = mock(Tweet.class);
        tweetFour = mock(Tweet.class);
    }

    @Test
    public void getTweetsTest() {
        when(tweetDao_impl.findAll()).thenReturn(Arrays.asList(tweetOne, tweetTwo, tweetThree, tweetFour));

        List<Tweet> listOfTweets = tweetMgr.getTweets(); //<--
        assertEquals(4, listOfTweets.size());

        Tweet tweetTone = tweetMgr.getTweets().get(0); //<--
        assertEquals(tweetOne, tweetTone);

        verify(tweetDao_impl, times(2)).findAll();
    }

    @Test
    public void getTweetsByUserNameTest() {
//        when(tweetDao_impl.findByUserName(tweetFour.getUserName())).thenReturn(Arrays.asList(tweetTwo, tweetThree));
//
//        List<Tweet> listOfTweets = tweetMgr.getTweetsByUserName(tweetFour.getUserName()); //<--
//        assertEquals(2, listOfTweets.size());
//
//        Tweet tweetTtwo = tweetMgr.getTweetsByUserName(tweetFour.getUserName()).get(0); //<--
//        assertEquals(tweetTwo, tweetTtwo);
//
//        verify(tweetDao_impl, times(2)).findByUserName(tweetFour.getUserName());
    }
}

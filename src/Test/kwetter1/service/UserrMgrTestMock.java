package kwetter1.service;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import twitter_webservice.doa.TweetDAO_impl;
import twitter_webservice.doa.UserrDAO_impl;
import twitter_webservice.domain.Tweet;
import twitter_webservice.domain.Userr;
import twitter_webservice.service.TweetMgr;
import twitter_webservice.service.UserMgr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Anna-May on 16/03/2017.
 */
public class UserrMgrTestMock {

    @InjectMocks
    UserMgr userMgr;

    @Mock
    UserrDAO_impl userrDao_impl;

    Userr userOne;
    Userr userTwo;
    Userr userThree;
    Userr userFour;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userOne = mock(Userr.class);
        userTwo = mock(Userr.class);
        userThree = mock(Userr.class);
        userFour = mock(Userr.class);
    }

    @Test
    public void getUsersTest(){
        when(userrDao_impl.findAll()).thenReturn(Arrays.asList(userOne, userTwo, userThree, userFour));

        List<Userr> listOfUsers = userMgr.getUsers(); //<--
        assertEquals(4, listOfUsers.size());

        Userr UserrTone = userMgr.getUsers().get(0); //<--
        assertEquals(userOne, UserrTone);

        verify(userrDao_impl, times(2)).findAll();
    }

    @Test
    public void getFollowersTest(){
        when(userrDao_impl.findFollowersByUserName(userThree.getUserName())).thenReturn(Arrays.asList(userOne, userTwo, userFour));

        List<Userr> listOfUsers = userMgr.getFollowers(userThree.getUserName()); //<--
        assertEquals(3, listOfUsers.size());

        Userr UserTtwo = userMgr.getFollowers(userThree.getUserName()).get(1); //<--
        assertEquals(userTwo, UserTtwo);

        verify(userrDao_impl, times(2)).findFollowersByUserName(userThree.getUserName());
    }

    @Test
    public void getFollowingTest(){
        when(userrDao_impl.findFollowingByUserName(userFour.getUserName())).thenReturn(Arrays.asList(userTwo, userThree));

        List<Userr> listOfUsers = userMgr.getFollowing(userFour.getUserName()); //<--
        assertEquals(2, listOfUsers.size());

        Userr UserTtwo = userMgr.getFollowing(userFour.getUserName()).get(0); //<--
        assertEquals(userTwo, UserTtwo);

        verify(userrDao_impl, times(2)).findFollowingByUserName(userFour.getUserName());
    }

    @Test
    public void getUserByUserNameTest(){
        Userr mockUser = mock(Userr.class);
        String Tom = "Tommy";

        when(userrDao_impl.findByUserName(Tom)).thenReturn(mockUser);
        Userr result = userMgr.getUserByUserName(Tom);

        verify(userrDao_impl, times(1)).findByUserName(Tom);
        assertEquals(result, mockUser);
    }

    @Test
    public void createUserTest(){
//        String userName = "floris2_1944";
//
//        Userr userFive = mock(Userr.class);
//
//        when(userrDao_impl.create(userFive)).thenReturn(userFive);
//
//        Userr userTfive = userMgr.createUser(userFive);
//        assertEquals(userFive, userTfive);
    }
}

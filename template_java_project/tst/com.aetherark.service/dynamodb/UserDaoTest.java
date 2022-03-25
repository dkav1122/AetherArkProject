package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UserDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void getUser_withUsername_returnUser() {
        //GIVEN
        String expectedUsername = "expectedName";
        User expectedUser = userHelper(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUsername)).thenReturn(expectedUser);
        //WHEN
        User user = userDao.getUser(expectedUsername);
        //THEN
        assertEquals(expectedUser, user);
    }

    @Test
    public void getUser_withUsernameNotFound_throwsUserNotFoundException() {
        //GIVEN
        String expectedUsername = "expectedName";
        String differentUsername = "Eric";
        when(dynamoDBMapper.load(User.class, differentUsername)).thenThrow(UserNotFoundException.class);
        //WHEN + // THEN
        assertThrows(UserNotFoundException.class, () -> userDao.getUser(expectedUsername));
    }

    @Test
    public void saveUser_withUserFound_returnsUser() {
        //GIVEN
        String expectedUsername = "expectedName";
        User expectedUser = userHelper(expectedUsername);
        //WHEN
        User user = userDao.saveUser(expectedUser);
        //THEN
        assertEquals(expectedUser, user);
        verify(dynamoDBMapper, times(1)).save(expectedUser);
    }

    @Test
    public void deleteUser_withUsername_returnsUser() {
        //GIVEN
        String expectedUsername = "expectedName";
        User expectedUser = userHelper(expectedUsername);
        //WHEN
        User user = userDao.deleteUser(expectedUser);
        //THEN
        assertEquals(expectedUser, user);
        verify(dynamoDBMapper, times(1)).delete(expectedUser);
    }

    @Test
    public void addToUserSolarSystemId_withUsernameAndSolarSystemId_returnsSolarSystemId() {
        //GIVEN
        String expectedUsername = "expectedName";
        String solarSystemId = "5";
        User expectedUser = userHelper(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUsername)).thenReturn(expectedUser);
        //WHEN
        userDao.addToUserSolarSystemId(expectedUsername, solarSystemId);
        //THEN
        assertEquals(5, expectedUser.getSolarSystemIds().size());
        verify(dynamoDBMapper, times(1)).save(expectedUser);
    }

    @Test
    public void removeFromUserSolarSystemId_withUsernameAndSolarSystemId_returnsSolarSystemId() {
        //GIVEN
        String expectedUsername = "expectedName";
        String solarSystemId = "4";
        User expectedUser = userHelper(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUsername)).thenReturn(expectedUser);
        //WHEN
        userDao.removeFromUserSolarSystemId(expectedUsername,solarSystemId);
        //THEN
        assertEquals(3, expectedUser.getSolarSystemIds().size());
        verify(dynamoDBMapper, times(1)).save(expectedUser);
    }

    @Test
    public void addToUserCelestialBodyId_withUsernameAndCelestialBodyId_returnsCelestialBodyId() {
        //GIVEN
        String expectedUsername = "expectedName";
        String CelestialBodyId = "10";
        User expectedUser = userHelper(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUsername)).thenReturn(expectedUser);
        //WHEN
            userDao.addToUserCelestialBodyId(expectedUsername,CelestialBodyId);
        //THEN
        assertEquals(5, expectedUser.getCelestialBodyIds().size());
        verify(dynamoDBMapper, times(1)).save(expectedUser);
    }

    @Test
    public void removeFromUserCelestialBodyId_withUsernameAndCelestialBodyId_returnsCelestialBodyId() {
        //GIVEN
        String expectedUsername = "expectedName";
        String CelestialBodyId = "9";
        User expectedUser = userHelper(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUsername)).thenReturn(expectedUser);
        //WHEN
        userDao.removeFromCelestialBodyId(expectedUsername,CelestialBodyId);
        //THEN
        assertEquals(3, expectedUser.getCelestialBodyIds().size());
        verify(dynamoDBMapper, times(1)).save(expectedUser);
    }

    private static User userHelper(String name) {
        String expectedUsername = name;
        String expectedEmail = "www.expected@aetherark.com";
        String[] expectedSolarSystemIdsArray = new String[]{"1", "2", "3", "4"};
        String[] expectedCelestialBodyIdsArray = new String[]{"6", "7", "8", "9"};
        List<String> expectedSolarSystemIds = new ArrayList<>(Arrays.asList(expectedSolarSystemIdsArray));
        List<String> expectedCelestialBodyIds = new ArrayList<>(Arrays.asList(expectedCelestialBodyIdsArray));

        User expectedUser = new User();
        expectedUser.setName(expectedUsername);
        expectedUser.setEmail(expectedEmail);
        expectedUser.setSolarSystemIds(expectedSolarSystemIds);
        expectedUser.setCelestialBodyIds(expectedCelestialBodyIds);

        return expectedUser;
    }
}

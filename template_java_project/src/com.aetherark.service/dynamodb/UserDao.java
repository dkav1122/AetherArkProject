package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.User;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a {@link User} to represent the model in DynamoDB.
 */
@Singleton
public class UserDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a UserDao object.
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the User table
     */
    @Inject
    public UserDao(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Returns the {@link User} corresponding to the specified username.
     *
     * @param name the username
     * @return the stored User, or if the given username is not found, will throw a UserNotFoundException.
     */
    public User getUser(String name){
        User user = this.dynamoDBMapper.load(User.class, name);

        if (user == null) {
            //todo throw new UserNotFoundException("User: " + name + " not found.")
        }
        return user;
    }

    /**
     * Saves the {@link User} to our database.
     *
     * @param user the user to be saved
     * @return the saved user
     */
    public User saveUser(User user) {
        this.dynamoDBMapper.save(user);
        return user;
    }

    /**
     * Deletes the {@link User} from our database.
     *
     * @param user to be deleted
     * @return the deleted user
     */
    public User deleteUser(User user) {
        this.dynamoDBMapper.delete(user);
        return user;
    }

}

package api;

import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;

import java.io.IOException;
import java.util.List;

public interface UserService
{
    List<User> getAllUsers() throws IOException;

    boolean addUser(User user) throws UserLoginAlreadyExistException, UserShortLengthLoginException, UserShortLengthPasswordException;

    User getUserById(Long userId) throws IOException;

    User getUserByLogin(String login) throws IOException;

    void removeUserById(Long id) throws IOException;

    boolean isCorrectLoginAndPassword(String login, String password);
}

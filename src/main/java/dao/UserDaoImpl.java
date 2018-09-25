package dao;

import api.UserDao;
import entity.User;
import entity.parser.UserParser;
import utils.FileUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao
{
    private static final String fileName = "users.txt";

    private static UserDaoImpl instance = null;

    public UserDaoImpl(String fileName)
    {
        try
        {
            FileUtils.createNewFile(fileName);

        }catch (IOException e)
        {
            System.out.println("Error with creating new file");
        }
    }

    public static UserDaoImpl getInstance()
    {
        if(instance == null)
            instance = new UserDaoImpl(fileName);

        return instance;
    }

    @Override
    public void saveUser(User user) throws IOException
    {
        List<User> users = getAllUsers();

        users.add(user);

        saveUsers(users);

    }

    @Override
    public void saveUsers(List<User> users) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new PrintWriter(new FileOutputStream(fileName, true)));
        FileUtils.clearFiler(fileName);
        for(User user : users)
        {
            writer.write(user.toString());
            writer.newLine();
        }

        writer.close();
    }

    @Override
    public void removeUserById(Long userId) throws IOException
    {
        List<User> users = getAllUsers();

        for(int i = 0; i<users.size(); i++)
        {
            if(users.get(i).getId() == userId)
                users.remove(i);
        }

        saveUsers(users);
    }

    @Override
    public void removeUserByLogin(String login) throws IOException
    {
        List<User> users = getAllUsers();

        for(int i = 0; i<users.size(); i++)
        {
            if(users.get(i).getLogin().equals(login))
                users.remove(i);
        }

        saveUsers(users);
    }

    @Override
    public List<User> getAllUsers() throws IOException
    {
        List<User> users = new ArrayList<User>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String readLine;
        while((readLine = reader.readLine()) != null)
        {
            User user = UserParser.parseUser(readLine);

            if(user != null)
                users.add(user);
        }

        reader.close();

        return users;
    }

    public User getUserByLogin(String login) throws IOException
    {
        List<User> users = getAllUsers();

        for(User user : users)
        {
            if(user.getLogin().equals(login))
                return user;
        }

        return null;
    }
}

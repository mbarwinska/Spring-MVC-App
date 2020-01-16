package pl.sda.intermediate.app.users;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDAO {
    private String filePath = "C:\\Users\\Maja\\users.txt";
    private Map<String, User> users = readUsersFromFile();


    public Map<String, User> readUsersFromFile() {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getEMail(), user);
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean emailExists(String email) {
        return users.containsKey(email);
    }

    public Optional<User> findUserByEmail(String login) {
        return Optional.ofNullable(users.get(login));
    }


}

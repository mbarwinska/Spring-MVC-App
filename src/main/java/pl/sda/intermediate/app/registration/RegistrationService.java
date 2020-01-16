package pl.sda.intermediate.app.registration;

import org.apache.commons.codec.digest.DigestUtils;
import pl.sda.intermediate.app.users.User;
import pl.sda.intermediate.app.users.UserDAO;

public class RegistrationService {
   private UserDAO userDAO;

    public RegistrationService(UserDAO userDAO) {
         this.userDAO = userDAO;
    }

    public void register(RegistrationDTO dto) {

        if (userDAO.emailExists(dto.getEMail())) {
            throw new RuntimeException("Email istnieje: " + dto.getEMail());
        }

        User user = buildUserFromDTO(dto);
        userDAO.addUser(user);
    }

    private User buildUserFromDTO(RegistrationDTO dto) {
        User user = new User();
        user.setBirthDate(dto.getBirthDate());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setEMail(dto.getEMail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPasswordHash(DigestUtils.sha512Hex(dto.getPassword()));
        user.setPesel(dto.getPesel());
        user.setPhone(dto.getPhone());
        user.setStreet(dto.getStreet());
        user.setZipCode(dto.getZipCode());
        user.setPreferEmails(dto.getPreferEmails());
        return user;
    }
}

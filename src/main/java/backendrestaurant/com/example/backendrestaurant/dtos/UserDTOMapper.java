package backendrestaurant.com.example.backendrestaurant.dtos;


import backendrestaurant.com.example.backendrestaurant.models.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {



    public UserModel mapToModel(UserRequestDTO userDTO) {
        var result = new UserModel(-1L);
        result.setUserName(userDTO.getUserName());
        result.setPassword(userDTO.getPassword());
        return result;
    }


}

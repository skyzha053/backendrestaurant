package backendrestaurant.com.example.backendrestaurant.dtos;

import backendrestaurant.com.example.backendrestaurant.models.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@Component
@Validated
public class UserDTOMapper {

    public UserModel mapToModel(@Valid UserRequestDTO userDTO) {
        var result = new UserModel(-1L);
        result.setUserName(userDTO.getUserName());
        result.setPassword(userDTO.getPassword());
        return result;
    }
}

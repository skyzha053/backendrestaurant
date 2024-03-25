package backendrestaurant.com.example.backendrestaurant.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import backendrestaurant.com.example.backendrestaurant.dtos.UserChangePassWordRequestDTO;
import backendrestaurant.com.example.backendrestaurant.dtos.UserDTOMapper;
import backendrestaurant.com.example.backendrestaurant.dtos.UserRequestDTO;
import backendrestaurant.com.example.backendrestaurant.helpers.UrlHelper;
import backendrestaurant.com.example.backendrestaurant.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserDTOMapper userDTOMapper;
    private final UserService userService;
    private final HttpServletRequest request;

    public UserController(UserDTOMapper userDTOMapper, UserService userService, HttpServletRequest request) {
        this.userDTOMapper = userDTOMapper;
        this.userService = userService;
        this.request = request;
    }

    @PostMapping("/users")
    public ResponseEntity<?> CreateUser(@RequestBody @Valid UserRequestDTO userDTO)
    {
        var userModel = userDTOMapper.mapToModel(userDTO);
        userModel.setEnabled(true);
        if(!userService.createUser(userModel, userDTO.getRoles())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(UrlHelper.getCurrentUrlWithId(request, userModel.getId())).build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> ChangePassword(@PathVariable Long id, @RequestBody @Valid UserChangePassWordRequestDTO userDTO)
    {
        var userModel = userDTOMapper.mapToModel(userDTO, id);
        if(!userService.updatePassword(userModel)) {
            return ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok().build();
    }
}

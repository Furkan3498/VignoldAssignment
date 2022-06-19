package de.vignold.vignoldmanagement.service;

import de.vignold.vignoldmanagement.dto.UserDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    UserDTO save(UserDTO userDto);
    UserDTO getByUserName(String userName);
    List<UserDTO> getAll(Sort sort);
    Boolean delete(Long id);
    UserDTO update(Long id,UserDTO userDto);
}

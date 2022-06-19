package de.vignold.vignoldmanagement.service.Impl;

import de.vignold.vignoldmanagement.dao.UserRepository;
import de.vignold.vignoldmanagement.dto.RegistrationRequest;
import de.vignold.vignoldmanagement.dto.UserDTO;
import de.vignold.vignoldmanagement.entity.User;
import de.vignold.vignoldmanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepo, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO save(UserDTO UserDTO) {
        User userDb = userRepo.getByUserName(UserDTO.getUserName());
        if(userDb != null)
            throw new IllegalArgumentException("Username Already Exist!");
        User u = modelMapper.map(UserDTO,User.class);
        u = userRepo.save(u);
        return modelMapper.map(u,UserDTO.class);


    }

    @Override
    public UserDTO getByUserName(String userName) {
        User u = userRepo.getByUserName(userName);
        return modelMapper.map(u,UserDTO.class);
    }

    @Override
    public List<UserDTO> getAll(Sort sort) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        userRepo.deleteById(id);
        return Boolean.TRUE;

    }

    @Override
    public UserDTO update(Long id, UserDTO UserDTO) {
        return null;
    }

    @Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
        try {
            User user = new User();
            user.setNameSurname(registrationRequest.getNameSurname());
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            user.setUserName(registrationRequest.getUsername());
            userRepo.save(user);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("REGISTRATION=>", e);
            return Boolean.FALSE;
        }
    }
}

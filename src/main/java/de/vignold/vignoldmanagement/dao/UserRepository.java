package de.vignold.vignoldmanagement.dao;

import de.vignold.vignoldmanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUserName(String userName);
    List<User> findAll(Sort sort);
    Page<User> findAll(Pageable pageable);
}

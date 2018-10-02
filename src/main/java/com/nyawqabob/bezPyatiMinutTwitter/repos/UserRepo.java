package com.nyawqabob.bezPyatiMinutTwitter.repos;

import com.nyawqabob.bezPyatiMinutTwitter.entity.Message;
import com.nyawqabob.bezPyatiMinutTwitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    @Override
    public Iterable<User> findAll();

    User findByUsername(String username);

    User findByActivationCode(String code);

    public User findByEmail(String email);
}

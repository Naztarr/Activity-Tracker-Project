package com.naz.activitytracker.repositories;

import com.naz.activitytracker.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("select (count(u) > 0) from Users u where u.userName = ?1")
    boolean usernameExists(@NonNull String userName);
    Users findByUserName(String userName);
}

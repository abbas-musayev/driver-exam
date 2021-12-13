package com.example.drivingexam.repo;

import com.example.drivingexam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findUserByEmail(String email);

//    @Query("select case when count (u)>0 then true else false end from User u where u.email=?1 and u.password=?2")
//    Boolean getuserByEmailAndPassword(String email,String password);

    @Query("select u from User u where u.email=?1 and u.password=?2")
    User getUserByEmailAndPassword(String email,String password);

    User findUserByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("select u from User u where u.approved=?1")
    List<User> getUsersByUnApproved(Boolean isfalse);

}

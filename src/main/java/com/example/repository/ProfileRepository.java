package com.example.repository;

import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.role = ?2 where p.id = ?1")
    int updateRole(Long id,ProfileRole role);

    Optional<ProfileEntity> findByPhone(String phone);

    ProfileEntity findByPhoneAndPassword(String phone, String password);

}

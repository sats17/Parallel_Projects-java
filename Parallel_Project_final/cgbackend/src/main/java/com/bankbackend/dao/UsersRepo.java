package com.bankbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankbackend.entities.UserBean;

@Repository
public interface UsersRepo extends JpaRepository<UserBean, Integer>{

}
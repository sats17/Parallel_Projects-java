package com.bankbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankbackend.entities.TransactionBean;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionBean, Integer>{

	
//	@Query("Select t From TransactionBean t where  t.userbean.accountId = ?1")
//	List<TransactionBean> FindData(int accountId);
	
}
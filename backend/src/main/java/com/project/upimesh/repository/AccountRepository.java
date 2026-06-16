package com.project.upimesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.upimesh.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}

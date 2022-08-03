package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Product;

public interface CustomRepository {
	List<Product> searchUsersByApprovalStatus(String searchKey, String approvalStatus);

}

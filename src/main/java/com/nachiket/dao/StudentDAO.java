package com.nachiket.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nachiket.model.Student;

public interface StudentDAO extends JpaRepository<Student, Integer>{
	public List<Student> findStudents_per_Batch(int batchID);
}

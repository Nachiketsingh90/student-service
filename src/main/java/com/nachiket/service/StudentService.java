package com.nachiket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nachiket.client.BatchClient;
import com.nachiket.dao.StudentDAO;
import com.nachiket.model.Student;
import com.nachiket.vo.Attendence;
import com.nachiket.vo.Batch;

import lombok.extern.java.Log;

@Log
@Service
public class StudentService {

	@Autowired
	private StudentDAO studentDTO;
	
	private String URL="";
	
	@Autowired
	private BatchClient batchClient;
	
	public Student createstudent(Student student)
	{
		log.info("Going to create student in studentService->>createstudent");
		if (student!=null)
		{
			System.out.println(student);
			studentDTO.save(student);
		}
		
		log.info("student is registered now");
		return student;
	}
	
	public Student updatestudent(Student student)
	{
		log.info("Ging to update student in studentService->>updatestudent");
		studentDTO.save(student);
		log.info("student is updated now");
		return student;
	}
	public String  deletestudent(int id)
	{
		log.info("Ging to delete student in studentService->>deletestudent");
		String msg;
		if(studentDTO.findById(id).isPresent())
		{
			studentDTO.deleteById(id);
			msg="student deleted with ID: "+id;
			log.info("student is deleted now");
		}
		else
			msg="student not found with ID: "+id;
		
		return msg;
		
	}
	public Student findstudent(int id)
	{
		log.info("Ging to find student in studentService->>findstudent");
		Optional<Student> studentOp=studentDTO.findById(id);
		if(studentOp.isPresent())
			return studentOp.get();
		else
			
		return null;
		
	}

	public List<Student> findAllstudent() {
		// TODO Auto-generated method stub
		return studentDTO.findAll();
	}
	
	public void linkBatch(int studentId,int batchID) {
		Student student=studentDTO.getById(studentId);
		Batch batch=batchClient.findBatchByID(batchID);
		System.out.println(batch);
		student.setBatch(batch);
		studentDTO.save(student);
		//student.setFacultyID(facultyID);
			
	}
	public void markAttendence(int studentId,Attendence attendence) {
		Student student=studentDTO.getById(studentId);
		
		student.setAttendence(attendence);
		studentDTO.save(student);
		//student.setFacultyID(facultyID);
			
	}
	
	public List<Student> findStudents_per_Batch(int batchID) {
		List<Student> studList=null;
		
		for(Student st: studentDTO.findAll())
		{
			if(st.getBatch().getBatchID()==batchID)
			{
				studList.add(st);
			}
		}
		return studList;
	}
	
}

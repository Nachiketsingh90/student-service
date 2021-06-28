package com.nachiket.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nachiket.model.Student;
import com.nachiket.service.StudentService;
import com.nachiket.vo.Attendence;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/student")
@Log
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/registerStudent")
	public ResponseEntity registerStudent(@RequestBody Student student) {
		log.info("StudentController-->registerStudent");
		if(student!=null)
		{
			studentService.createstudent(student);
			return ResponseEntity.status(HttpStatus.CREATED).

					body("Student is created with id:" + student.getStudentID());
		}	
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).

					body("Invalid Data");
		}
		

		
	}

	@GetMapping("/findStudentByID/{studentID}")
	public Student findStudentByID(@PathVariable int studentID) {
		log.info("StudentController-->findStudentByID");
		Student obj = studentService.findstudent(studentID);
		if (obj != null) {
			log.info("found Student with id " + studentID);
			return obj;
		} else {
			String msg = "No Student with given id found";
			log.info("Not found Student with id " + studentID);
			return null;
		}

	}

	@PutMapping("/updateStudent")
	public ResponseEntity updateStudent(@RequestBody Student student) {
		log.info("StudentController-->updateStudent");
		studentService.updatestudent(student);
		// return new ResponseEntity(Student, new HttpHeaders().set, HttpStatus.CREATED);

		return ResponseEntity.status(HttpStatus.OK).

				body("Student is updated with id:" + student.getStudentID());
	}

	@DeleteMapping("/deleteStudentByID/{studentID}")
	public ResponseEntity deleteStudentByID(@PathVariable int studentID) {
		log.info("StudentController-->deleteStudentByID");
		String msg = studentService.deletestudent(studentID);

		return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/findAllStudent")
	public List<Student> findAllStudent() {
		// log.info("StudentController-->findAllStudent");
		System.out.println("findall called");
		List<Student> studentList = studentService.findAllstudent();
		if (studentList.size() == 0) {

			return null;
		} else {

			return studentList;
		}

	}
	@GetMapping("/findAllStudent")
	public List<Student> findStudents_batchwise(int batchID) {
		 log.info("StudentController-->findAllStudent");
		
		List<Student> studentList = studentService.findStudents_per_Batch(batchID);
		if (studentList.size() == 0) {

			return null;
		} else {

			return studentList;
		}

	}
	@PostMapping("/linkStudent_withBacth/studentId/{studentId}/batchID/{batchID}")
	public ResponseEntity<String> linkStudent_withBacth(@PathVariable int studentId,@PathVariable int batchID) {
		System.out.println(" Studentid"+studentId+" BatchID "+batchID);
		 log.info("StudentController-->linkStudent_withFaculty");
		
		 studentService.linkBatch(studentId, batchID);
	

			return new ResponseEntity("Student is assigned to Batch", new HttpHeaders(), HttpStatus.FOUND);
		

	}
	@PostMapping("/markAttendence/studentId/{studentId}")
	public void markAttendence(@PathVariable int studentId,@RequestBody Attendence attendence) {
		
		studentService.markAttendence(studentId, attendence);
	}
	
	@PostMapping("/markAttendence_for_FullBacth")
	public void markAttendence(@RequestBody List<Student> studList,List<Attendence> attendenceList) {
		
		//studentService.markAttendence(studentId, attendence);
	}

}

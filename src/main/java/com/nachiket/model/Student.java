package com.nachiket.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.nachiket.vo.Attendence;
import com.nachiket.vo.Batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int studentID;
	private String studentName;
	private String contactNumber;
	private String email_id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "batch_id")
	private Batch  batch;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "attendence")	
	private Attendence attendence;
}

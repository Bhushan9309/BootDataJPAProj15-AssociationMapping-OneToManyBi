package com.nt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.College;
import com.nt.entity.Student;
import com.nt.repository.ICollegeRepository;
import com.nt.repository.IStudentRepository;
@Service
public class CollegeMgmtServiceImpl implements ICollegeMgmtService {
	@Autowired
	private ICollegeRepository clgRepo;
	@Autowired
	private IStudentRepository studRepo;

	@Override
	public String saveCollegeAndItsStudents(College clg) {
	    int idVal=clgRepo.save(clg).getCid();
		return "College and Its Students are saved with the id value : ==> "+idVal;
	}

	@Override
	public String saveStudentsAndTheirCollege(List<Student> list) {
		// use repository
		List<Student> savedList=studRepo.saveAll(list);
		//collect only id values
		List<Integer> id=savedList.stream().map(Student::getSid).collect(Collectors.toList());
		return "Student objects and their college are saved with id values : : "+id;
	}

	@Override
	public List<College> showCollegeAndItsStudents() {
		List<College> list=clgRepo.findAll();
		return list;
	}

	@Override
	public List<Student> showStudentsAndThierCollege() {
		List<Student> list=studRepo.findAll();
		return list;
	}

}

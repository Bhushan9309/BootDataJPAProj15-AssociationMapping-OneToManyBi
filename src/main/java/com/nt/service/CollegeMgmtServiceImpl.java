package com.nt.service;

import java.util.List;
import java.util.Set;
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

	@Override
	public String deleteCollegeAndItsStudentsById(int cno) {
		//Load Parent Object
		College college=clgRepo.findById(cno).orElseThrow(()->new IllegalArgumentException("Invalid College ID"));
		clgRepo.delete(college);
		return  cno+" College And Its Students are deleted";
	}

	@Override
	public String deleteAllStudentsOfACollegeById(int cno) {
		//Load Parent Object
	   College college=clgRepo.findById(cno).orElseThrow(()->new IllegalArgumentException("Invalid College ID"));
	   //get associated child objects
	   Set<Student> childs=college.getStudentsInfo();
	   //remove College from Students
	   childs.forEach(st->{
		   st.setCollegeInfo(null);
	   });
	   //delete all childs
	   studRepo.deleteAllInBatch(childs);
	   return cno+" College Students are deleted";
	}

	@Override
	public String deleteOnlyOneStudentOfACollege(int sno) {
		//Load the Student
		   Student stud=studRepo.findById(sno).orElseThrow(()->new IllegalArgumentException("Invalid Student ID"));
		   //remove link with parent
		   stud.setCollegeInfo(null);
		   //update the child object to empty the FK column
		   studRepo.save(stud);
		   //delete the child object
		   studRepo.delete(stud);
		  return sno+ " Student is deleted";
	}

	@Override
	public String deleteAllStudentsAndTheirCollege(List<Integer> sids) {
		// Load the childs
		List<Student> listStud=studRepo.findAllById(sids);
		if(listStud.size()!=0) {
			//delete the childs
			studRepo.deleteAll(listStud);
		}
		return listStud.size()+ " no.of Students And Their College are deleted";
	}

	@Override
	public String addNewStudentToCollege(int cid, Student st) {
		//Load Parent Object
		   College college=clgRepo.findById(cid).orElseThrow(()->new IllegalArgumentException("Invalid College ID"));
		   //get all childs of a parent
		   Set<Student> childs=college.getStudentsInfo();
		   //set parent to new Student(child)
		   st.setCollegeInfo(college);
		   //add new child to existing childs
		   childs.add(st);
		   //update the parent
		   clgRepo.save(college);
		return cid+" College is added with new Student";
	}

	@Override
	public String transferStudentToNewCollege(int sid, int cid) {
		// Load Student object(child object)
		 Student stud=studRepo.findById(sid).orElseThrow(()->new IllegalArgumentException("Invalid Student ID"));
		 //load College object(parent object)
		 College college=clgRepo.findById(cid).orElseThrow(()->new IllegalArgumentException("Invalid College ID"));
		 //replace existing College Object with new College Object for the student
		 stud.setCollegeInfo(college);
		 //update the  Student Object
		 studRepo.save(stud);
		 return sid+" student is added to "+cid+" College" ;
	}

}

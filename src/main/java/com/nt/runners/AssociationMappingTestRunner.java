package com.nt.runners;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.entity.College;
import com.nt.entity.Student;
import com.nt.service.ICollegeMgmtService;
@Component
public class AssociationMappingTestRunner implements CommandLineRunner {
     @Autowired
     private ICollegeMgmtService clgService;
	
	@Override
	public void run(String... args) throws Exception {
		//saved data parent to child
		/*try {
			//parent object
			College clg=new College("VJTI","Mumbai");
			Student st1=new Student("Sarvesh Kadam","Jalgaon");
			Student st2=new Student("Vilas Patil","Nandurbar");
			//link college with students
			st1.setCollegeInfo(clg); st2.setCollegeInfo(clg);
			//link Students with College
			clg.setStudentsInfo(Set.of(st1, st2));
			//invoke the service method
			String msg=clgService.saveCollegeAndItsStudents(clg);
			System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//saving data child to parent
		/*try {
			//parent object
			College clg=new College("IIT","Mumbai");
			Student st1=new Student("Vijaya Chavan","Nanded");
			Student st2=new Student("Swapnil Chitte","Latur");
			Student st3=new Student("Aakash Raut","Thane");
			//link college with students
			st1.setCollegeInfo(clg); st2.setCollegeInfo(clg); st3.setCollegeInfo(clg);
			//link Students with College
			Set<Student> studs=new HashSet<Student>();
			studs.add(st1);studs.add(st2);studs.add(st3);
			clg.setStudentsInfo(studs);
			//invoke the service method
			String msg=clgService.saveStudentsAndTheirCollege(Arrays.asList(st1, st2, st3));
			System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//parent to child Loading
		/*try {
			List<College> list=clgService.showCollegeAndItsStudents();
			list.forEach(clg->{
				System.out.println("parent : : ==> "+clg);
				Set<Student> childs=clg.getStudentsInfo();
				childs.forEach(st->{
						System.out.println("Child  ===> "+st);
					});
				System.out.println("===========================");
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		try {
			List<Student> list=clgService.showStudentsAndThierCollege();
			list.forEach(st->{
				System.out.println("Child : : ==> "+st);
				College clg=st.getCollegeInfo();
				System.out.println("Child  ===> "+clg);
			    System.out.println("===========================");
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}

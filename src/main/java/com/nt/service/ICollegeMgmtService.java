package com.nt.service;

import java.util.List;

import com.nt.entity.College;
import com.nt.entity.Student;

public interface ICollegeMgmtService {
     public String saveCollegeAndItsStudents(College clg);
     public String saveStudentsAndTheirCollege(List<Student> list);
     
     public List<College> showCollegeAndItsStudents();
     public List<Student> showStudentsAndThierCollege();
}

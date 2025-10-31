package com.nt.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "JPA_OTM_STUDENT")
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Student {
     @Id
     @SequenceGenerator(name = "gen1",sequenceName = "SID_SEQ",initialValue = 1,allocationSize = 1)
     @GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
     private Integer sid;
     
     @Column(length = 30)
     @NonNull
     private String sname;
     
     @Column(length = 30)
     @NonNull
     private String sadd;
     
     @ManyToOne(targetEntity = College.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     @JoinColumn(name = "COLLEGE_ID",referencedColumnName = "CID")
     private College collegeInfo;
     
     public Student() {
    	 System.out.println("Student ===> 0-param constructor  : : "+this.getClass());
     }
     //toString (alter+shift+s,s)
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", sadd=" + sadd + "]";
	}

     
}

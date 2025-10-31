//College.java
package com.nt.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="JPA_OTM_COLLEGE")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class College {
	@Id
	@SequenceGenerator(name = "gen1",sequenceName = "CID_SEQ",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
    private Integer cid;
    
    @Column(length = 30)
    @NonNull
    private String cname;
    
    @Column(length =30)
    @NonNull
    private String caddrs;
    
    @OneToMany(targetEntity = Student.class,cascade = CascadeType.ALL,
    		               mappedBy = "collegeInfo")
    private  Set<Student> studentsInfo; //for OneToMany
    
    //0-param constructor
    public College() {
    	System.out.println("College ==> 0-param constructor : :" +this.getClass());
    }

    //toString() (alter+shift+s,s)
	@Override
	public String toString() {
		return "College [cid=" + cid + ", cname=" + cname + ", caddrs=" + caddrs + "]";
	}
    
   
    
    
    
}

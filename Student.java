package com.capitalgbl.test;


public class Student {
	
	String name;
	Integer id;
	Float gpa;
	
	public Student(){
		
	}
	
	public Student ( String name, Integer id, Float gpa){
		this.name=name;
		this.id = id;
		this.gpa=gpa;
	}
	
	private void populateStudentInfo(String name, Integer id, Float gpa){
		this.name=name;
		this.id = id;
		this.gpa = gpa;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Float getGpa() {
		return gpa;
	}


	public void setGpa(Float gpa) {
		this.gpa = gpa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gpa == null) ? 0 : gpa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (gpa == null) {
			if (other.gpa != null)
				return false;
		} else if (!gpa.equals(other.gpa))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return " [name=" + name + ", gpa=" + gpa + "]";
	}

	
	
}
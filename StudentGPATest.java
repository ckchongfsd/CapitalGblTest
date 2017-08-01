package com.capitalgbl.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class StudentGPATest {
	public static void main(String[] args) {
		
		List<Student> students = StudentGPATest.populateStudent();
		
		System.out.println("the new array list is "+students.size());
		
		students.stream().sorted(Comparator.comparing(Student::getGpa).reversed().thenComparing(Student::getName))
		.forEach(p->System.out.println("in stream" +p));
		
	}
	
	public static List<Student> populateStudent(){
		List<Integer> stdIDs = Arrays.asList(100,101,102,103,104);
		List<String> stdNames = Arrays.asList("Ah Ling", "Chai Chin", "Boon Keong","Ramasumesh", "Ahminah");
		List<Float> stdgpas = Arrays.asList(2.34f,2.34f, 5.67f, 1.1f, 5.9f);
		
		List<Student> students = new ArrayList<Student>();
		for(int i=0; i < stdIDs.size(); i++){
		
			Student s = new Student(stdNames.get(i),stdIDs.get(i),stdgpas.get(i));
			students.add(s);
		}
		
		return students;
	}


}

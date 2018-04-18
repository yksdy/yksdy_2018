package com.yksdy.desigin.patterns.web.dataAccessObject;

import java.util.List;

public interface StudentDao {
	public List<Student> getAllStudents();

	public Student getStudent(int rollNo);

	public void updateStudent(Student student);

	public void deleteStudent(Student student);
}


package com.school.service;

import com.school.entity.Student;
import com.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
  private final StudentRepository repo;

  public StudentService(StudentRepository repo) { this.repo = repo; }

  public Student create(Student s) { return repo.save(s); }

  public List<Student> findAll() { return repo.findAll(); }

  public Student findById(Long id) {
    return repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));
  }

  public Student update(Long id, Student update) {
    Student s = findById(id);
    s.setName(update.getName());
    s.setEmail(update.getEmail());
    s.setDob(update.getDob());
    s.setMarks(update.getMarks());
    return repo.save(s);
  }

  public void delete(Long id) {
    Student s = findById(id);
    repo.delete(s);
  }
}


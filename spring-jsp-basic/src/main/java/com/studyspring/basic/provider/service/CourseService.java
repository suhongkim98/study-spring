package com.studyspring.basic.provider.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyspring.basic.core.CourseServiceInterface;
import com.studyspring.basic.provider.dao.CourseDAO;
import com.studyspring.basic.provider.dto.CourseDTO;

@Service
public class CourseService implements CourseServiceInterface{
	private CourseDAO courseDAO;
	
	@Autowired
	public CourseService(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	@Override
	public Optional<CourseDTO> registerCourse(String title, int memberIdx) {
		CourseDTO course = courseDAO.insert(title, memberIdx);
		return Optional.ofNullable(course);
	}

	@Override
	public Optional<List<CourseDTO>> selectAll() {
		List<CourseDTO> courses = courseDAO.selectAll();
		return Optional.ofNullable(courses);
	}
	

}

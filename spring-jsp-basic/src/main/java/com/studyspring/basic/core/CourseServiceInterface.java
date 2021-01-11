package com.studyspring.basic.core;

import java.util.List;
import java.util.Optional;

import com.studyspring.basic.provider.dto.CourseDTO;

public interface CourseServiceInterface {
	public Optional<CourseDTO> registerCourse(String title, int memberIdx);
	public Optional<List<CourseDTO>> selectAll();
}

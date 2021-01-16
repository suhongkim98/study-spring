package com.studyspring.basic.core;


import java.util.List;

import com.studyspring.basic.provider.dto.CourseDTO;

public interface CourseDAOInterface {
	public void insert(String title, int memberIdx);
	public List<CourseDTO> selectAll();
}

package com.studyspring.basic.provider.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyspring.basic.core.CourseDAOInterface;
import com.studyspring.basic.member.MemberDTO;
import com.studyspring.basic.provider.dto.CourseDTO;

@Repository
public class CourseDAO implements CourseDAOInterface{
	private SqlSessionTemplate session;
	
	@Autowired
	public CourseDAO(SqlSessionTemplate sessionTemplate) {
		this.session = sessionTemplate;
	}
	@Override
	public CourseDTO insert(String title, int memberIdx) {
		CourseDTO course = new CourseDTO();
		MemberDTO member = new MemberDTO();
		course.setTitle(title);
		member.setIdx(memberIdx);
		course.setMember(member);
		session.insert("courseTable.insertCourse", course);
		return course;
	}

	@Override
	public List<CourseDTO> selectAll() {
		List<CourseDTO> courses = session.selectList("courseTable.selectCourseAll");
		return courses;
	}
	
}

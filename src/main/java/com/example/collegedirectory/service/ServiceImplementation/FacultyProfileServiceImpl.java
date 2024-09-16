package com.example.collegedirectory.service.ServiceImplementation;

import com.example.collegedirectory.dto.FacultyProfileDTO;
import com.example.collegedirectory.dto.StudentProfileDTO;
import com.example.collegedirectory.model.Course;
import com.example.collegedirectory.model.Enrollment;
import com.example.collegedirectory.model.FacultyProfile;
import com.example.collegedirectory.model.StudentProfile;
import com.example.collegedirectory.repository.CourseRepository;
import com.example.collegedirectory.repository.EnrollmentRepository;
import com.example.collegedirectory.repository.FacultyProfileRepository;
import com.example.collegedirectory.service.ServiceInterface.FacultyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyProfileServiceImpl implements FacultyProfileService {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public FacultyProfile saveFacultyProfile(FacultyProfile facultyProfile) {
        return facultyProfileRepository.save(facultyProfile);
    }

    @Override
    public void deleteFacultyProfile(Long id) {
        facultyProfileRepository.deleteById(id);
    }

    @Override
    public FacultyProfile findById(Long id) {
        return facultyProfileRepository.findById(id).orElse(null);
    }

    @Override
    public List<FacultyProfile> findAllFacultyProfiles() {
        return facultyProfileRepository.findAll();
    }

    @Override
    public List<FacultyProfile> findByDepartment(Long departmentId) {
        // Assuming you've added this method to your repository
        return facultyProfileRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<Course> getFacultyCourses(Long facultyId) {
        return courseRepository.findByFacultyId(facultyId);
    }

    @Override
    public Course addFacultyCourse(Long facultyId, Course course) {
        FacultyProfile faculty = facultyProfileRepository.findById(facultyId).orElse(null);
        if (faculty != null) {
            course.setFaculty(faculty);
            return courseRepository.save(course);
        }
        return null;
    }

    @Override
    public List<StudentProfileDTO> getClassList(Long facultyId, Long courseId) {
        Course course = courseRepository.findByIdAndFacultyId(courseId, facultyId);
        if (course == null) {
            return List.of();
        }
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
        return enrollments.stream()
                .map(enrollment -> convertToStudentDTO(enrollment.getStudent()))
                .collect(Collectors.toList());
    }

    @Override
    public FacultyProfileDTO getFacultyProfile(Long id) {
        FacultyProfile faculty = facultyProfileRepository.findById(id).orElse(null);
        return (faculty != null) ? convertToDTO(faculty) : null;
    }

    @Override
    public FacultyProfileDTO updateFacultyProfile(Long id, FacultyProfileDTO updatedProfile) {
        FacultyProfile faculty = facultyProfileRepository.findById(id).orElse(null);
        if (faculty == null) {
            return null;
        }
        updateFacultyFromDTO(faculty, updatedProfile);
        faculty = facultyProfileRepository.save(faculty);
        return convertToDTO(faculty);
    }

    @Override
    public boolean updateContactInfo(Long facultyId, String email, String phone, String officeHours) {
        FacultyProfile faculty = facultyProfileRepository.findById(facultyId).orElse(null);
        if (faculty != null) {
            faculty.getUser().setEmail(email);
            faculty.getUser().setPhone(phone);
            faculty.setOfficeHours(officeHours);
            facultyProfileRepository.save(faculty);
            return true;
        }
        return false;
    }

    private FacultyProfileDTO convertToDTO(FacultyProfile faculty) {
        FacultyProfileDTO dto = new FacultyProfileDTO();
        dto.setId(faculty.getId());
        dto.setName(faculty.getUser().getName());
        dto.setEmail(faculty.getUser().getEmail());
        dto.setPhone(faculty.getUser().getPhone());
        dto.setDepartment(faculty.getDepartment().getName());
        dto.setOfficeHours(faculty.getOfficeHours());
        dto.setPhoto(faculty.getPhoto());
        return dto;
    }

    private StudentProfileDTO convertToStudentDTO(StudentProfile student) {
        StudentProfileDTO dto = new StudentProfileDTO();
        dto.setId(student.getId());
        dto.setName(student.getUser().getName());
        dto.setEmail(student.getUser().getEmail());
        dto.setPhoto(student.getPhoto());
        // Set other fields as needed
        return dto;
    }

    private void updateFacultyFromDTO(FacultyProfile faculty, FacultyProfileDTO dto) {
        faculty.getUser().setEmail(dto.getEmail());
        faculty.getUser().setPhone(dto.getPhone());
        faculty.setOfficeHours(dto.getOfficeHours());
        // Update other fields as needed
    }
}
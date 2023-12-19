package io.github.yanburigo.business;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import io.github.yanburigo.service.CourseService;

class CourseBusinessMockWithBDDTest {

	CourseService mockService;
    CourseBusiness business;
    List<String> courses;
    
    @BeforeEach
    void setup() {
    	mockService = mock(CourseService.class);
        business = new CourseBusiness(mockService);
        
        courses = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
            );
    }
    
    @Test
    void testCoursesRelatedToSpring_When_UsingAMock() {
    	
    	//Given    	
    	given(mockService.retrieveCourses("Leandro")).willReturn(courses);
    	
        // When / Act
        var filteredCourses =
            business.retrieveCoursesRelatedToSpring("Leandro");
        
        // Then / Assert
        assertThat(filteredCourses.size(), is(4));
    }
    
	@DisplayName("Delete Courses not Related to Spring Using Mockito Sould call Method")
	@Test
	void testeDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCourse() {
		// Given / Arrange
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		
		// When / Act
		business.deleteCoursesNotRelatedToSpring("Leandro");
		
		// Then / Assert
		verify(mockService).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		verify(mockService, times(1)).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		verify(mockService, atLeast(1)).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		verify(mockService, atLeastOnce()).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		verify(mockService).deleteCourse("Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#");
		verify(mockService, never()).deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
	}
	
	@DisplayName("Delete Courses not Related to Spring Using Mockito Sould call Method V2")
	@Test
	void testeDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCourseV2() {
		// Given / Arrange
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		
		String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
		String architectureCourse = "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#";
		String restSpringCourse = "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker";
		
		// When / Act
		business.deleteCoursesNotRelatedToSpring("Leandro");
		
		// Then / Assert
		then(mockService).should().deleteCourse(agileCourse);
		then(mockService).should().deleteCourse(architectureCourse);
		then(mockService).should(never()).deleteCourse(restSpringCourse);
	}
	
	@DisplayName("Delete Courses not Related to Spring Capturing Arguments Sould call Method")
	@Test
	void testeDeleteCoursesNotRelatedToSpring_CapturingArguments_Should_CallMethod_deleteCourse() {
		// Given / Arrange
		
		courses = Arrays.asList(
                	"Agile Desmistificado com Scrum, XP, Kanban e Trello",
                	"REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker"
				);
		
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		
		String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
		
		// When / Act
		business.deleteCoursesNotRelatedToSpring("Leandro");
		
		// Then / Assert
		then(mockService).should().deleteCourse(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(), is(agileCourse));
	}
	
	@DisplayName("Delete Courses not Related to Spring Capturing Arguments Sould call Method V2")
	@Test
	void testeDeleteCoursesNotRelatedToSpring_CapturingArguments_Should_CallMethod_deleteCourseV2() {
		// Given / Arrange
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		
		// When / Act
		business.deleteCoursesNotRelatedToSpring("Leandro");
		
		// Then / Assert
		then(mockService).should(times(7)).deleteCourse(argumentCaptor.capture());
		assertThat(argumentCaptor.getAllValues().size(), is(7));
	}
}
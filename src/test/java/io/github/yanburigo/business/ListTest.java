package io.github.yanburigo.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ListTest {
	
	@Test
	void testMockingList_when_SizeIsCalled_ShouldReturn10() {
		
		// Given / Arrange
		List<?> list = mock(List.class);
		when(list.size()).thenReturn(10);
		
		// When / Act & Then / Assert
		assertEquals(10, list.size());
	}
	
	@Test
	void testMockingList_when_SizeIsCalled_ShouldReturnMultipleValues() {
		
		// Given / Arrange
		List<?> list = mock(List.class);
		when(list.size()).thenReturn(10).thenReturn(20);
		
		// When / Act & Then / Assert
		assertEquals(10, list.size());
		assertEquals(20, list.size());
		assertEquals(20, list.size());
	}

	@Test
	void testMockingList_when_GetIsCalled_ShouldReturnTest() {
		
		// Given / Arrange
		var list = mock(List.class);
		when(list.get(0)).thenReturn("Test");
		
		// When / Act & Then / Assert
		assertEquals("Test", list.get(0));
		assertNull(list.get(1));
	}
	
	@Test
	void testMockingList_when_GetIsCalledWithArgumentMatcher_ShouldReturnValid() {
		
		// Given / Arrange
		var list = mock(List.class);
		when(list.get(anyInt())).thenReturn("Valid");
		
		// When / Act & Then / Assert
		assertEquals("Valid", list.get(anyInt()));
		assertNotNull(list.get(1));
	}
}

package io.github.yanburigo.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SpyTest {
	
	@Test
	void test() {
		//Given / Arrange
		List<String> mockArrayList = spy(ArrayList.class);
		
		//When / Act & Then / Assert
		assertEquals(0, mockArrayList.size());
		when(mockArrayList.size()).thenReturn(5);
		mockArrayList.add("Foo-Bar");
		assertEquals(5, mockArrayList.size());
	}
	
	@Test
	void testV2() {
		//Given / Arrange
		List<String> mockArrayList = spy(ArrayList.class);
		
		//When / Act & Then / Assert
		assertEquals(0, mockArrayList.size());
		mockArrayList.add("Foo-Bar");
		assertEquals(1, mockArrayList.size());
		mockArrayList.remove("Foo-Bar");
		assertEquals(0, mockArrayList.size());
	}
	
	@Test
	void testV3() {
		List<String> mockArrayList = spy(ArrayList.class);
		
		assertEquals(0, mockArrayList.size());
		when(mockArrayList.size()).thenReturn(5);
		assertEquals(5, mockArrayList.size());
	}
	
	@Test
	void testV4() {
		List<String> mockArrayList = spy(ArrayList.class);
		
		assertEquals(0, mockArrayList.size());
		mockArrayList.add("Foo-Bar");
		verify(mockArrayList).add("Foo-Bar");
		verify(mockArrayList, never()).remove("Foo-Bar");
		verify(mockArrayList, never()).remove(anyString());
		verify(mockArrayList, never()).clear();
	}
}

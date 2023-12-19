package io.github.yanburigo.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class OrderServiceTest {
	
	private final OrderService service = new OrderService();
	private final UUID defaultUuid = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
	private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2023, 7, 4, 15, 50);
	
	@Test
	@DisplayName("Should Include Random OrderId When No OrderId Exists")
	
	//Given
	void testShouldIncludeRandomOrderId_When_NoOrderIdExists() {
		try(MockedStatic<UUID> mockedUuid = mockStatic(UUID.class)){
			mockedUuid.when(UUID::randomUUID).thenReturn(defaultUuid);
			
			//When
			Order result = service.createOrder("MacBook Pro", 2L, null);
			
			//Then
			assertEquals(defaultUuid.toString(), result.getId());
		}
	}

	@Test
	@DisplayName("Should Include Current Time When Create A New Order")
	
	//Given
	void testShouldIncludeCurrentTime_When_CreateANewOrder() {
		try(MockedStatic<LocalDateTime> mockedUuid = mockStatic(LocalDateTime.class)){
			mockedUuid.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);
			
			//When
			Order result = service.createOrder("MacBook Pro", 2L, null);
			
			//Then
			assertEquals(defaultLocalDateTime, result.getCreationDate());
		}
	}
}

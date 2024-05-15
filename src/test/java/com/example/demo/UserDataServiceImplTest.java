package com.example.demo;

import com.example.demo.dto.UserDisplayDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserDataEntity;
import com.example.demo.repositry.UserDataRepositry;
import com.example.demo.serviceimpl.UserDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDataServiceImplTest {

    @Mock
    private UserDataRepositry repositry;

    @InjectMocks
    private UserDataServiceImpl service;

    @Test
public void testSaveUserDto_shouldSaveUserAndReturnDto() {
    // Given
    UserDto userDto = new UserDto("John Doe", "123 Main St", "1234567890", "P123456789");
    UserDataEntity expectedEntity = new UserDataEntity(1, "John Doe", "123 Main St", "1234567890", "P123456789", "ACTIVE", java.time.LocalDateTime.parse("2022-01-01T12:00:00"));

    when(repositry.save(any(UserDataEntity.class))).thenReturn(expectedEntity);

    // When
    UserDisplayDto result = service.saveUserDto(userDto);

    // Then
    assertThat(result.getName()).isEqualTo("John Doe");
    assertThat(result.getAddres()).isEqualTo("123 Main St");
    assertThat(result.getMobileno()).isEqualTo("1234567890");
    assertThat(result.getPolicyno()).isEqualTo("P123456789");
    assertThat(result.getId()).isEqualTo(1L);
}
}
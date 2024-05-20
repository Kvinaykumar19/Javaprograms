package com.example.demo.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.dto.UserDisplayDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserDataEntity;
import com.example.demo.repositry.UserDataRepositry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserDataServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserDataServiceImplDiffblueTest {
    @MockBean
    private UserDataRepositry userDataRepositry;

    @Autowired
    private UserDataServiceImpl userDataServiceImpl;

    /**
     * Method under test: {@link UserDataServiceImpl#saveUserDto(UserDto)}
     */
    @Test
    void testSaveUserDto() {
        // Arrange
        UserDataEntity userDataEntity = new UserDataEntity();
        userDataEntity.setAddres("42 Main St");
        userDataEntity.setCreateDate("2020-03-01");
        userDataEntity.setMobileno("Mobileno");
        userDataEntity.setName("Name");
        userDataEntity.setPolicyno("Policyno");
        userDataEntity.setRecodStatus("Recod Status");
        userDataEntity.setUpdateDate("2020-03-01");
        userDataEntity.setUseid(1);
        when(userDataRepositry.save(Mockito.<UserDataEntity>any())).thenReturn(userDataEntity);

        // Act
        UserDisplayDto actualSaveUserDtoResult = userDataServiceImpl
                .saveUserDto(new UserDto("Name", "42 Main St", "Policyno", "Mobileno"));

        // Assert
        verify(userDataRepositry).save(isA(UserDataEntity.class));
        assertEquals("42 Main St", actualSaveUserDtoResult.getAddres());
        assertEquals("Mobileno", actualSaveUserDtoResult.getMobileno());
        assertEquals("Name", actualSaveUserDtoResult.getName());
        assertEquals("Policyno", actualSaveUserDtoResult.getPolicyno());
        assertEquals(1, actualSaveUserDtoResult.getId());
    }
}

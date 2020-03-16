package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.Factory;
import com.telerik.ridepalplaylistgenerator.exceptions.DuplicateEntityException;
import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.List;

import static com.telerik.ridepalplaylistgenerator.Factory.createUser;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {

    @Mock
    UserRepository repository;

    @Mock
    UserDetailsManager userDetailsManager;

    @InjectMocks
    UserServiceImpl mockService;

    @Test(expected = DuplicateEntityException.class)
    public void createUserShould_Throw_When_UserAlreadyExists(){
        //Arrange
        User user = createUser();
        List<User> list = new ArrayList<>();
        list.add(user);
        Mockito.when(repository.getByUsername(Mockito.anyString()))
                .thenReturn(list);

        //Act, Assert
        mockService.createUser(user);
    }

    @Test(expected = DuplicateEntityException.class)
    public void createUserShould_Throw_When_UserEmailAlreadyExists(){
        //Arrange
        User user = Factory.createUser();
        List<User> list = new ArrayList<>();
        list.add(user);
        List<User> emptyList = new ArrayList<>();

        Mockito.when(repository.getByUsername(Mockito.anyString()))
                .thenReturn(emptyList);
        Mockito.when(repository.getByEmail(Mockito.anyString()))
                .thenReturn(list);

        //Act, Assert
        mockService.createUser(createUser());
    }

//    @Test
//    public void createUserShould_CreateUser_When_UserDoesNotExist(){
//        //Arrange
//        User user = createUser();
//
//        List<User> list = new ArrayList<>();
//        Mockito.when(repository.getByUsername(Mockito.anyString()))
//                .thenReturn(list);
//        Mockito.when(repository.getByEmail(Mockito.anyString()))
//                .thenReturn(list);
//
//        //Act
//        mockService.createUser(user);
//
//        Mockito.verify(userDetailsManager, times(1)).createUser((UserDetails) user);
//    }

    @Test
    public void getUserByUserNameShould_CallRepository(){
        //Arrange
        User user = createUser();

        Mockito.when(repository.getUserByUsername("Pesho"))
                .thenReturn(user);

        //Act
        repository.getUserByUsername("Pesho");

        //Assert
        Mockito.verify(repository, times(1)).getUserByUsername("Pesho");
    }

    @Test
    public void getUserByUserIdShould_ReturnUser(){
        //Arrange
        User user = createUser();
        Mockito.when(repository.getUserByUserId(Mockito.anyInt()))
                .thenReturn(user);

        //Act
        User returnedUser = repository.getUserByUserId(1);

        //Assert
        Assert.assertSame(user, returnedUser);
    }

    @Test
    public void getAllShould_ReturnListOfUsers(){
        //Arrange
        User user = createUser();
        List<User> users = new ArrayList<>();
        users.add(user);
        Mockito.when(repository.findAll())
                .thenReturn(users);

        //Act
        List<User> returnedUsers = repository.findAll();

        //Assert
        Assert.assertSame(users, returnedUsers);
    }

    @Test
    public void deleteByUserIdShould_CallRepository(){
        //Arrange
        User user = Factory.createUser();
        Mockito.when(repository.getUserByUserId(Mockito.anyInt()))
                .thenReturn(user);

        //Act
        mockService.deleteByUserId(1);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(user);
    }

//    @Test
//    public void changeRoleShould_CallRepository(){
//        //Arrange
//        User user = Factory.createUser();
//
//        //Act
//        mockService.changeRole(user);
//
//        //Assert
//        Mockito.verify(repository, Mockito.times(1)).save(user);
//    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserShould_ThrowException_When_EmptyPassword(){
        //Arrange
        User user = Factory.createUser();
        user.setPassword("");
        Mockito.when(repository.getUserByUserId(Mockito.anyInt()))
                .thenReturn(user);

        //Act, Assert
        mockService.updateUser(1, user);
    }

//    @Test
//    public void updateUserShould_CallUserDetailsManager(){
//        //Arrange
//        User user = Factory.createUser();
//        Mockito.when(repository.getUserByUserId(Mockito.anyInt()))
//                .thenReturn(user);
//
//        //Act
//        mockService.updateUser(1, user);
//
//        //Assert
//        Mockito.verify(userDetailsManager, Mockito.times(1)).deleteUser(user.getUsername());
//    }
}
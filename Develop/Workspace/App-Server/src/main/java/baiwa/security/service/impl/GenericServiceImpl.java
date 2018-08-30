package baiwa.security.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baiwa.security.domain.RandomCity;
import baiwa.security.domain.User;
import baiwa.security.repository.RandomCityRepository;
import baiwa.security.repository.UserRepository;
import baiwa.security.service.GenericService;

/**
 * Created by nydiarra on 07/05/17.
 */
@Service
public class GenericServiceImpl implements GenericService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RandomCityRepository randomCityRepository;

    @Override
    public User findByUsername(String username) {

			return userRepository.findByUsername(username);

    }

//    @Override
//    public List<User> findAllUsers() {
//        return (List<User>)userRepository.findAll();
//    }

  @Override
  public List<User> findAllUsers() {
      return null;
  }
    
    @Override
    public List<RandomCity> findAllRandomCities() {
        return (List<RandomCity>)randomCityRepository.findAll();
    }
}

package com.pitechplus.selfstudy.model.dao.specification;

import com.pitechplus.selfstudy.model.dao.repository.UserRepository;
import com.pitechplus.selfstudy.model.dao.specification.criteria.SearchCriteria;
import com.pitechplus.selfstudy.model.dao.specification.utils.Operation;
import com.pitechplus.selfstudy.model.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

import static com.pitechplus.selfstudy.utils.UtilsClass.getUserSpecification;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserSpecificationTest {

    @Autowired
    private UserRepository repository;

    private User userJane;
    private User userJohn;

    @BeforeAll
    public void init() {
        userJane = new User();
        userJane.setFirstName("Jane");
        userJane.setLastName("Doe");
        userJane.setEmail("jane.doe@email.com");
        userJane.setBirthDate(LocalDate.of(2000, 1, 1));
        userJane.setHeightInCm(165);
        repository.save(userJane);

        userJohn = new User();
        userJohn.setFirstName("John");
        userJohn.setLastName("Doe");
        userJohn.setEmail("john.doe@email.com");
        userJohn.setBirthDate(LocalDate.of(1999, 1, 1));
        userJohn.setHeightInCm(180);
        repository.save(userJohn);
    }

    @Test
    public void givenLastNameLike_whenGettingListOfUsers_thanCorrect() {
        //Given
        UserSpecification specification = getUserSpecification("lastName", Operation.LIKE, "Doe");

        //When
        List<User> result = repository.findAll(specification);

        //Then
        assert (result.contains(userJane));
        assert (result.contains(userJohn));
    }

    @Test
    public void givenFirstNameLike_whenGettingListOfUsers_thanCorrect() {
        //Given
        UserSpecification specification = getUserSpecification("firstName", Operation.LIKE, "Jane");

        //When
        List<User> result = repository.findAll(specification);

        //Then
        assert (result.contains(userJane));
        assert (!result.contains(userJohn));
    }

    @Test
    public void givenHeightGT_whenGettingListOfUsers_thanCorrect() {
        //Given
        UserSpecification specification = getUserSpecification("heightInCm", Operation.GT, 165);

        //When
        List<User> result = repository.findAll(specification);

        //Then
        assert (!result.contains(userJane));
        assert (result.contains(userJohn));
    }

    @Test
    public void givenHeightGE_whenGettingListOfUsers_thanCorrect() {
        //Given
        UserSpecification specification = getUserSpecification("heightInCm", Operation.GE, 165);

        //When
        List<User> result = repository.findAll(specification);

        //Then
        assert (result.contains(userJane));
        assert (result.contains(userJohn));
    }

    @Test
    public void givenHeightGEAndFirstNameLike_whenGettingListOfUsers_thanReturnNothing() {
        //Given
        UserSpecification specification1 = getUserSpecification("heightInCm", Operation.GE, 165);
        UserSpecification specification2 = getUserSpecification("firstName", Operation.LIKE, "William");

        //When
        List<User> result = repository.findAll(Specification.where(specification1).and(specification2));

        //Then
        assert (result.isEmpty());
    }

}
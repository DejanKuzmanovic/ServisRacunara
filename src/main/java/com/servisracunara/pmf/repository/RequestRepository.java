package com.servisracunara.pmf.repository;

import com.servisracunara.pmf.model.Request;
import com.servisracunara.pmf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> getAllByUser(User user);

    List<Request> getAllByApproved(Boolean approved);

}

package com.servisracunara.pmf.service;

import com.servisracunara.pmf.dto.RequestDTO;
import com.servisracunara.pmf.model.Request;
import com.servisracunara.pmf.model.User;
import com.servisracunara.pmf.repository.RequestRepository;
import com.servisracunara.pmf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private RequestRepository requestRepository;
    private UserRepository userRepository;

    @Autowired
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Request createRequest(RequestDTO requestDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Request request = new Request();
        request.setDescription(requestDTO.getDescription());
        request.setPhone(requestDTO.getPhone());
        request.setTitle(requestDTO.getTitle());
        request.setApproved(false);
        request.setUser(userRepository.findByUsername(((User) principal).getUsername()));

        return requestRepository.save(request);
    }

    public List<Request> getAllRequests() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByUsername(((User) principal).getUsername());
        return requestRepository.getAllByUser(currentUser);
    }

}

package com.max.avia2spring.service;

import com.max.avia2spring.model.CrewMember;
import com.max.avia2spring.model.User;
import com.max.avia2spring.repository.CrewMemberRepository;
import com.max.avia2spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final UserRepository userRepository;
    private final CrewMemberRepository crewRepository;

    @Transactional
    public void verifyAndPromote(Long userId, boolean isAdmin, boolean isVerify) {
        if (isVerify) {
            User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
            CrewMember newCrew = new CrewMember();
            newCrew.setName(user.getName());
            newCrew.setPassword(user.getPassword());
            newCrew.setAdmin(isAdmin);
            crewRepository.save(newCrew);
        }
        userRepository.deleteById(userId);
    }
}
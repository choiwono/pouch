package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Role;
import my.examples.pouch.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    @Transactional
    public void addRole(Role role){
        Role result = roleRepository.save(role);
        System.out.println(result.getId() + ", " + result.getName());
    }
}

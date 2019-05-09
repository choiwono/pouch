package my.examples.pouch.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Role;
import my.examples.pouch.repository.RoleRepository;
import my.examples.pouch.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    @Transactional
    public void addRole(Role role){
        Role result = roleRepository.save(role);
    }
}

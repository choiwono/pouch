package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Role;
import my.examples.pouch.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface RoleService {
    public void addRole(Role role);
}

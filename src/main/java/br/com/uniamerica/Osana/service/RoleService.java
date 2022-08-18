package br.com.uniamerica.Osana.service;

import br.com.uniamerica.Osana.model.Role;
import br.com.uniamerica.Osana.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    public Role save(Role role){
       return roleRepository.save(role);
   }
}

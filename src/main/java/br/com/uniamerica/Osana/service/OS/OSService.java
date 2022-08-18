package br.com.uniamerica.Osana.service.OS;

import br.com.uniamerica.Osana.dto.OSDTO;
import br.com.uniamerica.Osana.dto.input.NewOSDTO;
import br.com.uniamerica.Osana.model.OS;
import br.com.uniamerica.Osana.model.User;
import br.com.uniamerica.Osana.repository.OSRepository;
import br.com.uniamerica.Osana.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OSService {
    private final OSRepository osRepository;
    private final UserRepository userRepository;

    public List<OSDTO> findAll() {
        return osRepository.findAll().stream().map(OSDTO::new).collect(Collectors.toList());
    }

    public OSDTO findById(long id) {
        OS os = osRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found OS by ID:" + id));
        return new OSDTO(os);
    }

    public OSDTO save(NewOSDTO newOSDTO) {
        osRepository.findById(newOSDTO.getUserId()).orElseThrow(() -> new IllegalStateException("Not Found User by ID:" + newOSDTO.getUserId()));
        return new OSDTO(osRepository.save(newOSDTO.toModel()));
    }

    public OSDTO update(long idOS, NewOSDTO newOSDTO) {
        OS os = osRepository.findById(idOS).orElseThrow(() -> new IllegalStateException("Not Found OS by ID:" + idOS));

        os.setMotive(newOSDTO.getMotive());
        os.setObs(newOSDTO.getObs());
        os.setDevolution(newOSDTO.getDevolution());
        os.setDateOS(newOSDTO.getDateOS());
        User user = userRepository.findById(newOSDTO.getUserId()).orElseThrow(() -> new IllegalStateException("Not Found User by ID:" + newOSDTO.getUserId()));
        os.setUser(user);

        OS updated = osRepository.save(os);
        return new OSDTO(updated);
    }

    public String deleteById(long id) {
        OS os = osRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found OS by ID:" + id));
        osRepository.deleteById(id);
        return "OS was successfully deleted";
    }
}//

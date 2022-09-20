package br.com.uniamerica.Osana.DTO.OSDTOS;

import br.com.uniamerica.Osana.Model.*;
import br.com.uniamerica.Osana.Repository.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewOSDTO implements Serializable {
    @NotBlank(message = "Motive is Required")
    private String motive;
    private String obs;
    @NotBlank(message = "devolution is Required")
    private Date devolution;
    @NotBlank(message = "dateOS is Required")
    private Date dateOS;
    @NotBlank(message = "Priority is Required")
    private Long idPriority;
    @NotBlank(message = "Status is Required")
    private Long idStatus;
    @NotBlank(message = "Type Service is Required")
    private Long idTypeServices;
    @NotBlank(message = "Equipment is Required")
    private Long idEquipment;
    @NotBlank(message = "Client is Required")
    private Long idClient;
    @NotBlank(message = "User is Required")
    private Long idUsuario;
    //
    public OS toModel(UsuarioRepository usuarioRepository, StatusRepository statusRepository, EquipmentRepository equipmentRepository,TypeServicesRepository typeServicesRepository,PriorityRepository priorityRepository, ClientRepository clientRepository){
        OS os = new OS();
        Optional<Usuario> usuario = usuarioRepository.findById(this.idUsuario);
        if (usuario.isEmpty()){
            return null;
        }
        Optional<Status> status = statusRepository.findById(this.idStatus);
        if (status.isEmpty()){
            return null;
        }
        Optional<Equipment> equipment = equipmentRepository.findById(this.idEquipment);
        if(equipment.isEmpty()){
            return null;
        }
        Optional<TypeServices> typeServices = typeServicesRepository.findById(this.idTypeServices);
        if(typeServices.isEmpty()){
            return null;
        }
        Optional<Priority> priority = priorityRepository.findById(this.idPriority);
        if(priority.isEmpty()){
            return null;
        }
        Optional<Client> client = clientRepository.findById(this.idClient);
        if(client.isEmpty()){
            return null;
        }

        os.setUsuario(usuario.get());
        os.setStatus(status.get());
//        os.setEquipment(equipment.get());
        os.setTypeServices(typeServices.get());
        os.setPriority(priority.get());




        os.setMotive(getMotive());
        os.setObs(getObs());
        os.setDevolution(getDevolution());
        os.setDateOS(getDateOS());
        return os;
    }
    public OS updatedOS(OS updateOS, OSRepository osRepository){
        updateOS.setMotive(this.motive);
        updateOS.setObs(this.obs);
        updateOS.setDevolution(this.devolution);
        updateOS.setDateOS(this.dateOS);
//        updateOS.setStatus(this.status);
//        updateOS.setPriority(this.priority);
//        updateOS.setTypeServices(this.typeServices);
//        updateOS.setClient(this.client);
        return updateOS;
    }
}

package br.com.uniamerica.Osana.Services;

import br.com.uniamerica.Osana.Config.RegraNegocioException;
import br.com.uniamerica.Osana.DTO.InventoryDTOS.InventoryToOSDTO;
import br.com.uniamerica.Osana.DTO.OSDTOS.NewOSDTO;
import br.com.uniamerica.Osana.Model.*;
import br.com.uniamerica.Osana.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OSservice {
    @Autowired
    private OSRepository osRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private PriorityRepository priorityRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private TypeServicesRepository typeServicesRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OutputInventoryRepository outputInventoryRepository;

    // FUNCTIONS
    public void deleteAllOuts(OS myOs){
        for (OutputInventory item: myOs.getOutputInventories()){
            Optional<OutputInventory> outGet = outputInventoryRepository.findById(item.getId());
            if (outGet.isPresent()){
                OutputInventory outGetter = outGet.get();
                outGetter.getInventory().incrementarQuantity(item.getQuantity());
                outputInventoryRepository.delete(outGetter);
            }
        }
    }

    public void deleteOut(OutputInventory item){
        item.getInventory().incrementarQuantity(item.getQuantity());
        outputInventoryRepository.delete(item);
    }

    public OutputInventory updateOut(OutputInventory item, HashSet<InventoryToOSDTO> listInventoryRequest){
        for (InventoryToOSDTO inventory: listInventoryRequest){
            if (inventory.getId() == item.getInventory().getId()){
                if (inventory.getQuantity() == item.getQuantity()){
                    return item;
                }else if (inventory.getQuantity() > item.getQuantity()){
                    Integer valor = inventory.getQuantity() - item.getQuantity();
                    item.getInventory().diminuirQuantity(valor);
                    item.incrementarQuantity(valor);
                    return item;
                }else{
                    Integer valor = item.getQuantity() - inventory.getQuantity();
                    item.getInventory().incrementarQuantity(valor);
                    item.diminuirQuantity(valor);
                    return item;
                }
            }
        }
        return null;
    }

    public Set<OutputInventory> verificarOuts(HashSet<InventoryToOSDTO> listInventoryRequest, OS myOs){
        Set<OutputInventory> list_retorno = new HashSet<OutputInventory>();
        Set<Long> listIDInventoryActual = new HashSet<Long>();
        Set<Long> listIDInventoryResquest = new HashSet<Long>();

        for (OutputInventory item: myOs.getOutputInventories()){
            listIDInventoryActual.add(item.getId().getInventoryId());
        }

        for (InventoryToOSDTO item: listInventoryRequest){
            listIDInventoryResquest.add(item.getId());
        }

        for (OutputInventory item: myOs.getOutputInventories()) {
            if (!listIDInventoryResquest.contains(item.getId().getInventoryId())) {
                deleteOut(item);
            }else{
                list_retorno.add(updateOut(item, listInventoryRequest));
            }
        }

        for (InventoryToOSDTO item : listInventoryRequest) {
            if (!listIDInventoryActual.contains(item.getId())) {
                Optional<Inventory> inventory = inventoryRepository.findById(item.getId().longValue());
                if (inventory.isPresent()){
                    Inventory inventory_getter = inventory.get();
                    if (inventory_getter.getQuantity() >= item.getQuantity()){
                        inventory_getter.diminuirQuantity(item.getQuantity());
                        OutputInventory out = new OutputInventory();
                        out.setOs(myOs);
                        out.setInventory(inventory_getter);
                        out.setQuantity(item.getQuantity());
                        list_retorno.add(out);
                    }else{
                        throw new RegraNegocioException("Sem produtos Suficientes no Estoque");
                    }
                }else{
                    throw new RegraNegocioException("Inventory não existe");
                }
            }
        }

        return list_retorno;
    }
    public Set<OutputInventory> UpdatelistInventory(HashSet<InventoryToOSDTO> listInventoryRequest, OS myOs){
        Set<OutputInventory> list = new HashSet<OutputInventory>();

        if (listInventoryRequest.size() == 0 && myOs.getOutputInventories().size() > 0){
            deleteAllOuts(myOs);
            return list;
        } else if (myOs.getOutputInventories().size() == 0 && listInventoryRequest.size() > 0){
            for(InventoryToOSDTO item : listInventoryRequest){
                Optional<Inventory> inventory = inventoryRepository.findById(item.getId().longValue());
                if (inventory.isPresent()){
                    Inventory inventory_getter = inventory.get();
                    if (inventory_getter.getQuantity() >= item.getQuantity()){
                        inventory_getter.diminuirQuantity(item.getQuantity());
                        OutputInventory out = new OutputInventory();
                        out.setOs(myOs);
                        out.setInventory(inventory_getter);
                        out.setQuantity(item.getQuantity());
                        list.add(out);
                    }else{
                        throw new RegraNegocioException("Sem produtos Suficientes no Estoque");
                    }
                }else{
                    throw new RegraNegocioException("Inventory não existe");
                }
            }
            return list;

        }else{
            return verificarOuts(listInventoryRequest, myOs);
        }
    }

    public Set<Equipment> CreatelistEquipment(Set<Integer> listEquipament){
        Set<Equipment> list = new HashSet<Equipment>();
        for(Integer item : listEquipament){
            Optional<Equipment> equipament = equipmentRepository.findById(item.longValue());
            if (equipament.isPresent()){
                list.add(equipament.get());
            }
        }
        return list;
    }

    public OS createOS(NewOSDTO newOSDTO){
        Optional<OS> existsOS = osRepository.findByMotive(newOSDTO.getMotive());
        if (existsOS.isPresent()){
            throw new RegraNegocioException("OS already exists");
        }
        Optional<Usuario> usuario = usuarioRepository.findById(newOSDTO.getIdUsuario().longValue());
        if (usuario.isEmpty()){
            throw new RegraNegocioException("Deu erro maluko");
        }
        Optional<Status> status = statusRepository.findById(1L);
        if (status.isEmpty()){
            throw new RegraNegocioException("Deu erro maluko");
        }
        Optional<TypeServices> typeServices = typeServicesRepository.findById(newOSDTO.getIdTypeServices().longValue());
        if(typeServices.isEmpty()){
            throw new RegraNegocioException("Deu erro maluko");
        }
        Optional<Priority> priority = priorityRepository.findById(newOSDTO.getIdPriority().longValue());
        if(priority.isEmpty()){
            throw new RegraNegocioException("Deu erro maluko");
        }
        Optional<Client> client = clientRepository.findById(newOSDTO.getIdClient().longValue());
        if(client.isEmpty()){
            throw new RegraNegocioException("Deu erro maluko");
        }

        Set<Equipment> lista_obj_equipament = CreatelistEquipment(newOSDTO.getEquipaments());
        OS newOS = newOSDTO.toModel(lista_obj_equipament);
        newOS.setUsuario(usuario.get());
        newOS.setStatus(status.get());
        newOS.setTypeServices(typeServices.get());
        newOS.setPriority(priority.get());
        newOS.setClient(client.get());

        newOS.getOutputInventories().addAll((newOSDTO.getInventories()
                .stream()
                .map(inventory -> {
                    Optional<Inventory> inventory_get = inventoryRepository.findById(inventory.getId().longValue());
                    if (inventory_get.isPresent()){
                        Inventory inventory_getter = inventory_get.get();
                        if (inventory_getter.getQuantity() >= inventory.getQuantity()){
                            inventory_getter.diminuirQuantity(inventory.getQuantity());
                            OutputInventory newOut = new OutputInventory();
                            newOut.setOs(newOS);
                            newOut.setInventory(inventory_getter);
                            newOut.setQuantity(inventory.getQuantity());
                            return newOut;
                        }else{
                            throw new RegraNegocioException("Sem produtos Suficientes no Estoque");
                        }
                    }else{
                        throw new RegraNegocioException("Inventory não existe");
                    }
                }).collect(Collectors.toList())
        ));

        osRepository.save(newOS);
        return newOS;
    }

    public OS updateOS(Long id, NewOSDTO newOSDTO){
        Optional<OS> exists_OS = osRepository.findById(id);
        if(exists_OS.isEmpty()){
            throw new RegraNegocioException("OS não existe");
        }
        Optional<Usuario> usuario = usuarioRepository.findById(newOSDTO.getIdUsuario().longValue());
        if (usuario.isEmpty()){
            throw new RegraNegocioException("Usuario não existe");
        }
        Optional<Status> status = statusRepository.findById(newOSDTO.getIdStatus().longValue());
        if (status.isEmpty()){
            throw new RegraNegocioException("Status não existe");
        }
        Optional<TypeServices> typeServices = typeServicesRepository.findById(newOSDTO.getIdTypeServices().longValue());
        if(typeServices.isEmpty()){
            throw new RegraNegocioException("Tipo de Serviço não existe");
        }
        Optional<Priority> priority = priorityRepository.findById(newOSDTO.getIdPriority().longValue());
        if(priority.isEmpty()){
            throw new RegraNegocioException("Prioridade Não Existe");
        }
        Optional<Client> client = clientRepository.findById(newOSDTO.getIdClient().longValue());
        if(client.isEmpty()){
            throw new RegraNegocioException("Cliente não existe");
        }

        OS existsOS = exists_OS.get();

        Set<Equipment> lista_obj_equipament = CreatelistEquipment(newOSDTO.getEquipaments());

        Set<OutputInventory> lista_obj_inventories = UpdatelistInventory(newOSDTO.getInventories(), existsOS);

        existsOS.setEquipment(lista_obj_equipament);
        existsOS.setOutputInventories(lista_obj_inventories);
        existsOS.setUsuario(usuario.get());
        existsOS.setStatus(status.get());
        existsOS.setTypeServices(typeServices.get());
        existsOS.setPriority(priority.get());
        existsOS.setClient(client.get());
        existsOS.setMotive(newOSDTO.getMotive());
        existsOS.setObs(newOSDTO.getObs());
        existsOS.setDevolution(newOSDTO.getDevolution());

        return existsOS;
    }
}

package br.com.uniamerica.Osana.DTO.OSDTOS;

import br.com.uniamerica.Osana.Model.OS;
import br.com.uniamerica.Osana.Model.TypeServices;
import br.com.uniamerica.Osana.Model.Usuario;
import br.com.uniamerica.Osana.Repository.OSRepository;
import br.com.uniamerica.Osana.Repository.TypeServicesRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewOSDTO {
    @NotBlank(message = "Motive is Required")
    private String motive;
    @NotBlank(message = "OBS is Required")
    private String obs;
    private Date devolution;
    private Date dateOS;
    private Long idUsuario;
    //
    public OS toModel(){
        OS os = new OS();
        os.setMotive(getMotive());
        os.setObs(getObs());
        os.setDevolution(getDevolution());
        os.setDateOS(getDateOS());
        os.setUsuario(new Usuario(idUsuario));

        return os;
    }
    public OS updatedOS(OS updateOS, OSRepository osRepository){
        updateOS.setMotive(this.motive);
        updateOS.setObs(this.obs);
        updateOS.setDevolution(this.devolution);
        updateOS.setDateOS(this.dateOS);
        //updateOS.setUsuario(new Usuario(idUsuario) );
        return updateOS;
    }
}

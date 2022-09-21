package br.com.uniamerica.Osana.DTO.OSDTOS;

import br.com.uniamerica.Osana.Model.*;
import br.com.uniamerica.Osana.Repository.OSRepository;
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
    private String obs;
    @NotBlank(message = "devolution is Required")
    private Date devolution;
    @NotBlank(message = "dateOS is Required")
    private Date dateOS;
    @NotBlank(message = "Priority is Required")
    private Priority priority;
    @NotBlank(message = "Status is Required")
    private Status status;
    @NotBlank(message = "Type Service is Required")
    private TypeServices typeServices;
    @NotBlank(message = "Client is Required")
    private Client client;
    //
    public OS toModel(){
        OS os = new OS();
        os.setMotive(getMotive());
        os.setObs(getClass());
        os.setDevolution(getDevolution());
        os.setDateOS(getDateOS());
        os.setStatus(getStatus());
        os.setPriority(getPriority());
        os.setTypeServices(getTypeServices());
        os.setClient(getClass());

        return os;
    }
    
    private Object getTypeServices() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getPriority() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getDateOS() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getDevolution() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public OS updatedOS(OS updateOS, OSRepository osRepository){
        updateOS.setMotive(this.motive);
        updateOS.setObs(this.obs);
        updateOS.setDevolution(this.devolution);
        updateOS.setDateOS(this.dateOS);
        updateOS.setStatus(this.status);
        updateOS.setPriority(this.priority);
        updateOS.setTypeServices(this.typeServices);
        updateOS.setClient(this.client);
        return updateOS;
    }
	public String getMotive() {
		// TODO Auto-generated method stub
		return null;
	}
}

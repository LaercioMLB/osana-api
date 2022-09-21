package br.com.uniamerica.Osana.DTO.OSDTOS;

import br.com.uniamerica.Osana.Model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class OSDTO implements Serializable {
	private static final long serialVersionUID = 1603970066821872372L;
	
	private Long idOS;
    private String motive;
    private String obs;
    private Date devolution;
    private Date dateOS;
    private Usuario usuario;
    private Status status;
    private Priority priority;
    private TypeServices typeServices;
    private Client client;


    public OSDTO(OS os){
        idOS = os.getIdOS();
        motive = os.getMotive();
        obs = os.getObs();
        devolution = os.getDevolution();
        dateOS = os.getDateOS();
        usuario = os.getUsuario();
        status = os.getStatus();
        priority = os.getPriority();
        typeServices = os.getTypeServices();
        client = os.getClient();
    }
}

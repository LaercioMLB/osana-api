package br.com.uniamerica.Osana.dto;

import br.com.uniamerica.Osana.model.OS;
import br.com.uniamerica.Osana.model.User;
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
public class OSDTO implements Serializable {
    private Long idOS;
    private String motive;
    private String obs;
    private Date devolution;
    private Date dateOS;
    private User user;

    public OSDTO(OS os){
        idOS = os.getIdOS();
        motive = os.getMotive();
        obs = os.getObs();
        devolution = os.getDevolution();
        dateOS = os.getDateOS();
        user = os.getUser();
    }
}

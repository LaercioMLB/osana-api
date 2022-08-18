package br.com.uniamerica.Osana.dto.input;

import br.com.uniamerica.Osana.model.OS;
import br.com.uniamerica.Osana.model.User;
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
    @NotBlank(message = "Devolution date is Required")
    private Date devolution;
    @NotBlank(message = "Date is Required")
    private Date dateOS;
    @NotBlank(message = "User is Required")
    private Long userId;
//
    public OS toModel(){
        OS os = new OS();
        os.setMotive(getMotive());
        os.setObs(getObs());
        os.setDevolution(getDevolution());
        os.setDateOS(getDateOS());
        os.setUser(new User(userId));

        return os;
    }
}

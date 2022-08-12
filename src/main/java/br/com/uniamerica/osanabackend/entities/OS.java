package br.com.uniamerica.osanabackend.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class OS {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "motive")
    private String motive;
    @Column(name = "obs")
    private String obs;
    @Column(name = "devolution")
    private Date devolution;
    @Column(name = "dateOS")
    private Date dateOS;

    public OS(){

    }

    public OS( String motive, String obs, Date devolution, Date dateOS) {
        this.motive = motive;
        this.obs = obs;
        this.devolution = devolution;
        this.dateOS = dateOS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getDevolution() {
        return devolution;
    }

    public void setDevolution(Date devolution) {
        this.devolution = devolution;
    }

    public Date getDateOS() {
        return dateOS;
    }

    public void setDateOS(Date dateOS) {
        this.dateOS = dateOS;
    }

    @Override
    public String toString() {
        return "OS{" +
                "id=" + id +
                ", name='" + motive + '\'' +
                ", obs='" + obs + '\'' +
                ", devolution=" + devolution +
                ", dateOS=" + dateOS +
                '}';
    }
}

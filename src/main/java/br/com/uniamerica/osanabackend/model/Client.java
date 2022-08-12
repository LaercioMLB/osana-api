package br.com.uniamerica.osanabackend.model;
import javax.persistence.*;

@Entity
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "contract")
    private Boolean contract;
    @Column(name = "cnpj")
    private Integer cnpj;
    public Client(){

    }

    public Client(String name, Boolean contract, Integer cnpj) {
        this.name = name;
        this.contract = contract;
        this.cnpj = cnpj;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getContract() {
        return contract;
    }

    public void setContract(Boolean contract) {
        this.contract = contract;
    }

    public Integer getCnpj() {
        return cnpj;
    }

    public void setCnpj(Integer cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contract=" + contract +
                ", cnpj=" + cnpj +
                '}';
    }
}

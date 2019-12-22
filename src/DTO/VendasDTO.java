package DTO;

import java.util.Date;

/**
 * @author MDiasT
 */
public class VendasDTO {
    private Long idvenda;
    private Long idcliente;
    private Date data;
    private Double valortotal;

    /**
     * @return the idvenda
     */
    public Long getIdvenda() {
        return idvenda;
    }

    /**
     * @param idvenda the idvenda to set
     */
    public void setIdvenda(Long idvenda) {
        this.idvenda = idvenda;
    }

    /**
     * @return the idcliente
     */
    public Long getIdcliente() {
        return idcliente;
    }

    /**
     * @param idcliente the idcliente to set
     */
    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the valortotal
     */
    public Double getValortotal() {
        return valortotal;
    }

    /**
     * @param valortotal the valortotal to set
     */
    public void setValortotal(Double valortotal) {
        this.valortotal = valortotal;
    }

    
}

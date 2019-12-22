package DTO;

import java.util.Date;

/**
 * @author MDT
 */
public class EstoqueDTO {
    
    private Long idestoque;
    private Long idproduto;
    private Long idcliente;
    private Long quantidade;
    private Long notanumero;
    private Date data;

    /**
     * @return the idestoque
     */
    public Long getIdestoque() {
        return idestoque;
    }

    /**
     * @param idestoque the idestoque to set
     */
    public void setIdestoque(Long idestoque) {
        this.idestoque = idestoque;
    }

    /**
     * @return the idproduto
     */
    public Long getIdproduto() {
        return idproduto;
    }

    /**
     * @param idproduto the idproduto to set
     */
    public void setIdproduto(Long idproduto) {
        this.idproduto = idproduto;
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
     * @return the quantidade
     */
    public Long getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the notanumero
     */
    public Long getNotanumero() {
        return notanumero;
    }

    /**
     * @param notanumero the notanumero to set
     */
    public void setNotanumero(Long notanumero) {
        this.notanumero = notanumero;
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

}

package DTO;


/**
 * @author MDiasT
 */
public class VendaItensDTO {
    
    private Long idvendaitens;
    private Long idproduto;
    private Integer quantidade;
    private Double valor;
    private Long idvenda;

    /**
     * @return the idvendaitens
     */
    public Long getIdvendaitens() {
        return idvendaitens;
    }

    /**
     * @param idvendaitens the idvendaitens to set
     */
    public void setIdvendaitens(Long idvendaitens) {
        this.idvendaitens = idvendaitens;
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
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

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

   
}

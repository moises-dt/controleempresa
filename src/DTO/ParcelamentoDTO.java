package DTO;

/**
 * @author MDiasT
 */
public class ParcelamentoDTO {
    
    private Long id;
    private String descricao_parcelamento;
    private Double taxajuros;
    private Integer quantidadeparcelas;
    private Boolean entrada;

    
   
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the descricao_parcelamento
     */
    public String getDescricao_parcelamento() {
        return descricao_parcelamento;
    }

    /**
     * @param descricao_parcelamento the descricao_parcelamento to set
     */
    public void setDescricao_parcelamento(String descricao_parcelamento) {
        this.descricao_parcelamento = descricao_parcelamento;
    }

    /**
     * @return the taxajuros
     */
    public Double getTaxajuros() {
        return taxajuros;
    }

    /**
     * @param taxajuros the taxajuros to set
     */
    public void setTaxajuros(Double taxajuros) {
        this.taxajuros = taxajuros;
    }

    /**
     * @return the quantidadeparcelas
     */
    public Integer getQuantidadeparcelas() {
        return quantidadeparcelas;
    }

    /**
     * @param quantidadeparcelas the quantidadeparcelas to set
     */
    public void setQuantidadeparcelas(Integer quantidadeparcelas) {
        this.quantidadeparcelas = quantidadeparcelas;
    }

    /**
     * @return the entrada
     */
    public Boolean getEntrada() {
        return entrada;
    }

    /**
     * @param entrada the entrada to set
     */
    public void setEntrada(Boolean entrada) {
        this.entrada = entrada;
    }
    
     @Override
    public String toString() {
        return getDescricao_parcelamento();
    }

}

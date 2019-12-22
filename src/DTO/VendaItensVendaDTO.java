package DTO;

import java.util.Date;

/**
 * @author MDiasT
 */
public class VendaItensVendaDTO {
    
    private Long idvenda;
    private Long idvendaitens;
    private String nome_razao;
    private Date data;
    private Integer quantidade;
    private String descricao;
    private Double valor;

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
     * @return the nome_razao
     */
    public String getNome_razao() {
        return nome_razao;
    }

    /**
     * @param nome_razao the nome_razao to set
     */
    public void setNome_razao(String nome_razao) {
        this.nome_razao = nome_razao;
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    
}

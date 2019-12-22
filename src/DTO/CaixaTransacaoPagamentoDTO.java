package DTO;

import java.util.Date;

/**
 * @author MDiasT
 */
public class CaixaTransacaoPagamentoDTO {
    
    private Long idcaixa;
    private Date data;
    private String descricao;
    private Double valortotal;
    private String descricaotransacao;
    private String descricaopagamento;

    /**
     * @return the idcaixa
     */
    public Long getIdcaixa() {
        return idcaixa;
    }

    /**
     * @param idcaixa the idcaixa to set
     */
    public void setIdcaixa(Long idcaixa) {
        this.idcaixa = idcaixa;
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

    /**
     * @return the descricaotransacao
     */
    public String getDescricaotransacao() {
        return descricaotransacao;
    }

    /**
     * @param descricaotransacao the descricaotransacao to set
     */
    public void setDescricaotransacao(String descricaotransacao) {
        this.descricaotransacao = descricaotransacao;
    }

    /**
     * @return the descricaopagamento
     */
    public String getDescricaopagamento() {
        return descricaopagamento;
    }

    /**
     * @param descricaopagamento the descricaopagamento to set
     */
    public void setDescricaopagamento(String descricaopagamento) {
        this.descricaopagamento = descricaopagamento;
    }

    
}

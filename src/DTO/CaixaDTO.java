package DTO;

import java.util.Date;

/**
 * @author MDiasT
 */
public class CaixaDTO {
    
    private Long idcaixa;
    private Date data;
    private String descricao;
    private Double valortotal;
    private Long idtransacao;
    private Long idpagamento;

    public CaixaDTO(){
    }

    public CaixaDTO(Long idcaixa, Date data, String descricao, Double valortotal, Long idtransacao, Long idpagamento) {
        this.idcaixa = idcaixa;
        this.data = data;
        this.descricao = descricao;
        this.valortotal = valortotal;
        this.idtransacao = idtransacao;
        this.idpagamento = idpagamento;
    }

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
     * @return the idtransacao
     */
    public Long getIdtransacao() {
        return idtransacao;
    }

    /**
     * @param idtransacao the idtransacao to set
     */
    public void setIdtransacao(Long idtransacao) {
        this.idtransacao = idtransacao;
    }

    /**
     * @return the idpagamento
     */
    public Long getIdpagamento() {
        return idpagamento;
    }

    /**
     * @param idpagamento the idpagamento to set
     */
    public void setIdpagamento(Long idpagamento) {
        this.idpagamento = idpagamento;
    }

    @Override
    public String toString() {
        return "CaixaDTO{" + "idcaixa=" + idcaixa + ", data=" + data + ", descricao=" + descricao + ", valortotal=" + valortotal + ", idtransacao=" + idtransacao + ", idpagamento=" + idpagamento + '}';
    }
}

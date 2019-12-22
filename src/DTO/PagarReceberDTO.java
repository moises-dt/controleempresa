package DTO;

import java.util.Date;

/**
 * @author MDT
 */
public class PagarReceberDTO {

    private Long id;
    private Date data_gerada;
    private String descricao_pagar;
    private Long numero_parcelas;
    private Double valor_parcela;
    private Double valor_total;
    private Double valor_final;
    private Double juros;
    private Long id_tipo_conta;
    private Long id_cliente;
    private Long id_parcelamento;

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
     * @return the data_gerada
     */
    public Date getData_gerada() {
        return data_gerada;
    }

    /**
     * @param data_gerada the data_gerada to set
     */
    public void setData_gerada(Date data_gerada) {
        this.data_gerada = data_gerada;
    }

    /**
     * @return the descricao_pagar
     */
    public String getDescricao_pagar() {
        return descricao_pagar;
    }

    /**
     * @param descricao_pagar the descricao_pagar to set
     */
    public void setDescricao_pagar(String descricao_pagar) {
        this.descricao_pagar = descricao_pagar;
    }

    /**
     * @return the numero_parcelas
     */
    public Long getNumero_parcelas() {
        return numero_parcelas;
    }

    /**
     * @param numero_parcelas the numero_parcelas to set
     */
    public void setNumero_parcelas(Long numero_parcelas) {
        this.numero_parcelas = numero_parcelas;
    }

    /**
     * @return the valor_parcela
     */
    public Double getValor_parcela() {
        return valor_parcela;
    }

    /**
     * @param valor_parcela the valor_parcela to set
     */
    public void setValor_parcela(Double valor_parcela) {
        this.valor_parcela = valor_parcela;
    }

    /**
     * @return the valor_total
     */
    public Double getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * @return the valor_final
     */
    public Double getValor_final() {
        return valor_final;
    }

    /**
     * @param valor_final the valor_final to set
     */
    public void setValor_final(Double valor_final) {
        this.valor_final = valor_final;
    }

    /**
     * @return the juros
     */
    public Double getJuros() {
        return juros;
    }

    /**
     * @param juros the juros to set
     */
    public void setJuros(Double juros) {
        this.juros = juros;
    }

    /**
     * @return the id_tipo_conta
     */
    public Long getId_tipo_conta() {
        return id_tipo_conta;
    }

    /**
     * @param id_tipo_conta the id_tipo_conta to set
     */
    public void setId_tipo_conta(Long id_tipo_conta) {
        this.id_tipo_conta = id_tipo_conta;
    }

    /**
     * @return the id_cliente
     */
    public Long getId_cliente() {
        return id_cliente;
    }

    /**
     * @param id_cliente the id_cliente to set
     */
    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * @return the id_parcelamento
     */
    public Long getId_parcelamento() {
        return id_parcelamento;
    }

    /**
     * @param id_parcelamento the id_parcelamento to set
     */
    public void setId_parcelamento(Long id_parcelamento) {
        this.id_parcelamento = id_parcelamento;
    }
    
    

}
package DTO;

import java.util.Date;

/**
 * @author MDiasT
 */
public class PagarReceberClienteParcelamentoDTO {

    private Long id;
    private Date data_gerada;
    private String descricao_pagar;
    private Long numero_parcelas;
    private Double valor_parcela;
    private Double valor_total;
    private Double valor_final;
    private Double juros;
    private String descricao_conta;
    private String nome_razao;
    private String descricao_parcelamento;
    private Long id_parcela;
    private Long id_pagar_receber;
    private String parcela_numero;
    private Date data_vencimento;
    private Boolean pago;
    private Double valor_pago;
    private Date data_pagamento;

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
     * @return the descricao_conta
     */
    public String getDescricao_conta() {
        return descricao_conta;
    }

    /**
     * @param descricao_conta the descricao_conta to set
     */
    public void setDescricao_conta(String descricao_conta) {
        this.descricao_conta = descricao_conta;
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
     * @return the id_parcela
     */
    public Long getId_parcela() {
        return id_parcela;
    }

    /**
     * @param id_parcela the id_parcela to set
     */
    public void setId_parcela(Long id_parcela) {
        this.id_parcela = id_parcela;
    }

    /**
     * @return the id_pagar_receber
     */
    public Long getId_pagar_receber() {
        return id_pagar_receber;
    }

    /**
     * @param id_pagar_receber the id_pagar_receber to set
     */
    public void setId_pagar_receber(Long id_pagar_receber) {
        this.id_pagar_receber = id_pagar_receber;
    }

    /**
     * @return the parcela_numero
     */
    public String getParcela_numero() {
        return parcela_numero;
    }

    /**
     * @param parcela_numero the parcela_numero to set
     */
    public void setParcela_numero(String parcela_numero) {
        this.parcela_numero = parcela_numero;
    }

    /**
     * @return the data_vencimento
     */
    public Date getData_vencimento() {
        return data_vencimento;
    }

    /**
     * @param data_vencimento the data_vencimento to set
     */
    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    /**
     * @return the pago
     */
    public Boolean getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    /**
     * @return the valor_pago
     */
    public Double getValor_pago() {
        return valor_pago;
    }

    /**
     * @param valor_pago the valor_pago to set
     */
    public void setValor_pago(Double valor_pago) {
        this.valor_pago = valor_pago;
    }

    /**
     * @return the data_pagamento
     */
    public Date getData_pagamento() {
        return data_pagamento;
    }

    /**
     * @param data_pagamento the data_pagamento to set
     */
    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    
}

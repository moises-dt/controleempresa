package DTO;

import java.util.Date;

/**
 * @author MDiasT
 */
public class ParcelasPRDTO {
    
    private Long id_parcela;
    private Long id_pagar_receber;
    private String parcela_numero;
    private Date data_vencimento;
    private Boolean pago;
    private Double valor_pago;
    private Date data_pagamento;

   
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
    
}

package DTO;

/**
 * @author MDT
 */
public class TipoPagamentoDTO {
    
    private Long idpagamento;
    private String descricao;
    
    public Long getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(Long idpagamento) {
        this.idpagamento = idpagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

     @Override
    public String toString() {
        return getDescricao();
    }
    
}

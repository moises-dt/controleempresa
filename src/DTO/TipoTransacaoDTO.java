package DTO;

/**
 * @author MDT
 */
public class TipoTransacaoDTO {
    
    private Long idtransacao;
    private String descricao;

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
    
    @Override
    public String toString() {
        return getDescricao();
    }
    
}

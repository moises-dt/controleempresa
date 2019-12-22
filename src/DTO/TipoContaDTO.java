package DTO;

/**
 * @author MDT
 */
public class TipoContaDTO {
    
    private Long idconta;
    private String descricao_conta;

    @Override
    public String toString() {
        return getDescricao_conta();
    }

    /**
     * @return the idconta
     */
    public Long getIdconta() {
        return idconta;
    }

    /**
     * @param idconta the idconta to set
     */
    public void setIdconta(Long idconta) {
        this.idconta = idconta;
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
}

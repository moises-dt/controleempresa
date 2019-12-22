package DTO;

import java.util.Date;

/**
 * @author MDT
 */
public class EstoqueProdutoFornecedorDTO {

    private Long idestoque;
    private String descricao;
    private String nome_razao;
    private Long quantidade;
    private Long notanumero;
    private Date data;

    /**
     * @return the idestoque
     */
    public Long getIdestoque() {
        return idestoque;
    }

    /**
     * @param idestoque the idestoque to set
     */
    public void setIdestoque(Long idestoque) {
        this.idestoque = idestoque;
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
     * @return the quantidade
     */
    public Long getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the notanumero
     */
    public Long getNotanumero() {
        return notanumero;
    }

    /**
     * @param notanumero the notanumero to set
     */
    public void setNotanumero(Long notanumero) {
        this.notanumero = notanumero;
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

   
}

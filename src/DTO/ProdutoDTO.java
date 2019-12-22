package DTO;

/**
 * @author MDT
 */
public class ProdutoDTO {

    private Long idproduto;
    private String codigo;
    private String descricao;
    private String tipovolume;
    private String categoria;
    private Double valorcompra;
    private Double comissaovendedor;
    private Double lucro;
    private Double transportecusto;
    private Boolean ativoinativo;
    private Boolean valorestipo;
    private Double valortotalproduto;
    private Integer quantidademinima;
    private Long quantidadeatual;


    /**
     * @return the idproduto
     */
    public Long getIdproduto() {
        return idproduto;
    }

    /**
     * @param idproduto the idproduto to set
     */
    public void setIdproduto(Long idproduto) {
        this.idproduto = idproduto;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * @return the tipovolume
     */
    public String getTipovolume() {
        return tipovolume;
    }

    /**
     * @param tipovolume the tipovolume to set
     */
    public void setTipovolume(String tipovolume) {
        this.tipovolume = tipovolume;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the valorcompra
     */
    public Double getValorcompra() {
        return valorcompra;
    }

    /**
     * @param valorcompra the valorcompra to set
     */
    public void setValorcompra(Double valorcompra) {
        this.valorcompra = valorcompra;
    }

    /**
     * @return the comissaovendedor
     */
    public Double getComissaovendedor() {
        return comissaovendedor;
    }

    /**
     * @param comissaovendedor the comissaovendedor to set
     */
    public void setComissaovendedor(Double comissaovendedor) {
        this.comissaovendedor = comissaovendedor;
    }

    /**
     * @return the lucro
     */
    public Double getLucro() {
        return lucro;
    }

    /**
     * @param lucro the lucro to set
     */
    public void setLucro(Double lucro) {
        this.lucro = lucro;
    }

    /**
     * @return the transportecusto
     */
    public Double getTransportecusto() {
        return transportecusto;
    }

    /**
     * @param transportecusto the transportecusto to set
     */
    public void setTransportecusto(Double transportecusto) {
        this.transportecusto = transportecusto;
    }

    /**
     * @return the ativoinativo
     */
    public Boolean getAtivoinativo() {
        return ativoinativo;
    }

    /**
     * @param ativoinativo the ativoinativo to set
     */
    public void setAtivoinativo(Boolean ativoinativo) {
        this.ativoinativo = ativoinativo;
    }

    /**
     * @return the valorestipo
     */
    public Boolean getValorestipo() {
        return valorestipo;
    }

    /**
     * @param valorestipo the valorestipo to set
     */
    public void setValorestipo(Boolean valorestipo) {
        this.valorestipo = valorestipo;
    }

    /**
     * @return the valortotalproduto
     */
    public Double getValortotalproduto() {
        return valortotalproduto;
    }

    /**
     * @param valortotalproduto the valortotalproduto to set
     */
    public void setValortotalproduto(Double valortotalproduto) {
        this.valortotalproduto = valortotalproduto;
    }

    /**
     * @return the quantidademinima
     */
    public Integer getQuantidademinima() {
        return quantidademinima;
    }

    /**
     * @param quantidademinima the quantidademinima to set
     */
    public void setQuantidademinima(Integer quantidademinima) {
        this.quantidademinima = quantidademinima;
    }

    /**
     * @return the quantidadeatual
     */
    public Long getQuantidadeatual() {
        return quantidadeatual;
    }

    /**
     * @param quantidadeatual the quantidadeatual to set
     */
    public void setQuantidadeatual(Long quantidadeatual) {
        this.quantidadeatual = quantidadeatual;
    }
    
    @Override
    public String toString() {
        return getDescricao();
    }
    
}

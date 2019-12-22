package controller;

import DTO.ClienteFornecedorFuncionarioDTO;
import DTO.EstoqueDTO;
import DTO.EstoqueProdutoFornecedorDTO;
import static controleempresa.Controleempresa.stage;
import exception.ApplicationException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import DTO.ProdutoDTO;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ClienteFornecedorFuncionarioService;
import service.EstoqueService;
import service.ProdutoService;
import util.Calculos;
import util.Conversor;
import util.Formatar;
import view.util.Alerts;

/**
 * FXML Controller class
 * @author MDT
 */
public class ProdutoController implements Initializable {

    Conversor convers = new Conversor();
    
    ProdutoService pSer = new ProdutoService();
    ProdutoDTO p = new ProdutoDTO();
    Formatar format = new Formatar();
    Calculos calculo = new Calculos();

    ClienteFornecedorFuncionarioService cffSer = new ClienteFornecedorFuncionarioService();
    ClienteFornecedorFuncionarioDTO cff = new ClienteFornecedorFuncionarioDTO();
    
    EstoqueService eSer = new EstoqueService();
    EstoqueDTO e = new EstoqueDTO();
    
    EstoqueProdutoFornecedorDTO epf = new EstoqueProdutoFornecedorDTO();
            
    /**
     * Initializes the controller class.
     */
    @FXML private TextField txtidproduto;
    @FXML private TextField txtcodigo;
    @FXML private TextField txtdescricao;
    @FXML private TextField txttipovolume;
    @FXML private TextField txtCategoria;
    @FXML private TextField txtValorCompra;
    @FXML private TextField txtLucro;
    @FXML private TextField txtCustoTransporte;
    @FXML private TextField txtComissaoVenda;
    @FXML private CheckBox txtAtivoInativo;
    @FXML private CheckBox txtValoresTipo;
    @FXML private TextField txtValorTotalProduto;
    @FXML private Label labelLucro;
    @FXML private Label labelTransporte;
    @FXML private Label labelComissao;

    @FXML private TableView<ProdutoDTO> tabela;
    @FXML private TableColumn<ProdutoDTO, Long> idproduto;
    @FXML private TableColumn<ProdutoDTO, String> codigo;
    @FXML private TableColumn<ProdutoDTO, String> descricao;
    @FXML private TableColumn<ProdutoDTO, String> tipovolume;
    @FXML private TableColumn<ProdutoDTO, String> categoria;
    @FXML private TableColumn<ProdutoDTO, String> valorcompra;
    @FXML private TableColumn<ProdutoDTO, Double> comissaovendedor;
    @FXML private TableColumn<ProdutoDTO, Double> lucro;
    @FXML private TableColumn<ProdutoDTO, Double> transportecusto;
    @FXML private TableColumn<ProdutoDTO, Boolean> ativoinativo;
    @FXML private TableColumn<ProdutoDTO, Boolean> valorestipo;
    @FXML private TableColumn<ProdutoDTO, Double> valortotalproduto;
    @FXML private TableColumn<ProdutoDTO, Integer> quantidademinima;
    @FXML private TableColumn<ProdutoDTO, Long> quantidadeatual;
    @FXML private TextField txtQuantidadeMinima;
    @FXML private TextField txtQuantidadeAtual;

    @FXML private Button btSalvarAlterar;
    @FXML private Button btExcluir;
    @FXML private Button btFechar;

    @FXML private Button btSalvarAlterarEstoque;
    @FXML private Button btExcluirEstoque;
    @FXML private Button btFechar1;

    @FXML private TableView<EstoqueProdutoFornecedorDTO> tabelaEstoque;
    @FXML private TableColumn<EstoqueProdutoFornecedorDTO, Long> idestoque;
    @FXML private TableColumn<EstoqueProdutoFornecedorDTO, String> idprodutoestoque;
    @FXML private TableColumn<EstoqueProdutoFornecedorDTO, String> idcliente;
    @FXML private TableColumn<EstoqueProdutoFornecedorDTO, Long> quantidade;
    @FXML private TableColumn<EstoqueProdutoFornecedorDTO, Long> notanumero;
    @FXML private TableColumn<EstoqueProdutoFornecedorDTO, Date> data;
    @FXML private TextField txtIdProdutoEstoque;
    @FXML private ComboBox<ProdutoDTO> cbDescricaoProduto;
    @FXML private TextField txtIdFornecedorEstoque;
    @FXML private ComboBox<ClienteFornecedorFuncionarioDTO> cbNomeRazao;
    @FXML private DatePicker txtDataEstoque;
    @FXML private TextField txtQuantidade;
    @FXML private TextField txtNotaNumero;
    @FXML private TextField txtIdEstoque;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableProduto();
        listaDeFornecedoresComboBox();
        listaDeProdutosComboBox();
        txtDataEstoque.setValue(LocalDate.now());
    }

    public void initTableProduto() {
        idproduto.setCellValueFactory(new PropertyValueFactory<>("idproduto"));
        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tipovolume.setCellValueFactory(new PropertyValueFactory<>("tipovolume"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        valorcompra.setCellValueFactory(new PropertyValueFactory<>("valorcompra"));
        comissaovendedor.setCellValueFactory(new PropertyValueFactory<>("comissaovendedor"));
        lucro.setCellValueFactory(new PropertyValueFactory<>("lucro"));
        transportecusto.setCellValueFactory(new PropertyValueFactory<>("transportecusto"));
        ativoinativo.setCellValueFactory(new PropertyValueFactory<>("ativoinativo"));
        valorestipo.setCellValueFactory(new PropertyValueFactory<>("valorestipo"));
        valortotalproduto.setCellValueFactory(new PropertyValueFactory<>("valortotalproduto"));
        quantidademinima.setCellValueFactory(new PropertyValueFactory<>("quantidademinima"));
        quantidadeatual.setCellValueFactory(new PropertyValueFactory<>("quantidadeatual"));

        tabela.setItems(listaDeProdutos());
    }
    
    public void initTableEstoque() {
        idestoque.setCellValueFactory(new PropertyValueFactory<>("idestoque"));
        idprodutoestoque.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        idcliente.setCellValueFactory(new PropertyValueFactory<>("nome_razao"));
        quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        notanumero.setCellValueFactory(new PropertyValueFactory<>("notanumero"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));

        tabelaEstoque.setItems(listaDeEstoques());
    }
    
    private ObservableList<EstoqueProdutoFornecedorDTO> listaDeEstoques() {
        try {
            return FXCollections.observableArrayList(eSer.listarOrdemPorCodigo(Long.parseLong(txtIdProdutoEstoque.getText())));
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Preenche a tabela de Produtos    
    private ObservableList<ProdutoDTO> listaDeProdutos() {
        try {
            return FXCollections.observableArrayList(pSer.listarOrdem());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Preenche a lista de Fornecedores no COMBOBOX fornecedores no Estoque só trás fornecedores    
    private ObservableList<ClienteFornecedorFuncionarioDTO> listaDeFornecedoresComboBox() {
        ObservableList listaDeFornecedoresComboBox;
        try {
            listaDeFornecedoresComboBox = FXCollections.observableArrayList(cffSer.listarOrdemCombo());
            cbNomeRazao.setItems(listaDeFornecedoresComboBox);
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Preenche a lista de Produtos no COMBOBOX Descrição no Estoque    
    private ObservableList<ProdutoDTO> listaDeProdutosComboBox() {
        ObservableList listaDeProdutosComboBox;
        try {
            listaDeProdutosComboBox = FXCollections.observableArrayList(pSer.listarOrdemCombo());
            cbDescricaoProduto.setItems(listaDeProdutosComboBox);
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @FXML
    public void onbtSalvarAlterarAction() {
        CalculaValorProduto();
        capturarInformacoes();
        try {
            if (txtcodigo.getText().trim().equals("")) {
                Alerts.showAlert("ATENÇÂO", "", "O campo CÒDIGO se encontra em branco", Alert.AlertType.WARNING);
            } else if (!txtcodigo.getText().trim().equals("") && pSer.buscarProdutoCodigo(txtcodigo.getText()) != null) {
                pSer.alterar(p);
                listaDeProdutosComboBox();
                initTableProduto();
                Alerts.showAlert("ATENÇÂO", "", "Produto já existe ALTERADO com SUCESSO", Alert.AlertType.WARNING);
                limparTelaProduto();
            } else if(txtidproduto.getText().trim().equals("") && pSer.buscarProdutoCodigo(txtcodigo.getText()) != null){
                Alerts.showAlert("ATENÇÂO", "", "Código do Produto já Cadastrado", Alert.AlertType.WARNING);
            } else {
                pSer.incluir(p);
                listaDeProdutosComboBox();
                initTableProduto();
                Alerts.showAlert("ATENÇÂO", "", "Produto CADASTRADO com SUCESSO", Alert.AlertType.WARNING);
                limparTelaProduto();
        }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void btExcluirAction() {
        try {
            Long produtoid = tabela.getSelectionModel().getSelectedItem().getIdproduto();
            if (produtoid.intValue() >= 1) {
                Alerts.showAlert("ATENÇÂO", "", "Necessário selecionar o que deseja excluir", Alert.AlertType.WARNING);
            } else {
                pSer.excluir(produtoid);
                initTableProduto();
                limparTelaProduto();
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onFecharAction() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewPrincipal.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void CalculaValorProduto() {
        Double ValorCompra, ComissaoVenda, Lucro, CustoTransporte = 0.00;
        if (txtCustoTransporte.getText().equals("") | txtComissaoVenda.getText().equals("") | txtLucro.getText().equals("")) {
            
        } else {
            ValorCompra = Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtValorCompra.getText()));
            ComissaoVenda = Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtComissaoVenda.getText()));
            Lucro = Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtLucro.getText()));
            CustoTransporte = Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtCustoTransporte.getText()));
            if (txtValoresTipo.isSelected()) {
                txtValorTotalProduto.setText(String.valueOf(format.doubleParaStringComPontoDeMilhar(calculo.calculaValorProdutoPorcentagem(ValorCompra, ComissaoVenda, Lucro, CustoTransporte))));
                txtValorCompra.setText(String.valueOf(format.doubleParaStringComPontoDeMilhar(ValorCompra)));
            } else {
                txtValorTotalProduto.setText(String.valueOf(format.doubleParaStringComPontoDeMilhar(calculo.calculaValorProduto(ValorCompra, ComissaoVenda, Lucro, CustoTransporte))));
                txtValorCompra.setText(String.valueOf(format.doubleParaStringComPontoDeMilhar(ValorCompra)));
            }
        }
    }
    
    //Função para troca de caracteres de % para R$ nos campos
    @FXML
    public void OnSelection() {
        Double ValorCompra, ComissaoVenda, Lucro, CustoTransporte, ValorTotalProduto = 0.00;

        if (txtCustoTransporte.getText().equals("") | txtComissaoVenda.getText().equals("") | txtLucro.getText().equals("")) {

        } else {
            ValorCompra = Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtValorCompra.getText()));
            ComissaoVenda = Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtComissaoVenda.getText()));
            Lucro = Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtLucro.getText()));
            CustoTransporte = Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtCustoTransporte.getText()));

            if (txtValoresTipo.isSelected()) {
                txtValoresTipo.setText("Valores em %");
                labelLucro.setText("%");
                labelComissao.setText("%");
                labelTransporte.setText("%");
                CalculaValorProduto();
            } else if (txtValoresTipo.isSelected() == false) {
                txtValoresTipo.setText("Valores em R$");
                labelLucro.setText("R$");
                labelComissao.setText("R$");
                labelTransporte.setText("R$");
                CalculaValorProduto();
            }
        }
    }
    
    //Função de pegada de dados da tabela Produto para legar para os campos da tela
    @FXML
    public void onMouseClicked(MouseEvent event) {
        if(event.getClickCount() == 2){
            limparTelaProduto();
            if(tabela.getSelectionModel().getSelectedItem()== null){
                Alerts.showAlert("ATENÇÂO", "", "Não existe informações a carregar 'a linha selecionada' se encontra em branco", Alert.AlertType.WARNING);
            }else{
                txtidproduto.setText(tabela.getSelectionModel().getSelectedItem().getIdproduto().toString());
                txtcodigo.setText(tabela.getSelectionModel().getSelectedItem().getCodigo());
                txtdescricao.setText(tabela.getSelectionModel().getSelectedItem().getDescricao());
                txttipovolume.setText(tabela.getSelectionModel().getSelectedItem().getTipovolume());
                txtCategoria.setText(tabela.getSelectionModel().getSelectedItem().getCategoria());
                //Convertendo o valor para formato moeda Brasilheira
                txtValorCompra.setText(format.doubleParaStringSemPontoDeMilhar(tabela.getSelectionModel().getSelectedItem().getValorcompra()));
                txtLucro.setText(String.format("%.2f", tabela.getSelectionModel().getSelectedItem().getLucro()));
                txtCustoTransporte.setText(String.format("%.2f", tabela.getSelectionModel().getSelectedItem().getTransportecusto()));
                txtComissaoVenda.setText(String.format("%.2f", tabela.getSelectionModel().getSelectedItem().getComissaovendedor()));
                //Levando da tabela para os campos
                if (Objects.equals(tabela.getSelectionModel().getSelectedItem().getAtivoinativo(), true)) {
                    txtAtivoInativo.setSelected(true);
                } else if (Objects.equals(tabela.getSelectionModel().getSelectedItem().getAtivoinativo(), false)) {
                    txtAtivoInativo.setSelected(false);
                }
                //Levando da tabela para os campos
                if (Objects.equals(tabela.getSelectionModel().getSelectedItem().getValorestipo(), true)) {
                    txtValoresTipo.setSelected(true);
                    OnSelection();
                } else if (Objects.equals(tabela.getSelectionModel().getSelectedItem().getValorestipo(), false)) {
                    txtValoresTipo.setSelected(false);
                    OnSelection();
                }
                txtValorTotalProduto.setText(format.doubleParaStringSemPontoDeMilhar(tabela.getSelectionModel().getSelectedItem().getValortotalproduto()));
                txtQuantidadeMinima.setText(tabela.getSelectionModel().getSelectedItem().getQuantidademinima().toString());
                txtQuantidadeAtual.setText(tabela.getSelectionModel().getSelectedItem().getQuantidadeatual().toString());
            }
        }
    }
    
    //Função que captura informações para gravar o Produto
    public void capturarInformacoes() {
        if (txtidproduto.getText().trim().equals("")){
        
        }else{
        p.setIdproduto(Long.parseLong(txtidproduto.getText()));
        }  
        p.setCodigo(txtcodigo.getText());
        p.setDescricao(txtdescricao.getText());
        p.setTipovolume(txttipovolume.getText());
        p.setCategoria(txtCategoria.getText());
        //Convertendo o valor em double simples
        p.setValorcompra(Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtValorCompra.getText())));
        p.setLucro(Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtLucro.getText())));
        p.setTransportecusto(Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtCustoTransporte.getText())));
        p.setComissaovendedor(Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtComissaoVenda.getText())));
        if (txtAtivoInativo.isSelected()) {
            p.setAtivoinativo(true);
        } else {
            p.setAtivoinativo(false);
        }
        if (txtValoresTipo.isSelected()) {
            p.setValorestipo(true);
        } else {
            p.setValorestipo(false);
        }
        p.setValortotalproduto(Double.parseDouble(format.convertendoTextoparaDoubleAmericano(txtValorTotalProduto.getText())));
        p.setQuantidademinima(Integer.parseInt(txtQuantidadeMinima.getText()));
    }

    //Colocar o id do Fornecedor no campo para gravar no combobox
    @FXML
    void aoSelecionarFornecedor() {
        ClienteFornecedorFuncionarioDTO fornecedor = cbNomeRazao.getSelectionModel().getSelectedItem();
        txtIdFornecedorEstoque.setText(fornecedor.getId().toString());
    }

    //Colocar o id do Produto no campo para gravar no combobox
    @FXML
    void aoSelecionarProduto() {
        ProdutoDTO produto = cbDescricaoProduto.getSelectionModel().getSelectedItem();
        txtIdProdutoEstoque.setText(produto.getIdproduto().toString());
        initTableEstoque();
    }

    public void capturarInformacoesEstoque() {
        if(txtIdEstoque.getText().trim().equals("")){

        }else{
            e.setIdestoque(Long.parseLong(txtIdEstoque.getText()));
        }
        e.setIdproduto(Long.parseLong(txtIdProdutoEstoque.getText()));
        e.setIdcliente(Long.parseLong(txtIdFornecedorEstoque.getText()));
        e.setQuantidade(Long.parseLong(txtQuantidade.getText()));
        e.setNotanumero(Long.parseLong(txtNotaNumero.getText()));
        try {
            e.setData(convers.localdatetoDate(txtDataEstoque.getValue()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
    
    //Executa a atualização de quantidade do produto no estoque 
    public void executaEntradaValorEstoque() {
        Long quantidade1, quantidade2, quantidade3, total, id;
        try {
            if(txtQuantidade.getText().trim().equals("")){
                Alerts.showAlert("ATENÇÂO", "", "É necessário digitar a quantidade de produtos no estoque", Alert.AlertType.WARNING);
            }else if(!txtIdEstoque.getText().trim().equals("")){
                quantidade3 = eSer.buscarIdProduto(Long.parseLong(txtIdEstoque.getText())).getQuantidade();
                id = Long.parseLong(txtIdProdutoEstoque.getText());
                quantidade1 = Long.parseLong(txtQuantidade.getText());
                quantidade2 = pSer.buscarProdutoId(Long.parseLong(txtIdProdutoEstoque.getText())).getQuantidadeatual();
                p.setQuantidadeatual(calculo.atualizaEntradaValoresEstoque(quantidade1, quantidade2, quantidade3));
                p.setIdproduto(id);
                pSer.updateQuantidadeAtual(p);
                initTableProduto();
            }else{
                id = Long.parseLong(txtIdProdutoEstoque.getText());
                quantidade1 = Long.parseLong(txtQuantidade.getText());
                quantidade2 = pSer.buscarProdutoId(Long.parseLong(txtIdProdutoEstoque.getText())).getQuantidadeatual();
                p.setQuantidadeatual(calculo.entradaValoresEstoque(quantidade1, quantidade2));
                p.setIdproduto(id);
                pSer.updateQuantidadeAtual(p);
                initTableProduto();
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void btExcluirEstoque() {
        Alerts.showAlert("ATENÇÂO", "", "Não é possível EXCLUIR informações do Estoque", Alert.AlertType.WARNING);
        limparTelaEstoque();
    }

    @FXML
    void onbtSalvarAlterarEstoque() {
        capturarInformacoesEstoque();
        try {
            if (txtIdProdutoEstoque.getText().equals("")) {
                Alerts.showAlert("ATENÇÂO", "", "Selecione o PRODUTO para cadastrar o Estoque", Alert.AlertType.WARNING);
            }else if(txtIdFornecedorEstoque.getText().equals("")){
                Alerts.showAlert("ATENÇÂO", "", "Selecione o FORNECEDOR para cadastrar o Estoque", Alert.AlertType.WARNING);
            } else if (!txtIdEstoque.getText().equals("")) {
                executaEntradaValorEstoque();
                eSer.alterar(e);
                initTableEstoque();
                Alerts.showAlert("ATENÇÂO", "", "Estoque ALTERADO com SUCESSO", Alert.AlertType.WARNING);
                limparTelaEstoque();
            } else {
                eSer.incluir(e);
                executaEntradaValorEstoque();
                initTableEstoque();
                Alerts.showAlert("ATENÇÂO", "", "Produto CADASTRADO com SUCESSO", Alert.AlertType.WARNING);
                limparTelaEstoque();
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    void ClickTabelaEstoque(MouseEvent event) {
        if(event.getClickCount() == 2){
            limparTelaEstoque();
            if(tabelaEstoque.getSelectionModel().getSelectedItem()== null){
                Alerts.showAlert("ATENÇÂO", "", "Necessário selecionar o produto Primeiro para depois Selecionar a Entrada no Estoque", Alert.AlertType.WARNING);
            }else{
                try {
                    String idprodutoretorno = eSer.buscarIdProduto(tabelaEstoque.getSelectionModel().getSelectedItem().getIdestoque()).getIdproduto().toString();
                    String idfornecedorretorno = eSer.buscarIdProduto(tabelaEstoque.getSelectionModel().getSelectedItem().getIdestoque()).getIdcliente().toString();
                        txtIdProdutoEstoque.setText(idprodutoretorno);
                        txtIdFornecedorEstoque.setText(idfornecedorretorno);
                } catch (ApplicationException ex) {
                    ex.printStackTrace();
                }
                txtDataEstoque.setValue(convers.asLocalDate(tabelaEstoque.getSelectionModel().getSelectedItem().getData()));
                txtIdEstoque.setText(tabelaEstoque.getSelectionModel().getSelectedItem().getIdestoque().toString());
                txtQuantidade.setText(tabelaEstoque.getSelectionModel().getSelectedItem().getQuantidade().toString());
                txtNotaNumero.setText(tabelaEstoque.getSelectionModel().getSelectedItem().getNotanumero().toString());
            }
        }
    }
    
    public void limparTelaEstoque(){
        txtIdEstoque.setText("");
        txtIdProdutoEstoque.setText("");
        txtIdFornecedorEstoque.setText("");
        txtQuantidade.setText("");
        txtNotaNumero.setText("");
        txtDataEstoque.setValue(LocalDate.now());
        txtIdFornecedorEstoque.setText("");
        txtIdProdutoEstoque.setText("");
    }
    
    public void limparTelaProduto(){
        txtidproduto.setText("");
        txtcodigo.setText("");
        txtdescricao.setText("");
        txttipovolume.setText("");
        txtCategoria.setText("");
        txtValorCompra.setText("");
        txtLucro.setText("");
        txtCustoTransporte.setText("");
        txtComissaoVenda.setText("");
        p.setAtivoinativo(false);
        p.setValorestipo(false);
        txtValorTotalProduto.setText("");
        txtQuantidadeMinima.setText("");
    }
    
    @FXML
    void aoSair(ActionEvent event) {
        CalculaValorProduto();
    }
    
}

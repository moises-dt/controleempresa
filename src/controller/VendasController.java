package controller;

import DTO.ClienteFornecedorFuncionarioDTO;
import DTO.ProdutoDTO;
import DTO.VendaItensDTO;
import DTO.VendaItensVendaDTO;
import DTO.VendasDTO;
import static controleempresa.Controleempresa.stage;
import exception.ApplicationException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ClienteFornecedorFuncionarioService;
import service.ProdutoService;
import service.VendaItensService;
import service.VendasService;
import util.Conversor;
import util.Formatar;
import util.ValidadorCampos;
import view.util.Alerts;

/**
 * FXML Controller class
 * @author MDiasT
 */

public class VendasController implements Initializable {
    
    ProdutoDTO pDTO = new ProdutoDTO();
    ProdutoService pSER = new ProdutoService();
    
    VendasService vSER = new VendasService();
    VendaItensService viSER = new VendaItensService();
    
    VendasDTO vDTO = new VendasDTO();
    
    VendaItensDTO viDTO = new VendaItensDTO();
    
    ClienteFornecedorFuncionarioDTO cff = new ClienteFornecedorFuncionarioDTO();
    ClienteFornecedorFuncionarioService cffSER = new ClienteFornecedorFuncionarioService();
    
    Formatar formata = new Formatar();
    
    Conversor converte = new Conversor();
    
    ValidadorCampos validador = new ValidadorCampos();
    
    @FXML private Tab TabPaneFormaPagamento;

    //Busca e Pesquisa da tela Cliente
    @FXML private Tab TabPaneCliente;
    @FXML private TextField txtNome;
    @FXML private TextField txtId;
    @FXML private TextField txtIdCliente;
    @FXML private TextField txtNomeRazao;
    @FXML private Button btPesquisa;
    @FXML private Button btFechar;
    
    //tabela cliente 
    @FXML private TableView<ClienteFornecedorFuncionarioDTO> TabelaCliente;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Long>id;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>cpf_cnpj;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>nome_razao;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>endereco;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Integer>numero;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>complemento;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>bairro;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>cidade;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>cep;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>uf;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>sexo;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>email;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>site;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>telefone;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>telefone1;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Date>data_cadastro;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Boolean>cliente;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Boolean>fornecedor;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Boolean>funcionario;
    
    @FXML private TableView<VendaItensVendaDTO> tabelaItensVenda;
    @FXML private TableColumn<VendaItensVendaDTO, Long> tblId;
    @FXML private TableColumn<VendaItensVendaDTO, Long> tblIdVenda;
    @FXML private TableColumn<VendaItensVendaDTO, String> tblCliente;
    @FXML private TableColumn<VendaItensVendaDTO, Date> tblData;
    @FXML private TableColumn<VendaItensVendaDTO, Integer> tblQtd;
    @FXML private TableColumn<VendaItensVendaDTO, String> tblProduto;
    @FXML private TableColumn<VendaItensVendaDTO, Double> tblValor;    
    @FXML private DatePicker CmbData;
    
    @FXML private Tab TabPaneProdutos;
    @FXML private TextField txtIdVenda;
    @FXML private TextField txtValorTotal;
    @FXML private Button btPesquisaProduto;
    @FXML private Button btFecharProduto;
    @FXML private Button btFinalizarVenda;
    @FXML private TextField txtIdPesquisa;
    
    @FXML private TextField txtDescricaoPesquisa;
    @FXML private Button btAdicionaProduto;
    @FXML private Button btRetiraProduto;
    @FXML private TextField txtIdProduto;
    @FXML private TextField txtDescricaoProduto;
    @FXML private TextField txtQtd;
    @FXML private TextField txtValorUnitario;
    @FXML private TextField txtValorUnitarioTotal;
    
    @FXML private TableView<ProdutoDTO>tabelaProduto;
    @FXML private TableColumn<ProdutoDTO, Long>idproduto;
    @FXML private TableColumn<ProdutoDTO, String>codigo;
    @FXML private TableColumn<ProdutoDTO, String>descricao;
    @FXML private TableColumn<ProdutoDTO, String>tipovolume;
    @FXML private TableColumn<ProdutoDTO, String>categoria;
    @FXML private TableColumn<ProdutoDTO, String>valorcompra;
    @FXML private TableColumn<ProdutoDTO, Double>comissaovendedor;
    @FXML private TableColumn<ProdutoDTO, Double>lucro;
    @FXML private TableColumn<ProdutoDTO, Double>transportecusto;
    @FXML private TableColumn<ProdutoDTO, Boolean>ativoinativo;
    @FXML private TableColumn<ProdutoDTO, Boolean>valorestipo;
    @FXML private TableColumn<ProdutoDTO, Double>valortotalproduto;
    @FXML private TableColumn<ProdutoDTO, Integer>quantidademinima;
    @FXML private TableColumn<ProdutoDTO, Long>quantidadeatual;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableCliente();
        initTableVendaItens();
        initTableProduto();
        validador.setTextFieldInteger(txtIdPesquisa);
        validador.setTextFieldInteger(txtQtd);
        validador.setTextFieldInteger(txtId);
        validador.setTextFieldString(txtNome);
        CmbData.setValue(LocalDate.now());
    }    
    //Base da tabela de CLientes
    public void initTableCliente() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cpf_cnpj.setCellValueFactory(new PropertyValueFactory<>("cpf_cnpj"));
        nome_razao.setCellValueFactory(new PropertyValueFactory<>("nome_razao"));
        endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        complemento.setCellValueFactory(new PropertyValueFactory<>("complemento"));
        bairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        cep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        uf.setCellValueFactory(new PropertyValueFactory<>("uf"));
        sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        site.setCellValueFactory(new PropertyValueFactory<>("site"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        telefone1.setCellValueFactory(new PropertyValueFactory<>("telefone1"));
        data_cadastro.setCellValueFactory(new PropertyValueFactory<>("data_cadastro"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        fornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
        funcionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));

        if(txtId.getText().trim().equals("") && txtNome.getText().trim().equals("")){
            TabelaCliente.setItems(listaCLientes());
        }else if(txtId.getText().trim().equals("") && !txtNome.getText().trim().equals("")){
            TabelaCliente.setItems(listaCLientesNome());
        }else if(txtNome.getText().trim().equals("") && !txtId.getText().trim().equals("")){
            TabelaCliente.setItems(listaCLientesCodigo());
        }
    }
    //Base inicial que preenche a tabela clientes
    private ObservableList<ClienteFornecedorFuncionarioDTO> listaCLientes() {
        try {
            return FXCollections.observableArrayList(cffSER.listarOrdem());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //Base de BUSCA por NOME da tabela clientes 
    private ObservableList<ClienteFornecedorFuncionarioDTO> listaCLientesNome() {
        try {
            String texto = txtNome.getText();
            return FXCollections.observableArrayList(cffSER.buscarclientefornecedorfuncionarionome(texto));
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //Base de BUSCA por CÒDIGO da tabela clientes
    private ObservableList<ClienteFornecedorFuncionarioDTO> listaCLientesCodigo() {
        try {
            Long codigo = Long.parseLong(txtId.getText());
            return FXCollections.observableArrayList(cffSER.buscarclientefornecedorfuncionariocodigo(codigo));
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    // Botão Fechar o que funciona
    @FXML
    void aoFecharAction(ActionEvent event) {
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
    
    //Função de Pesquisa da parte de Clientes
    @FXML
    void aoPesquisarAction(ActionEvent event) {
        if(txtId.getText().trim().equals("")){
            try {
                cffSER.buscarclientefornecedorfuncionarionome(txtNome.getText());
                initTableCliente();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }else if (txtNome.getText().trim().equals("")){
            try {
                cffSER.buscarclientefornecedorfuncionariocodigo(Long.parseLong(txtId.getText()));
                initTableCliente();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
       }
    }
    
    //Função de Selecionar o id e o nome do cliente da tabela cliente
    @FXML
    void aoClicarTabelaCliente(MouseEvent event) {
        if(event.getClickCount()==2){
           txtIdCliente.setText(TabelaCliente.getSelectionModel().getSelectedItem().getId().toString());
            txtNomeRazao.setText(TabelaCliente.getSelectionModel().getSelectedItem().getNome_razao());
        }
    }
    
//====================================================================================================//
    //Itens de preenchimento da tabela de itens de venda
    public void initTableVendaItens() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("idvenda"));
        tblIdVenda.setCellValueFactory(new PropertyValueFactory<>("idvendaitens"));
        tblCliente.setCellValueFactory(new PropertyValueFactory<>("nome_razao"));
        tblData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tblQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tblProduto.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tblValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        if(!txtIdVenda.getText().trim().equals("")){
            tabelaItensVenda.setItems(listaItensVenda());
        }
    }
    
    //Base inicial que preenche a tabela Itens de Venda
    private ObservableList<VendaItensVendaDTO> listaItensVenda() {
        try {
            return FXCollections.observableArrayList(vSER.listarOrdemTabela(Long.parseLong(txtIdVenda.getText())));
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void somaTotal() throws ApplicationException{
        if(vSER.listarOrdemTabela(Long.parseLong(txtIdVenda.getText())) != null){
        Integer tamanho = tabelaItensVenda.getItems().size();
        Double valor = 0.0;
        for(int i = 0; i < tamanho; i++) {
            try {
                valor += vSER.listarOrdemTabela(Long.parseLong(txtIdVenda.getText())).get(i).getValor();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }
        txtValorTotal.setText(formata.doubleParaStringComPontoDeMilhar(valor));
        }
    }
    
    //====================================================================================================//
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

        if(txtIdPesquisa.getText().trim().equals("") && txtDescricaoPesquisa.getText().trim().equals("")){
            tabelaProduto.setItems(listaDeProdutos());
        }else if(txtIdPesquisa.getText().trim().equals("") && !txtDescricaoPesquisa.getText().trim().equals("")){
            tabelaProduto.setItems(listaProdutosNome());
        }else if(txtDescricaoPesquisa.getText().trim().equals("") && !txtIdPesquisa.getText().trim().equals("")){
            tabelaProduto.setItems(listaProdutosCodigo());
        }
    }
    
    //Preenche a tabela de Produtos    
    private ObservableList<ProdutoDTO> listaDeProdutos() {
        try {
            return FXCollections.observableArrayList(pSER.listarOrdem());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    //Base de BUSCA por DESCRIÇÂO da tabela Produtos 
    private ObservableList<ProdutoDTO> listaProdutosNome() {
        try {
            return FXCollections.observableArrayList(pSER.buscarProdutoDescricao(txtDescricaoPesquisa.getText()));
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //Base de BUSCA por CÒDIGO da tabela Produtos
    private ObservableList<ProdutoDTO> listaProdutosCodigo() {
        try {
            return FXCollections.observableArrayList(pSER.buscarProdutoId(Long.parseLong(txtIdPesquisa.getText())));
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @FXML
    void aoClicarTabelaProduto(MouseEvent event) {
        if(event.getClickCount()==2){
            txtIdProduto.setText(tabelaProduto.getSelectionModel().getSelectedItem().getIdproduto().toString());
            txtDescricaoProduto.setText(tabelaProduto.getSelectionModel().getSelectedItem().getDescricao());
            txtValorUnitario.setText(formata.doubleParaStringComPontoDeMilhar(tabelaProduto.getSelectionModel().getSelectedItem().getValortotalproduto()));
            txtValorUnitarioTotal.setText(formata.doubleParaStringComPontoDeMilhar(calculodetotal(txtQtd.getText(), txtValorUnitario.getText())));
        }
    }
    
       @FXML
    void aoPesquisarProdutoAction(ActionEvent event) {
        if(txtIdPesquisa.getText().trim().equals("")){
            try {
                pSER.buscarProdutoDescricao(txtDescricaoPesquisa.getText());
                initTableProduto();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }else if (txtDescricaoPesquisa.getText().trim().equals("")){
            try {
                pSER.buscarProdutoId(Long.parseLong(txtIdPesquisa.getText()));
                initTableProduto();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }else if(!txtIdPesquisa.getText().trim().equals("") && !txtDescricaoPesquisa.getText().trim().equals("")){
            Alerts.showAlert("ATENÇÂO", "", "É necessário deixar só um campo, código ou descrição, com informações para busca!!!", Alert.AlertType.WARNING);
        }
    }
    
    @FXML
    void aoFecharProdutoAction(ActionEvent event) {
        aoFecharAction(event);
    }
    
    @FXML
    void aoAdicionarProdutosAction(ActionEvent event) {
        if(!txtIdProduto.getText().trim().equals("") && !txtDescricaoProduto.getText().trim().equals("")){
            if(!txtIdCliente.getText().trim().equals("")){
                try {
                    if(txtIdVenda.getText().trim().equals("")){
                        Long idvenda = 0l;
                        capturandoInformaçõesVenda();
                        idvenda = vSER.incluir(vDTO);
                        txtIdVenda.setText(idvenda.toString());
                        capturandoInformaçõesItensVenda();
                        viSER.incluir(viDTO);
                        initTableVendaItens();
                        somaTotal();
                        limpaTela();
                    }else{
                        capturandoInformaçõesItensVenda();
                        viSER.incluir(viDTO);
                        initTableVendaItens();
                        somaTotal();
                        limpaTela();
                    }
                } catch (ApplicationException ex) {
                    ex.printStackTrace();
                }    
            }else{
                Alerts.showAlert("ATENÇÂO", "", "Necessário selecionar o Cliente na tela Anterior", Alert.AlertType.WARNING);
            }
        }else{
            Alerts.showAlert("ATENÇÂO", "", "Necessita Selecionar o Produto e a quantidade antes de Adicionar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void aoRetirarProdutosAction(ActionEvent event) {
        Long idvendaitens = 0l;
        if(idvendaitens > 1l){
            Alerts.showAlert("ATENÇÂO", "", "Necessário selecionar o que deseja excluir", Alert.AlertType.WARNING);
        }else{
            try {
                viSER.excluir(tabelaItensVenda.getSelectionModel().getSelectedItem().getIdvendaitens());
                initTableVendaItens();
                somaTotal();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @FXML
    void aoFinalizarVendaAction(ActionEvent event) {
        try {
            capturandoInformaçõesVenda();
            vSER.alterar(vDTO);
            
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }
    
    public void capturandoInformaçõesItensVenda(){
        viDTO.setIdvenda(Long.parseLong(txtIdVenda.getText()));
        viDTO.setIdproduto(Long.parseLong(txtIdProduto.getText()));
        viDTO.setQuantidade(Integer.parseInt(txtQtd.getText()));
        viDTO.setValor(Double.parseDouble(formata.convertendoTextoparaDoubleAmericano(txtValorUnitarioTotal.getText())));
    }
    
    public void capturandoInformaçõesVenda(){
        vDTO.setIdcliente(Long.parseLong(txtIdCliente.getText()));
        try {
            vDTO.setData(converte.localdatetoDate(CmbData.getValue()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        vDTO.setValortotal(Double.parseDouble(formata.convertendoTextoparaDoubleAmericano(txtValorTotal.getText())));
        if(!txtIdVenda.getText().trim().equals("")){
            vDTO.setIdvenda(Long.parseLong(txtIdVenda.getText()));
        }
    }
    
    Double calculodetotal(String qtd, String valor){
       Double total, valor1 = 0.0;
       Integer qtd1=0;
       qtd1 = Integer.parseInt(qtd);        
       valor1 = Double.parseDouble(formata.convertendoTextoparaDoubleAmericano(valor));
        
       total = qtd1 * valor1;
       
       return total;
    }
    
// Calcula a multiplicação do campo ao gerar evento no mesmo caso apertando a tecla Enter ou Return    
    @FXML
    void setOnAction(ActionEvent event) {
        if(!txtQtd.getText().trim().equals("0")) {
            if(txtValorUnitario.getText().trim().equals("")){
                Alerts.showAlert("ATENÇÂO", "", "É necessário ter valores nos campos Qtd e Valor!!!", Alert.AlertType.WARNING);
            }else{
                txtValorUnitarioTotal.setText(formata.doubleParaStringComPontoDeMilhar(calculodetotal(txtQtd.getText(), txtValorUnitario.getText())));
            }
        }
    }
// Função que limpa a tela    
    void limpaTela(){
        txtIdProduto.setText("");
        txtQtd.setText("1");
        txtValorUnitarioTotal.setText("");
        txtDescricaoProduto.setText("");
        txtValorUnitario.setText("");
    }
}


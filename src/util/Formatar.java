/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import exception.ApplicationException;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author Alessandro
 */
public class Formatar {

    private SimpleDateFormat formatoDataHota = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private Locale Brasil = new Locale("pt", "BR");
    
    private NumberFormat formatoValor = NumberFormat.getInstance(Locale.ENGLISH);
    private NumberFormat dinheiro = NumberFormat.getCurrencyInstance();
    private SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatoAmericano = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat formatoAmericano2 = new SimpleDateFormat("yyyyMMdd");
    private DecimalFormat df = new DecimalFormat("R$ ,##0.00");
    private DecimalFormat porc = new DecimalFormat("#0.00");
    
    
    // int codigo = 45075;
    // String.format ("%010d", codigo) - para add zeros a esquerda (com tamanho total 10)
    //String.format ("%010d", 45075) - para add zeros a esquerda(com tamanho total 10)
    public int converteStringParaInt(String campo) throws ApplicationException {
        int n = 0;
        try {
            n = Integer.parseInt(campo);
        } catch (NumberFormatException nfe) {
            throw new ApplicationException(nfe.getMessage() + " Informe Somente Numero(s)");

        }
        return n;
    }

    public Date stringParaDate(String dtString) throws ApplicationException {
        Date dt = new Date();

        if (dtString == null || dtString.trim().equals("") || dtString.trim().length() < 8) {  //9
            return null;
        }

        if (dtString.trim().length() >= 8 && dtString.length() <= 9) {
            StringBuilder st = new StringBuilder(dtString);
            int ano = Integer.parseInt(dtString.substring(6, dtString.length()));
            if (ano > 99) {
                return null;
            }

            if (ano >= 0 && ano <= 12) {
                ano = ano + 2000;
            } else {
                ano = ano + 1900;
            }
            String dataNova = st.substring(0, 6) + ano;
            dtString = dataNova;
        }

        try {
            dt = formatoData.parse(dtString);
        } catch (ParseException ex) {
            throw new ApplicationException(ex.getMessage() + " Data Invalida(De String Para Date)");
        }

        return dt;

    }

    public Date stringAmericanaParaDate(String dtString) throws ApplicationException {

        if (dtString == null || dtString.trim().equals("") || dtString.trim().length() < 8) {  //9
            return null;
        }

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = (java.util.Date) formatter.parse(dtString);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public String dateParaString(Date dt) {
        if (dt == null) {
            return "";
        }
        return formatoData.format(dt);
    }

    public String dateParaStringAmericano(Date dt) {
        if (dt == null) {
            return "";
        }
        return formatoAmericano.format(dt);
    }

    public long dateParaStringAmericanoSemSeparador(Date dt) {
        if (dt == null) {
            return 0;
        }
        long n = Long.parseLong(formatoAmericano2.format(dt));
        return n;
    }

    public String doubleParaString(Double valor) {

        formatoValor.setGroupingUsed(true);
        formatoValor.setMaximumFractionDigits(2);
        formatoValor.setMinimumFractionDigits(2);
        return formatoValor.format(valor);
    }
    
    public String doubleParaStringBrasil(Double valor) {
        
        dinheiro.setGroupingUsed(true);
        dinheiro.setMaximumFractionDigits(2);
        dinheiro.setMinimumFractionDigits(2);
        return dinheiro.format(valor);
    }
    
    public String ValorParaPercentual(Double valor) {
        
//        formatoValor.setGroupingUsed(true);
//        formatoValor.setMaximumFractionDigits(2);
//        formatoValor.setMinimumFractionDigits(2);
//        return formatoValor.format(valor);
            
//        porc.setGroupingUsed(true);
//        porc.setMaximumFractionDigits(2);
//        porc.setMinimumFractionDigits(2);
        return porc.format(valor);
    }

    public String doubleParaString4Decimal(Double valor) {

        formatoValor.setGroupingUsed(true);
        formatoValor.setMaximumFractionDigits(4);
        formatoValor.setMinimumFractionDigits(4);
        return formatoValor.format(valor);

    }
    
    public String StringparaDoubleAmericano(Double valor) {

        formatoValor.setGroupingUsed(true);
        formatoValor.setMaximumFractionDigits(2);
        formatoValor.setMinimumFractionDigits(2);
        return formatoValor.format(valor);

    }

    public double stringParaDouble(String valor) throws ApplicationException {

        double vlr = 0.00;
        try {
            formatoValor.setGroupingUsed(true);
            formatoValor.setMaximumFractionDigits(2);
            formatoValor.setMinimumFractionDigits(2);
            String vl1 = String.valueOf(formatoValor.parse(valor));
            
            vlr = Double.valueOf(vl1);

        } catch (ParseException ex) {
            throw new ApplicationException(ex.getMessage() + " Valor Invalido");
        }
        return vlr;
    }
    //Retira os R$ e virgulas e coloca com pontos
    public String convertendoTextoparaDoubleAmericano(String valor){
        String texto = valor.replaceAll("[R$ . %]", "");
        String texto1 = texto.replaceAll("[,]", ".");
        return texto1;
    }
    
    //Retira os Pontos e coloca vírgula
    public String convertendoDoubleparaDoubleBrasil(String valor){
        String texto = valor.replaceAll("[R$ %]", "");
        String texto1 = texto.replaceAll("[.]", ",");
        return texto1;
    }
    
    public String doubleParaStringSemPontoDeMilhar(Double valor) {
        String dx = null;
        dx = df.format(valor);
        return dx;
    }
        
    public String doubleParaStringComPontoDeMilhar(Double valor) {
        String dx = null;
        dx = dinheiro.format(valor);
        return dx;
    }

    public Date stringParaDateHota(String dtString) throws ApplicationException {
        if (dtString == null || dtString.trim().equals("")) {
            return null;
        }

        Date dt = new Date();

        try {
            dt = formatoDataHota.parse(dtString);
        } catch (ParseException ex) {
            throw new ApplicationException(ex.getMessage() + " Data Invalida");
        }

        return dt;

    }

    public String DateHoraParaString(Timestamp data) throws ApplicationException {
        return formatoDataHota.format(data);
    }

    public int stringParaInt(String valor) throws ApplicationException {
        int vlr = 0;
        try {
            formatoValor.setGroupingUsed(true);
            formatoValor.setMaximumFractionDigits(0);
            formatoValor.setMinimumFractionDigits(0);
            String vl1 = String.valueOf(formatoValor.parse(valor));

            vlr = Integer.valueOf(vl1);
        } catch (ParseException ex) {
            throw new ApplicationException(ex.getMessage() + " Informe Somente Valor");
        }
        return vlr;
    }

    //Quando realiza a diferença em dias entre duas datas, este método considera as horas restantes e as converte em fração de dias.
    public double diferencaEmDias(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        double diferencaEmDias = (diferenca / 1000) / 60 / 60 / 24; //resultado é diferença entre as datas em dias
        long horasRestantes = (diferenca / 1000) / 60 / 60 % 24; //calcula as horas restantes
        result = diferencaEmDias + (horasRestantes / 24d); //transforma as horas restantes em fração de dias

        return result;
    }

    //Quando realiza a diferença em horas entre duas datas, este método considera os minutos restantes e os converte em fração de horas.
    public double diferencaEmHoras(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        long diferencaEmHoras = (diferenca / 1000) / 60 / 60;
        long minutosRestantes = (diferenca / 1000) / 60 % 60;
        double horasRestantes = minutosRestantes / 60d;
        result = diferencaEmHoras + (horasRestantes);

        return result;
    }

    //Quando realiza a diferença em minutos entre duas datas, este método considera os segundos restantes e os converte em fração de minutos.
    public double diferencaEmMinutos(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        double diferencaEmMinutos = (diferenca / 1000) / 60; //resultado é diferença entre as datas em minutos
        long segundosRestantes = (diferenca / 1000) % 60; //calcula os segundos restantes
        result = diferencaEmMinutos + (segundosRestantes / 60d); //transforma os segundos restantes em minutos

        return result;
    }

//    public int diferencaEmMeses(Date dataInicial, Date dataFinal) {
//        if (dataInicial == null || dataFinal == null) {
//            return 0;
//        }
//        int ano=0, mes=0, dia=0;
//        Calendar c = Calendar.getInstance();
//        c.setTime(dataInicial);
//        ano = c.get(c.YEAR);
//        mes = (c.get(c.MONTH)) + 1;
//        dia = c.get(c.DAY_OF_MONTH);
//        
//        DateTime inicio = new DateTime(ano, mes, dia, 0, 0, 0, 0);
//        
//        c.setTime(dataFinal);
//        ano = c.get(c.YEAR);
//        mes = (c.get(c.MONTH)) + 1;
//        dia = c.get(c.DAY_OF_MONTH);
//        
//        DateTime fim = new DateTime(ano, mes, dia, 23, 59, 0, 0);
//        
//        return Months.monthsBetween(inicio, fim).getMonths();
//    }

    public String intParaString(int valor) {

        formatoValor.setGroupingUsed(true);
        formatoValor.setMaximumFractionDigits(0);
        formatoValor.setMinimumFractionDigits(0);
        return formatoValor.format(valor);

    }

    public List<String> listaSexo() {
        List<String> lista = new ArrayList<String>();
        lista.add("..Selecione..");
        lista.add("FEMININO");
        lista.add("MASCULINO");
        return lista;
    }

    public List<String> listaTipoPessoa() {
        List<String> lista = new ArrayList<String>();
        lista.add("..Selecione..");
        lista.add("FISICA");
        lista.add("JURIDICA");
        return lista;
    }

    public List<String> listaCarater() {
        List<String> lista = new ArrayList<String>();
        lista.add("TODOS");
        lista.add("ELETIVA");
        lista.add("URGENCIA");
        lista.add("RETORNO");
        return lista;
    }

    public List<String> listaSituacao() {
        List<String> lista = new ArrayList<String>();
        lista.add("..Selecione..");
        lista.add("ATIVO");
        lista.add("INATIVO");
        return lista;
    }

    public List<String> listaEstadoCivil() {
        List<String> lista = new ArrayList<String>();
        lista.add("..Selecione..");
        lista.add("CASADO(A)");
        lista.add("SOLTEIRO(A)");
        lista.add("DIVORCIADO(A)");
        lista.add("VIUVO(A)");
        lista.add("NAO IDENTIFICADO");
        return lista;
    }

    public List<String> listaUf() { //Portador de Necessidades Especiais
        List<String> lista = new ArrayList<String>();
        lista.add("..Selecione..");
        lista.add("AC");
        lista.add("AL");
        lista.add("AM");
        lista.add("AP");
        lista.add("BA");
        lista.add("CE");
        lista.add("DF");
        lista.add("ES");
        lista.add("GO");
        lista.add("MA");
        lista.add("MG");
        lista.add("MS");
        lista.add("MT");
        lista.add("PA");
        lista.add("PB");
        lista.add("PE");
        lista.add("PI");
        lista.add("PR");
        lista.add("RJ");
        lista.add("RN");
        lista.add("RO");
        lista.add("RR");
        lista.add("RS");
        lista.add("SC");
        lista.add("SE");
        lista.add("SP");
        lista.add("TO");
        return lista;
    }

    public String mascaraPlanoContas(String codReceita) {

        StringBuilder novo = new StringBuilder();

        for (int i = 0; i < 20; i++) {
            if (i == 0) {
                novo.append(codReceita.substring(0, 1));
            } else if (i == 1) {
                novo.append(".");
                novo.append(codReceita.substring(1, 4));
            } else if (i == 4) {
                novo.append(".");
                novo.append(codReceita.substring(4, 8));
            } else if (i == 8) {
                novo.append(".");
                novo.append(codReceita.substring(8, 12));
            } else if (i == 12) {
                novo.append(".");
                novo.append(codReceita.substring(12, 16));
            } else if (i == 16) {
                novo.append(".");
                novo.append(codReceita.substring(16, 20));
            }
        }
        return novo.toString();

    }

    public String nomeArquivo(String nomeArq, String nome) {

        return nomeArq + formataNome(nome) + "_" + formataData() + ".PDF";

    }

    private String formataNome(String name) {
        return name.replaceAll(" ", "_").replaceAll("\\.", "_");
    }

    private String formataData() {
        DateFormat df = new SimpleDateFormat("ddMMyyyy");
        return df.format(new Date());
    }

    public String converteCaracteresEspeciais(String text) {
        if (text != null) {
            return text.replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e").replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u").replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I").replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C').replace('ñ', 'n').replace('Ñ', 'N').replace('µ', 'A').replaceAll("!", "").replaceAll("\\[\\´\\`\\?!\\@\\#\\$\\%\\¨\\*", "").replaceAll("\\(\\)\\=\\{\\}\\[\\]\\~\\^\\]", "").replaceAll("[\\.\\;\\-\\_\\+\\'\\ª\\º\\:\\;\\/\\°]", "");
        }
        return "";
    }

    public String retirarAcentos(String text) {
        return text.replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e").replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u").replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I").replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C').replace('ñ', 'n').replace('Ñ', 'N').replace('µ', 'A');
    }

    public String alinharStringAesquerda(String var, int tamanho) {

        StringBuilder st = new StringBuilder(tamanho);
        st.append(var);
        for (int i = var.length(); i < tamanho; i++) {
//            st.append(".");
            st.append(" ");
        }

        return st.toString();
    }

    public String alinharStringComZerosAesquerda(String var, int tamanho) {

        StringBuilder st = new StringBuilder(tamanho);
        st.append(var);
        for (int i = var.length(); i < tamanho; i++) {
            st.append("0");
            //st.append(" ");
        }

        return st.toString();
    }

    public String alinharStringAdireita(String var, int tamanho) {

        StringBuilder st = new StringBuilder(tamanho);
        for (int i = var.length(); i < tamanho; i++) {
//            st.append(".");
            st.append(" ");
        }
        st.append(var);

        return st.toString();
    }

    public String alinharStringComZerosAdireita(String var, int tamanho) {

        StringBuilder st = new StringBuilder(tamanho);
        for (int i = var.length(); i < tamanho; i++) {
            st.append("0");
            //st.append(" ");
        }
        st.append(var);

        return st.toString();
    }

    public List<String> principal() {
        List<String> lista = new ArrayList<String>();
        lista.add("..Selecione..");
        lista.add("SIM");
        lista.add("NAO");

        return lista;
    }

    public String timestampToString(Timestamp t) {
        // t.toString() should be: "yyyy-mm-dd hh:mm:ss.nanoseconds"
        if (t == null) {
            //t = new Timestamp(new Date().getTime());
            return "";
        }
        String tOld = t.toString();

        if (tOld.length() < 21) {
            return "";
        }

        String tNew
                = tOld.substring(8, 10) + "/"
                + tOld.substring(5, 7) + "/"
                + tOld.substring(0, 4) + " "
                + tOld.substring(11, 13) + ":"
                + tOld.substring(14, 16) + ":"
                + tOld.substring(17, 19);

        return tNew;
    }

    public String timestampToStringSeNuloRetornaBranco(Timestamp t) {
        // t.toString() should be: "yyyy-mm-dd hh:mm:ss.nanoseconds"
        if (t == null) {
            return "";
        }
        String tOld = t.toString();

        if (tOld.length() < 21) {
            return "";
        }

        String tNew
                = tOld.substring(8, 10) + "/"
                + tOld.substring(5, 7) + "/"
                + tOld.substring(0, 4) + " "
                + tOld.substring(11, 13) + ":"
                + tOld.substring(14, 16) + ":"
                + tOld.substring(17, 19);

        return tNew;
    }

    public String timestampToStringAmericano(Timestamp t) {
        // t.toString() should be: "yyyy-mm-dd hh:mm:ss.nanoseconds"
        if (t == null) {
            t = new Timestamp(new Date().getTime());
        }
        String tNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t);
        return tNew;
    }

    public String timestampToStringBrasil(Timestamp t) {
        // t.toString() should be: "yyyy-mm-dd hh:mm:ss.nanoseconds"
        if (t == null) {
            t = new Timestamp(new Date().getTime());
        }
        String tNew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        return tNew;
    }
    
    public String DataStringBrasil(Date t) {
        // t.toString() should be: "yyyy-mm-dd hh:mm:ss.nanoseconds"
        if (t == null) {
            t = new Timestamp(new Date().getTime());
        }
        String tNew = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        return tNew;
    }

    public String dateParaStringAmericana2(Date t) {
        // t.toString() should be: "yyyy-mm-dd"
        if (t == null) {
            return "";
        }
        Calendar c = Calendar.getInstance();
        c.setTime(t);

        String tNew = String.valueOf(c.get(Calendar.YEAR)) + "-"
                + String.valueOf((c.get(Calendar.MONTH)) + 1) + "-"
                + String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        return tNew;
    }

    public Timestamp stringParaTimestamp(String dtString) throws ApplicationException {
        if (dtString == null || dtString.trim().equals("")) {
            return null;
        }

        Date dt = new Date();

        try {
            dt = formatoDataHota.parse(dtString);
        } catch (ParseException ex) {
            throw new ApplicationException(ex.getMessage() + " Data Invalida");
        }

        Timestamp tp = new Timestamp(dt.getTime());

        return tp;

    }

    public int horaEntreDoisTimestamp(Timestamp inicio, Timestamp fim) throws ApplicationException {
        if (inicio == null || fim == null) {
            return 0;
        }
        if (fim.getTime() < inicio.getTime()) {
            return 0;
        }

        long resultado = fim.getTime() - inicio.getTime();
        long conversaoSegundos = TimeUnit.MILLISECONDS.toSeconds(resultado);

        int hr = (int) (conversaoSegundos / 3600);

        return hr;

    }

    public String deixarSomenteNumero(String c) {
        String resultado = c.replaceAll("\\D", ""); //Troca tudo que não for dígito por vazio
        return resultado;
    }

    public boolean soContemNumeros(String texto) {
        if (texto == null) {
            return false;
        }
        for (char letra : texto.toCharArray()) {
            if (letra < '0' || letra > '9') {
                return false;
            }
        }
        return true;

    }

    public String mesIntParaString(int mes) {
        if (mes < 1 || mes > 12) {
            return "";
        }
        switch (mes) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
            default:
                return "";
        }
    }

    public int mesStringParaInt(String mes) {
        if (mes.length() < 1) {
            return 0;
        }

        if (mes.equalsIgnoreCase("Janeiro")) {
            return 1;
        } else if (mes.equalsIgnoreCase("Fevereiro")) {
            return 2;
        } else if (mes.equalsIgnoreCase("Marco")) {
            return 3;
        } else if (mes.equalsIgnoreCase("Abril")) {
            return 4;
        } else if (mes.equalsIgnoreCase("Maio")) {
            return 5;
        } else if (mes.equalsIgnoreCase("Junho")) {
            return 6;
        } else if (mes.equalsIgnoreCase("Julho")) {
            return 7;
        } else if (mes.equalsIgnoreCase("Agosto")) {
            return 8;
        } else if (mes.equalsIgnoreCase("Setembro")) {
            return 9;
        } else if (mes.equalsIgnoreCase("Outubro")) {
            return 10;
        } else if (mes.equalsIgnoreCase("Novembro")) {
            return 11;
        } else if (mes.equalsIgnoreCase("Dezembro")) {
            return 12;
        } else {
            return 0;
        }

    }

    public List<String> listaMeses() { //Portador de Necessidades Especiais
        List<String> lista = new ArrayList<String>();
        lista.add("Janeiro");
        lista.add("Fevereiro");
        lista.add("Marco");
        lista.add("Abril");
        lista.add("Maio");
        lista.add("Junho");
        lista.add("Julho");
        lista.add("Agosto");
        lista.add("Setembro");
        lista.add("Outubro");
        lista.add("Novembro");
        lista.add("Dezembro");
        return lista;
    }

    public List<Integer> listaMesesInteiro() { //Portador de Necessidades Especiais
        List<Integer> lista = new ArrayList<Integer>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(6);
        lista.add(7);
        lista.add(8);
        lista.add(9);
        lista.add(10);
        lista.add(11);
        lista.add(12);

        return lista;
    }

    public int calculaIdade(java.util.Date dataNasc) {
        if (dataNasc != null) {
            Calendar dateOfBirth = new GregorianCalendar();
            dateOfBirth.setTime(dataNasc); // Cria um objeto calendar com a data atual
            Calendar today = Calendar.getInstance(); // Obtém a idade baseado no ano
            int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
            dateOfBirth.add(Calendar.YEAR, age); //se a data de hoje é antes da data de Nascimento, então diminui 1(um)
            if (today.before(dateOfBirth)) {
                age--;
            }
            return age;
        } else {
            return 0;
        }
    }

    public String retiraEspacosDuplicados(String text) {
        String string = text.trim();
        while (string.contains("  ")) {
            string = string.replaceAll("  ", " ");
        }
        return string;
    }

    public int digitoModulo_10(String bloco) {

        int d1 = 0;
        int d2 = 0;
        int d3 = 0;
        String rs = "";

        int soma = 0;
        int resultado = 0;
        int resto = 0;
        int digito = 0;

        int f = 0;
        int g = 0;

        for (int i = bloco.length(); i > 0; i--) {

            d1 = Integer.parseInt(bloco.substring(i - 1, i));

            f = g % 2;
            if (f == 0) {
                d1 = d1 * 2;
            } else {
                d1 = d1 * 1;
            }
            rs = String.valueOf(d1);
            if (rs.length() > 1) {
                d2 = Integer.parseInt(rs.substring(0, 1));
                d3 = Integer.parseInt(rs.substring(1, 2));
                d1 = d3 + d2;
            }
            soma += d1;

            g += 1;
        }

        resultado = soma / 10;
        resto = soma - (resultado * 10);
        digito = 10 - resto;

        if (digito == 10) {
            digito = 0;
        }

        return digito;
    }

    public int digitoModulo_11(String bloco) {

        int d1 = 0;
        int d2 = 0;
        int soma = 0;
        int resultado = 0;
        int resto = 0;
        int digito = 0;
        int g = 2;

        for (int i = bloco.length(); i > 0; i--) {

            d1 = Integer.parseInt(bloco.substring(i - 1, i));
            d2 = d1;
            if (g > 9) {
                g = 2;
            }

            d1 = d1 * g;

            soma += d1;
            g += 1;
        }
        resultado = soma / 11;
        resto = resultado * 11;
        resto = soma - resto;
        digito = 11 - resto;

        if (digito > 9 || digito == 0 || digito == 1) {
            digito = 1;
        }

        return digito;
    }

    public int digitoModulo_11_admite_zero(String bloco) {

        int d1 = 0;
        int d2 = 0;
        int soma = 0;
        int resultado = 0;
        int resto = 0;
        int digito = 0;
        int g = 2;

        for (int i = bloco.length(); i > 0; i--) {

            d1 = Integer.parseInt(bloco.substring(i - 1, i));
            d2 = d1;
            if (g > 9) {
                g = 2;
            }

            d1 = d1 * g;

            soma += d1;
            g += 1;
        }

        resultado = soma / 11;
        resto = resultado * 11;
        resto = soma - resto;
        digito = 11 - resto;

        if (digito > 9 || digito == 1) {
            digito = 1;
        }

        return digito;
    }

    public String converteValor(double valor) {
        String valor1 = null;
        StringBuilder valor2 = new StringBuilder();

        valor1 = doubleParaStringSemPontoDeMilhar(valor);
        if (valor1.length() > 3) {
            valor2.append(valor1.substring(0, valor1.length() - 3));
            valor2.append(valor1.substring(valor1.length() - 2, valor1.length()));
        } else {
            valor2.append(valor1.substring(1, valor1.length()));
        }
        valor1 = valor2.toString();
        valor2.delete(0, valor2.length());

        if (valor1.length() < 10) {
            for (int i = 0; i < (10 - valor1.length()); i++) {
            }
            valor2.append("0");
            valor2.append(valor1);
        }

        return valor2.toString();
    }

    private String formatar(String valor, String mascara) {

        String dado = "";
        // remove caracteres nao numericos
        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (Character.isDigit(c)) {
                dado += c;
            }
        }

        int indMascara = mascara.length();
        int indCampo = dado.length();

        for (; indCampo > 0 && indMascara > 0;) {
            if (mascara.charAt(--indMascara) == '#') {
                indCampo--;
            }
        }

        String saida = "";
        for (; indMascara < mascara.length(); indMascara++) {
            saida += ((mascara.charAt(indMascara) == '#') ? dado.charAt(indCampo++) : mascara.charAt(indMascara));
        }
        return saida;
    }

    public String mascaraCpf(String cpf) {
        while (cpf.length() < 11) {
            cpf = "0" + cpf;
        }
        return formatar(cpf, "###.###.###-##");
    }

    public String mascaraCnae(String cnae) {
        return formatar(cnae, "##.##-#-##");
    }

    public String mascaraProtocolo(String protocolo) {
        return formatar(protocolo, "####.#####.##");
    }
    public String mascaraProtocolo2(String protocolo) {
        return formatar(protocolo, "####.########");
    }

    public String mascaraCnpj(String cnpj) {
        while (cnpj.length() < 14) {
            cnpj = "0" + cnpj;
        }
        return formatar(cnpj, "##.###.###/####-##");
    }
    public String mascaraCpfOuCnpj(String cpfCpnj) {
        if(cpfCpnj.length() == 11){
            return formatar(cpfCpnj, "###.###.###-##");
        }else if(cpfCpnj.length() == 14){
            return formatar(cpfCpnj, "##.###.###/####-##");
        }else{
            return cpfCpnj;
        }
    }

    public Date SomarSubtrairDiaNaData(Date hoje, int qtd) {
        // para subtrair basta passar valor negativo no parametro QTD = -3, -2
        if (hoje == null) {
            return null;
        }
        Date nova_data = new Date(hoje.getTime() + ((1000 * 24 * 60 * 60) * qtd));

        return nova_data;
    }

}

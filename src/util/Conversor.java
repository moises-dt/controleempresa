package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author MDT
 */
public class Conversor {

    public static double converteHoraMinutos(double hora, double minuto) {
        double minutosTrabalhado = (hora * 60) + minuto;
        return minutosTrabalhado;
    }

    //Verificar a se a funcionalidade está ok
    public static void verificacaoHorario(long horaAtual) {
        SimpleDateFormat sdfConvert = new SimpleDateFormat("HH:mm");
        Date hora = null;
        try {
            hora = sdfConvert.parse("00:22");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(hora);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long tempAgora = gc.getTimeInMillis();
        System.out.println(sdf.format(gc.getTime()));
        System.out.println(tempAgora);

        gc.add(Calendar.MINUTE, 10);
        String minutosDepois = sdf.format(gc.getTime());
        long tempDepois = gc.getTimeInMillis();
        System.out.println(minutosDepois);
        System.out.println(tempDepois);

        gc.add(Calendar.MINUTE, 2 * -10);
        String minutosAntes = sdf.format(gc.getTime());
        long tempAntes = gc.getTimeInMillis();
        System.out.println(minutosAntes);
        System.out.println(tempAntes);

        if ((horaAtual > tempAntes) && (horaAtual < tempDepois)) {
            System.out.println("Não pode fazer tarefa!");
        } else {
            System.out.println("Pode fazer tarefa!");
        }
    }

    public static Date localdatetoDate(LocalDate datacadastro) throws ParseException {
        String localdate = datacadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date saida = formatador.parse(localdate);
        return saida;
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

}

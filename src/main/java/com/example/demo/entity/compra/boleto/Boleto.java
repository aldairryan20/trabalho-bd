package com.example.demo.entity.compra.boleto;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.example.demo.dao.BoletoDAO;

public class Boleto {
    private int id;
    private double valor;
    private Date dataVencimento;
    private Date dataGeracao;
    private String codigoBarras;
    private int tipoBoletoId;
    private int faturaCartaoId;
    
    private boolean isPago;

    public Boleto() {
        this.setPago(false);
    }

    public static Boleto gerarBoleto(double valor) {
        var boleto = new Boleto();
        Calendar calendar = Calendar.getInstance();
        var codigoBarras = generateEAN13();
        var id = gerarId();
        var tipoBoleto = new TipoBoleto();

        tipoBoleto.setId(id);
        var ids = BoletoDAO.ids;
        ids.add(id);
        
        tipoBoleto.setDescricao("descricao");

        boleto.setCodigoBarras(codigoBarras);
        boleto.setDataGeracao(calendar.getTime());

        calendar.add(Calendar.MONTH, 1);
        boleto.setDataVencimento(calendar.getTime());

        boleto.setFaturaCartaoId(id);
        boleto.setId(id);
        boleto.setTipoBoletoId(id);

        boleto.setValor(valor);
        return boleto;
    }
    public static int gerarId() {
        var id = new Random().nextInt(Integer.MAX_VALUE);
        var ids = BoletoDAO.ids;
        if (ids.contains(id)){
            return gerarId();
        }
        return id;
    }

    public static void pagarBoleto(Boleto boleto, String codigoBarras) {
        if (boleto.isPago()) {
            System.out.println("Este boleto já foi pago.");
            return;
        }

        if (boleto.getCodigoBarras().equals(codigoBarras)) {
            boleto.setPago(true);
            System.out.println("Boleto pago com sucesso.");
        } else {
            System.out.println("Código de barras inválido.");
        }
    }

    public static String generateEAN13() {
        Random random = new Random();
        StringBuilder codigoBarras = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            codigoBarras.append(random.nextInt(10));
        }

        int checkDigit = calculateCheckDigit(codigoBarras.toString());
        codigoBarras.append(checkDigit);

        return codigoBarras.toString();
    }

    public static int calculateCheckDigit(String codigo) {
        int sum = 0;
        for (int i = 0; i < codigo.length(); i++) {
            int digit = Character.getNumericValue(codigo.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        return (10 - (sum % 10)) % 10;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataVencimento() {
        return this.dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataGeracao() {
        return this.dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getCodigoBarras() {
        return this.codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getTipoBoletoId() {
        return this.tipoBoletoId;
    }

    public void setTipoBoletoId(int tipoBoletoId) {
        this.tipoBoletoId = tipoBoletoId;
    }

    public int getFaturaCartaoId() {
        return this.faturaCartaoId;
    }

    public void setFaturaCartaoId(int faturaCartaoId) {
        this.faturaCartaoId = faturaCartaoId;
    }
    public boolean isPago() {
        return this.isPago;
    }

    public void setPago(boolean isPago) {
        this.isPago = isPago;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", valor='" + getValor() + "'" +
            ", dataVencimento='" + getDataVencimento() + "'" +
            ", dataGeracao='" + getDataGeracao() + "'" +
            ", codigoBarras='" + getCodigoBarras() + "'" +
            ", tipoBoletoId='" + getTipoBoletoId() + "'" +
            ", faturaCartaoId='" + getFaturaCartaoId() + "'" +
            "}";
    }
}

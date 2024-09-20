package com.example.demo.entity.compra.boleto;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.example.demo.interfaces.EntityFactory;

@Component
public class BoletoFactory implements EntityFactory<Boleto> {

    @Override
    public Boleto createFromResultSet(ResultSet rs) throws SQLException {
        var boleto = new Boleto();

        int id = rs.getInt("id");
        double valor = rs.getDouble("valor");
        Date dataVencimento = rs.getDate("dt_vencimento");
        Date dataGeracao = rs.getDate("dt_geracao");
        String codigoBarras = rs.getString("codigo_barras");
        int tipoBoletoId = rs.getInt("tipo_boleto_id");
        int faturaCartaoId = rs.getInt("fatura_cartao_id");

        boleto.setId(id);
        boleto.setValor(valor);
        boleto.setDataVencimento(dataVencimento);
        boleto.setDataGeracao(dataGeracao);
        boleto.setCodigoBarras(codigoBarras);
        boleto.setTipoBoletoId(tipoBoletoId);
        boleto.setFaturaCartaoId(faturaCartaoId);
        
        return boleto;
    }
    
}

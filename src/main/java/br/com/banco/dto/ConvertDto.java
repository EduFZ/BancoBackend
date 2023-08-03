package br.com.banco.dto;

import br.com.banco.domain.Transferencia;

public class ConvertDto {

    public TransferenciaDto convertToDto(Transferencia transferencia){
        TransferenciaDto transferenciaDto = new TransferenciaDto();
        transferenciaDto.setId(transferencia.getId());
        transferenciaDto.setNomeOperador(transferencia.getNomeOperadorTransacao());
        transferenciaDto.setValor(transferencia.getValor());
        transferenciaDto.setTipo(transferencia.getTipo());
        transferenciaDto.setDataTransferencia(transferencia.getDataTransferencia());
        return transferenciaDto;
    }

}

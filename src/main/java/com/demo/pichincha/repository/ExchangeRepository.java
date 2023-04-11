package com.demo.pichincha.repository;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.demo.pichincha.dto.TypeExchangeDto;
import com.demo.pichincha.model.Currency;

import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ExchangeRepository {

	private ConnectionFactory connectionFactory;

	public ExchangeRepository(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public Flux<Currency> getAllCurrency() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Mono<TypeExchangeDto> getByCointExchange(String one, String two ) {		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  TE.COINT_ONE, CU_ONE.COINT AS \"DESC_ONE\", ");
		sql.append("TE.COINT_TWO, CU_TWO .COINT AS \"DESC_TWO\",  TE.BUY, TE.SALE FROM TYPE_EXCHANGE TE ");
		sql.append("INNER JOIN CURRENCY CU_ONE ON CU_ONE.ID = TE.COINT_ONE ");
		sql.append("INNER JOIN CURRENCY CU_TWO ON CU_TWO.ID = TE.COINT_TWO ");		
		sql.append("WHERE TE.COINT_ONE =$1 ");
		sql.append("AND TE.COINT_TWO =$2 ");		
		return Mono.from(connectionFactory.create())
		.flatMapMany(connection -> connection
				.createStatement(sql.toString())
				.bind("$1", Integer.parseInt(one))
				.bind("$2", Integer.parseInt(two))
				.execute())
		.flatMap(result -> result
				.map((row, rowMetadata) ->
				new TypeExchangeDto(
						row.get("coint_one", Integer.class),
						row.get("desc_one", String.class),
						row.get("coint_two", Integer.class),
						row.get("desc_two", String.class),
						row.get("buy", BigDecimal.class),
						row.get("sale", BigDecimal.class))))		
		.next();
	}
	

	public Mono<String> updateByCointExchange(TypeExchangeDto enchangeDto) {
		StringBuffer sql = new StringBuffer();
		sql.append("Update TYPE_EXCHANGE set buy=$1, sale=$2 where coint_one = $3 and coint_two = $4  ");
		return Mono.from(connectionFactory.create())
		.flatMapMany(connection -> connection
				.createStatement(sql.toString())
				.bind("$1", enchangeDto.getBuy())
				.bind("$2", enchangeDto.getSale())
				.bind("$3", enchangeDto.getCoint_one())
				.bind("$4", enchangeDto.getCoint_two())
				.execute())
		.flatMap(result -> result
				.map((row, rowMetadata) ->
				new String()))		
		.next();				
	}
	
	
	public Mono<String> insertAuditExchange(TypeExchangeDto enchangeDto) {
		System.out.println("Entra por aqui" + enchangeDto);
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO AUDIT_EXCHANGE "
				+ "(COINT_ONE, COINT_TWO, BUY, SALE, DATE_AUDIT)"
				+ "VALUES ("
				+ "$1,$2,$3,$4,now())");
		System.out.println("El SQL " + sql);
		return Mono.from(connectionFactory.create())
		.flatMapMany(connection -> connection
				.createStatement(sql.toString())				
				.bind("$1", enchangeDto.getCoint_one())
				.bind("$2", enchangeDto.getCoint_two())
				.bind("$3", enchangeDto.getBuy())
				.bind("$4", enchangeDto.getSale())
				.execute())
		.flatMap(result -> result
				.map((row, rowMetadata) ->
				new String()))		
		.next();				
	}

}

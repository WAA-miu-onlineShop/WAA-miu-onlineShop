package com.miu.waa.groupbravo.onlineshop.domain;

import com.miu.waa.groupbravo.onlineshop.repository.SequenceNumberRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SEQUENCE_NUMBER",uniqueConstraints = {
		@UniqueConstraint(columnNames = { "TYPE" }) })
@NamedQueries({
		@NamedQuery(name = SequenceNumberRepository.QUERY_NAME.findBySequenceType, query = SequenceNumberRepository.QUERY.findBySequenceType) })
public class SequenceNumber  extends DomainClass{

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "SEQUENCE", nullable = false)
	private Long sequence; 

 	@Column(name="TYPE",nullable = false)
 	@Enumerated(EnumType.STRING)
	private ESequenceType sequenceType;
	

	public Long getSequence() {
		return sequence;
	}
	

	public void setSequence(final Long sequence) {
		this.sequence = sequence;
	}
	

	public ESequenceType getSequenceType() {
		return sequenceType;
	}

	public void setSequenceType(final ESequenceType type) {
		this.sequenceType = type;
	}
 	
 	

}

package com.iu.JaWa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CurrentStockId implements Serializable{
	private static final long serialVersionUID = 2794969430033224062L;

	private int articleNumber;
	
	private LocalDate mhd;
	
	public CurrentStockId() {
		
	}

	public CurrentStockId(int articleNumber, LocalDate mhd) {
		super();
		this.articleNumber = articleNumber;
		this.mhd = mhd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mhd, articleNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrentStockId other = (CurrentStockId) obj;
		return Objects.equals(mhd, other.mhd) && Objects.equals(articleNumber, other.articleNumber);
	}
}

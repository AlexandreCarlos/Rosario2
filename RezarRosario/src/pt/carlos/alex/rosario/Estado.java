/*
 * Copyright (C) 2012 Alexandre Carlos 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Estado da aplicação
 */
package pt.carlos.alex.rosario;

/**
 * @author alexandre
 *
 */
public class Estado {

	private int diaSemana;
	private int misterio;
	private int pagina;
	private Boolean dualPage;
	/**
	 * Construtor de defeito
	 * Inicializa os atributos com valores incorretos (int = -1)
	 */
	public Estado() {
		this.setDiaSemana(-1);
		this.setMisterio(-1);
		this.setPagina(-1);
		this.setDualPage(false);
	}
  /**
   * Construtor com parâmetros de inicialização dos atributos
   *
   * @Param int diaSemana o dia da semana a assignar 
   * @Param int misterio o índex do mistério selecionado
   * @Param int pagina o índex da página selecionada
   * @Param Boolean dualPage o indicador se o layout tem 2 fragmentos visíveis 
   */
	public Estado(final int pDiaSemana, final int pMisterio, final int pPagina, final Boolean pDualPage) {
		this.setDiaSemana(pDiaSemana);
		this.setMisterio(pMisterio);
		this.setPagina(pPagina);
		this.setDualPage(pDualPage);
	}

	/**
	 * @return o dia da semana
	 */
	public int getDiaSemana() {
		return diaSemana;
	}

	/**
	 * @param int diaSemana o dia da semana a assignar 
	 */
	public void setDiaSemana(final int mDiaSemana) {
		this.diaSemana = mDiaSemana;
	}

	/**
	 * @return o índex do mistério selecionado
	 */
	public int getMisterio() {
		return misterio;
	}

	/**
	 * @param misterio o índex do mistério, a assignar
	 */
	public void setMisterio(final int mMisterio) {
		this.misterio = mMisterio;
	}

	/**
	 * @return o índex da página selecionada
	 */
	public int getPagina() {
		return pagina;
	}

	/**
	 * @param pagina o índex da página selecionada, a assignar
	 */
	public void setPagina(final int mPagina) {
		this.pagina = mPagina;
	}

	/**
	 * @return true se os 2 fragmentos estão visíveis
	 */
	public Boolean isDualPage() {
		return dualPage;
	}

	/**
	 * @param Boolean dualPage Assigna o estado da visibilidade dos 2 fragmentos
	 */
	public void setDualPage(final Boolean mDualPage) {
		this.dualPage = mDualPage;
	}
	
	/**
	 * @return String representation of Estado
	 */
	@Override
	public String toString() {
		return "Estado [diaSemana=" + diaSemana + ", misterio=" + misterio
				+ ", pagina=" + pagina + ", dualPage=" + dualPage + "]";
	}
}

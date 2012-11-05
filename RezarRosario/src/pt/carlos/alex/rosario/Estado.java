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

	private int mDiaSemana;
	private int mMisterio;
	private int mPagina;
	private Boolean mDualPage;
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
	public Estado(int diaSemana, int misterio, int pagina, Boolean dualPage) {
		this.setDiaSemana(diaSemana);
		this.setMisterio(misterio);
		this.setPagina(pagina);
		this.setDualPage(dualPage);
	}

	/**
	 * @return o dia da semana
	 */
	public int getDiaSemana() {
		return mDiaSemana;
	}

	/**
	 * @param int mDiaSemana o dia da semana a assignar 
	 */
	public void setDiaSemana(int mDiaSemana) {
		this.mDiaSemana = mDiaSemana;
	}

	/**
	 * @return o índex do mistério selecionado
	 */
	public int getMisterio() {
		return mMisterio;
	}

	/**
	 * @param mMisterio o índex do mistério, a assignar
	 */
	public void setMisterio(int mMisterio) {
		this.mMisterio = mMisterio;
	}

	/**
	 * @return o índex da página selecionada
	 */
	public int getPagina() {
		return mPagina;
	}

	/**
	 * @param mPagina o índex da página selecionada, a assignar
	 */
	public void setPagina(int mPagina) {
		this.mPagina = mPagina;
	}

	/**
	 * @return true se os 2 fragmentos estão visíveis
	 */
	public Boolean isDualPage() {
		return mDualPage;
	}

	/**
	 * @param Boolean mDualPage Assigna o estado da visibilidade dos 2 fragmentos
	 */
	public void setDualPage(Boolean mDualPage) {
		this.mDualPage = mDualPage;
	}
	
	/**
	 * @return String representation of Estado
	 */
	@Override
	public String toString() {
		return "Estado [mDiaSemana=" + mDiaSemana + ", mMisterio=" + mMisterio
				+ ", mPagina=" + mPagina + ", mDualPage=" + mDualPage + "]";
	}
}

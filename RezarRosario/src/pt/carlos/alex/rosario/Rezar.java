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
 * Evento Rezar acionado quando se muda de oração, mistério ou configuração
 */
package pt.carlos.alex.rosario;

/**
 * @author alexandre
 * 
 */
public class Rezar {
	protected int diaSemana;
	protected int misterio;
	protected int pagina;

/**
 * Construtor por defeito inibido
 */
	@SuppressWarnings(value = "UnusedDeclaration")
    private Rezar() {
	}

/**
 * Construtor com os parâmetros de inicialização dos atributos
 *
 * @Param int diaSemana inicializa o dia da semana
 * @Param int mistério inicializa o index do mistério selecionado
 * @Param int pagina inicializa o índex da página selecionada
 */
	public Rezar(final int pDiaSemana, final int pmisterio, final int ppagina) {
		this.diaSemana = pDiaSemana;
		this.misterio = pmisterio;
		this.pagina = ppagina;
	}

	/**
	 * @return O dia da semana
	 */
    @SuppressWarnings(value = "UnusedDeclaration")
	public int getDiaSemana() {
		return diaSemana;
	}

	/**
	 * @param int diaSemana
	 *            Assigna o Dia da semana
	 */
    @SuppressWarnings(value = "UnusedDeclaration")
	public void setDiaSemana(final int pDiaSemana) {
		this.diaSemana = pDiaSemana;
	}

	/**
	 * @return O índex do Mistério selecionado
	 */
    @SuppressWarnings(value = "UnusedDeclaration")
	public int getMisterio() {
		return misterio;
	}

	/**
	 * @param int misterio
	 *            Assigna o índex do Mistério selecionado
	 */
    @SuppressWarnings(value = "UnusedDeclaration")
	public void setMisterio(final int pMisterio) {
		this.misterio = pMisterio;
	}
	
	/**
	 * @return O índex da Página selecionada
	 */
    @SuppressWarnings(value = "UnusedDeclaration")
	public int getPagina() {
		return pagina;
	}
	
	/**
	 * @param int pagina
	 *            Assigna o índex da Página selecionada
	 */
    @SuppressWarnings(value = "UnusedDeclaration")
	public void setPagina(final int pPagina) {
		this.pagina = pPagina;
	}
	
	/**
	 * @return String representation of Rezar
	 */
	@Override
	public String toString() {
		return "Rezar [mDiaSemana=" + diaSemana + ", misterio=" + misterio
				+ ", pagina=" + pagina + "]";
	}

}

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
	protected int dia_semana;
	protected int misterio;
	protected int pagina;

/**
 * Construtor por defeito
 * Construtor que inicializa a classe com os atributos a zero
 */
	public Rezar() {
		this.dia_semana = 0;
		this.misterio = 0;
		this.pagina = 0;
	}

/**
 * Construtor com os parâmetros de inicialização dos atributos
 *
 * @Param int dia_semana inicializa o dia da semana
 * @Param int mistério inicializa o index do mistério selecionado
 * @Param int pagina inicializa o índex da página selecionada
 */
	public Rezar(int dia_semana, int mistério, int pagina) {
		this.dia_semana = dia_semana;
		this.misterio = mistério;
		this.pagina = pagina;
	}

	/**
	 * @return O dia da semana
	 */
	public int getDia_semana() {
		return dia_semana;
	}

	/**
	 * @param int dia_semana
	 *            Assigna o Dia da semana
	 */
	public void setDia_semana(int dia_semana) {
		this.dia_semana = dia_semana;
	}

	/**
	 * @return O índex do Mistério selecionado
	 */
	public int getMisterio() {
		return misterio;
	}

	/**
	 * @param int misterio
	 *            Assigna o índex do Mistério selecionado
	 */
	public void setMisterio(int misterio) {
		this.misterio = misterio;
	}
	
	/**
	 * @return O índex da Página selecionada
	 */
	public int getPagina() {
		return pagina;
	}
	
	/**
	 * @param int pagina
	 *            Assigna o índex da Página selecionada
	 */
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
	/**
	 * @return String representation of Rezar
	 */
	@Override
	public String toString() {
		return "Rezar [mDiaSemana=" + dia_semana + ", misterio=" + misterio
				+ ", pagina=" + pagina + "]";
	}

}

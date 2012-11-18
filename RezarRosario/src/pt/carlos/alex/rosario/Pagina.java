package pt.carlos.alex.rosario;
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
 * Evento de mudança de página.
 * @author alexandre
 *
 */
public class Pagina {

	private int mPagina;
	
	/**
	 * Construtor de defeito  inibido
	 */
	@SuppressWarnings(value = "UnusedDeclaration")
    private Pagina() {
	}
	
	/**
	 * Construtor com parâmetro de inicialização do atributo.
	 * @param pagina Índex da página selecionada
	 */
	public Pagina(final int pagina) {
		setPagina(pagina);
	}

	/**
	 * @return O índex da página selecionada
	 */
	public int getPagina() {
		return mPagina;
	}

	/**
	 * @param int mPagina o valor do índex da página selecionada
	 */
	public void setPagina(final int pPagina) {
		this.mPagina = pPagina;
	}
	
	/**
	 * @return String representation of Pagina
	 */
	@Override
	public String toString() {
		return "Pagina [mPagina=" + mPagina + "]";
	}
}

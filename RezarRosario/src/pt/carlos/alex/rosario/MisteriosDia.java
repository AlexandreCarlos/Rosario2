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
 * Fragmento com a visualização dos mistérios do dia. 
 */
package pt.carlos.alex.rosario;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * @author alexandre
 * 
 */
@EFragment
public class MisteriosDia extends SherlockListFragment {
	static final String TAG = "Rosário.misterios_dia";

	private int mIndexDiaSemana = -1;
	private EventBus mEventBus;
	private boolean mRegistado = false;
	private boolean isInit = false;

	@ViewById(R.id.oracoes)
	protected View mOracoes;

	/**
	 * Inicialização do event bus e respeito registo.
	 */
	@AfterInject
	void beforeCreate() {

		mEventBus = EventBus.getDefault();
//		mEventBus.register(this);
		registaBus();

		if (V.DEBUG) {
			Log.d(TAG, "Inicializa-mEventBus ");
		}

	}

	/**
	 * Criação da List View com os mistérios do dia.
	 */
	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setMisteriosList();

		isInit= true;
	}

	/**
	 * 
	 */
	private void setMisteriosList() {
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.misterio_list_itemlayout, R.id.item_misterio,
				Misterios.designacaoMisterios(mIndexDiaSemana)));
	}

	/**
	 * Tratamento do mistério selecionado.
	 * 
	 * Se o atividade tiver 2 fragmentos desencadeia o evento Rezar. Senão
	 * inicia a atividade ActivityMostraOracoes com o estado da aplicação como
	 * parâmetros 'Extra'.
	 * 
	 */
	@Override
	public void onListItemClick(final ListView l, final View v,
			final int position, final long id) {
		if (V.DEBUG) {
			Log.d(TAG, "List Item Click: " + position);
		}

		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		l.setItemChecked(position, true);

		mEventBus.post(new Rezar(mIndexDiaSemana, position, 0));

		if (V.DEBUG) {
			Log.d(TAG, "Rezar eventBus generated");
		}

		// Intent intent = new Intent(this.getActivity(),
		// ActivityMostraOracoes_.class);
		// intent.putExtra(V.DIA, mIndexDiaSemana);
		// intent.putExtra(V.MISTERIO, position);
		// intent.putExtra(V.PAGINA, 0);
		// Log.d(TAG,
		// "ActivityMostraOracoes_.class Intent generated & started");
		// startActivity(intent);

	}

	/**
	 * Método auxiliar de controlo do registo no event bus.
	 */
	private void registaBus() {
		if (!mRegistado) {
			mEventBus.register(this);
			mRegistado = true;
		}
	}

	/**
	 * Método auxiliar de controlo do desregisto no event bus.
	 */
	private void desregistaBus() {
		if (mRegistado) {
			mEventBus.unregister(this);
			mRegistado = false;
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		registaBus();

	}

	@Override
	public void onPause() {
		desregistaBus();
		super.onPause();
	}

	/**
	 * Trata os eventos de alteração de estado da aplicação.
	 */
	public void onEvent(final Estado event) {
		mIndexDiaSemana = event.getDiaSemana();
		
		if (isInit) {
			this.setMisteriosList();
		}
	}

}

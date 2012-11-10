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
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * Atividade invocada para mostrar as orações do mistério selecionado. 
 */
@EActivity(R.layout.activity_mostra_oracoes)
@OptionsMenu(R.menu.activity_main)
public class ActivityMostraOracoes extends SherlockFragmentActivity {

	static final String TAG = "Rosário.MostraOracoes Activity";

	@ViewById(R.id.dia_semana_p)
	protected TextView diaSemana;

	private EventBus eventBus;
//	private GregorianCalendar calendario;
	protected int indexDiaSemana = -1;
	protected int misterioSelected = 0;
	protected int paginaActual = 0;
	protected boolean dualPage = false;
	
   /**
    * Inicialização antes da criação da Vida View.
    *
    */
	@AfterInject
	void startup() {
//		calendario = (GregorianCalendar) GregorianCalendar.getInstance();
//
//		indexDiaSemana = calendario.get(Calendar.DAY_OF_WEEK);

		eventBus = EventBus.getDefault();

		indexDiaSemana = getIntent().getIntExtra(V.DIA, -1);
		misterioSelected = getIntent().getIntExtra(V.MISTERIO, -1);
		paginaActual = getIntent().getIntExtra(V.PAGINA, -1);
		
		if (V.DEBUG) {
			Log.d(TAG, "IntentExtra values -Dia:"+indexDiaSemana+"; Mistério:"+misterioSelected+"; Página:"+paginaActual);
			Log.d(TAG, "Dia Semana:"+V.DIA_SEMANA[indexDiaSemana]);
		}

		if (V.DEBUG) {
			Log.d(TAG, "mIndexDiaSemana: " + indexDiaSemana);
		}
	}

   /**
    * Inicialização depois da criação da View 
    */
	@AfterViews
	void init() {

		try {
//			eventBus.register(this);

//			mIndexDiaSemana = getIntent().getIntExtra(V.DIA, -1);
//			mMisterioSelected = getIntent().getIntExtra(V.MISTERIO, -1);
//			mPaginaActual = getIntent().getIntExtra(V.PAGINA, -1);
//			
//			if (V.DEBUG) {
//				Log.d(TAG, "IntentExtra values -Dia:"+mIndexDiaSemana+"; Mistério:"+mMisterioSelected+"; Página:"+mPaginaActual);
//				Log.d(TAG, "Dia Semana:"+V.DIA_SEMANA[mIndexDiaSemana]);
//			}

			diaSemana.setText(V.DIA_SEMANA[indexDiaSemana]);
			
			eventBus.post(new Estado(indexDiaSemana, misterioSelected, paginaActual, false));
			
		} catch (Exception e) {
			Log.e(TAG, "Erro no init() @AfterViews:", e);
		}

	}
	
	
}

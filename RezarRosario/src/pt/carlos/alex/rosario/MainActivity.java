/*
 * Copyright (c) 2012. Alexanndre Carlos.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package pt.carlos.alex.rosario;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;
import com.slidingmenu.lib.SlidingMenu;

import de.greenrobot.event.EventBus;

/**
 * 
 * Atividade principal que inicializa e controla os estados da aplicaçã.
 * 
 */
@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.activity_main)
public class MainActivity extends SherlockFragmentActivity implements
		OnNavigationListener {

	static final String TAG = "Rosário.MainActivity";

	@ViewById(R.id.dia_semana)
	protected TextView mDiaSemana;

	@ViewById(R.id.oracoes)
	protected View mOracoes;

	@ViewById(R.id.slidingmenulayout)
	protected SlidingMenu mSlindingMenu;

	private EventBus mEventBus;
	private GregorianCalendar mCalendario;
	protected int mIndexDiaSemana = -1;
	protected int mMisterioSelected = 0;
	protected int mPaginaActual = 0;
	private ActionBar ab;

    /**
	 * Inicialização antes da criação das views. Determina o dia da semana
	 * atual.
	 */
	@AfterInject
	void beforeCreate() {
		mCalendario = (GregorianCalendar) GregorianCalendar.getInstance();

		mIndexDiaSemana = mCalendario.get(Calendar.DAY_OF_WEEK);

		mEventBus = EventBus.getDefault();

		if (V.DEBUG) {
			Log.d(TAG, "mIndexDiaSemana: " + mIndexDiaSemana);
		}
	}

	/**
	 * Inicialização depois da criação das views. Determina se o layout inclui 2
	 * views. Notifica o novo estado da aplicação.
	 */
	@AfterViews
	void afterCreate() {

		mEventBus.register(this);

		ab = getSupportActionBar();

		ab.setDisplayShowTitleEnabled(false);
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        ArrayAdapter<CharSequence> weekDaysList;
        weekDaysList = ArrayAdapter.createFromResource(ab.getThemedContext(),
                R.array.week_days, R.layout.sherlock_spinner_item);
        weekDaysList.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
		
		ab.setListNavigationCallbacks(weekDaysList, this);

		this.escreveTitulo();

		if (mSlindingMenu != null) {
			ab.setDisplayHomeAsUpEnabled(true);
		}

		mEventBus.post(new Estado(mIndexDiaSemana, mMisterioSelected,
				mPaginaActual));

		if (V.DEBUG) {
			Log.d(TAG, "Triggered event Estado. Dia:" + mIndexDiaSemana
					+ "; Mistério:" + mMisterioSelected + "; Página:"
					+ mPaginaActual);
		}

	}

	/**
	 * Actualiza o texto da TextView dia_semana com o dia da semana e o mistério
	 * que se está a rezar.
	 */
	private void escreveTitulo() {

	String formattedString;
	
		if (this.isLandscape()) {
			formattedString = String.format("<html><head></head><body>%s - %s (%s)</body></html>", V.DIA_SEMANA[mIndexDiaSemana], 
				Misterios.designacaoMisterio(mIndexDiaSemana), Misterios.identificarMisterioDia(mMisterioSelected));
		} else {
			formattedString = String.format("<html><head></head><body>%s - %s <br />(%s)</body></html>", V.DIA_SEMANA[mIndexDiaSemana], 
				Misterios.designacaoMisterio(mIndexDiaSemana), Misterios.identificarMisterioDia(mMisterioSelected));
		}
	
		mDiaSemana.setText(Html.fromHtml(formattedString));

//	StringBuilder s;
//
//		s = new StringBuilder("<html><head></head><body>");
//		s.append(V.DIA_SEMANA[mIndexDiaSemana]);
		// s.append(" - ");
		// s.append(Misterios.designacaoMisterio(mIndexDiaSemana));

		// if (this.isLandscape()) {
			// s.append(" (");
		// } else {
			// s.append("<br />(");
		// }

		// s.append(Misterios.identificarMisterioDia(mIndexDiaSemana,
				// mMisterioSelected));
		// s.append(")</body></html>");

		// mDiaSemana.setText(Html.fromHtml(s.toString()));

		ab.setSelectedNavigationItem(mIndexDiaSemana - 1);
		
		if (this.mSlindingMenu != null) {
			if (this.mSlindingMenu.isBehindShowing()) {
				this.mSlindingMenu.showAbove();
			}
		}
	}

	/**
	 * Detecta a orientação do écran.
	 * 
	 * Utiliza métodos Deprecated, para garantir a retro compatibilidade com API
	 * Level < 13 (Honeycomb)
	 * 
	 * @return True se o écran estiver orientado em Landscape
	 */
	@SuppressWarnings("deprecation")
	private boolean isLandscape() {

		Display getOrient = getWindowManager().getDefaultDisplay();

		if (V.DEBUG) {
			Log.d(TAG, "Screen Size - Width: " + getOrient.getWidth()
					+ "; Heigth: " + getOrient.getHeight());
		}

		return !(getOrient.getWidth() < getOrient.getHeight());
	}

	/**
	 * Guarda o estado da aplicação.
	 */
	@Override
	protected void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt(V.MISTERIO, this.mMisterioSelected);
		outState.putInt(V.DIA, this.mIndexDiaSemana);
		outState.putInt(V.PAGINA, this.mPaginaActual);

		if (V.DEBUG) {
			Log.d(TAG, "onSaveInstanceState [MISTERIO]=" + mMisterioSelected
					+ "; [DIA]=" + mIndexDiaSemana + "; [PAGINA]="
					+ mPaginaActual);
		}

	}

	/**
	 * 
	 * Recupera o estado da aplicação e notifica as alterações.
	 * 
	 */
	@Override
	protected void onRestoreInstanceState(final Bundle inState) {
		super.onRestoreInstanceState(inState);

		int d = inState.getInt(V.DIA);
		int p = inState.getInt(V.MISTERIO);
		int g = inState.getInt(V.PAGINA);
		boolean alterado = false;

		if (V.DEBUG) {
			Log.d(TAG, "onRestoreInstanceState [MISTERIO]=" + p + "; [DIA]="
					+ d + "; [PAGINA]=" + g);
			Log.d(TAG, "MainActivityState [MISTERIO]=" + mMisterioSelected
					+ "; [DIA]=" + mIndexDiaSemana + "; [PAGINA]="
					+ mPaginaActual);
		}

		if (d > this.mIndexDiaSemana) {
			this.mIndexDiaSemana = d;
			alterado = true;
		}

		if (p != this.mMisterioSelected) {
			this.mMisterioSelected = p;
			alterado = true;
		}

		if (g != this.mPaginaActual) {
			this.mPaginaActual = g;
			alterado = true;
		}

		if (alterado) {

			if (V.DEBUG) {
				Log.d(TAG, "MainActivityState changed [MISTERIO]="
						+ mMisterioSelected + "; [DIA]=" + mIndexDiaSemana
						+ "; [PAGINA]=" + mPaginaActual);
				Log.d(TAG, " Rezar EventBus Generated");
			}

			mEventBus.post(new Rezar(mIndexDiaSemana, mMisterioSelected,
					mPaginaActual));
		}

	}

	/**
	 * Recebe as notificações de mudança de página e guarda no estado.
	 */
	@SuppressWarnings(value = "UnusedDeclaration")
    public void onEvent(final Pagina event) {

		if (V.DEBUG) {
			Log.d(TAG, "Evento (Integer) página recebido:" + event);
		}

		this.mPaginaActual = event.getPagina();

	}

	/**
	 * Recebe a notificação de mistério selecionado e guarda no estado.
	 * 
	 */
	@SuppressWarnings(value = "UnusedDeclaration")
    public void onEvent(final Rezar event) {

		if (V.DEBUG) {
			Log.d(TAG, "Evento Rezar recebido:" + event);
		}

		mIndexDiaSemana = event.diaSemana;
		mMisterioSelected = event.misterio;

		this.escreveTitulo();

	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mSlindingMenu.toggle();
			return true;
		case R.id.menu_data:
			 DialogFragment newFragment = new DialogoData();
			 newFragment.show(getSupportFragmentManager(), getResources().getString(R.string.dialog_menu_data));
			return true;
		case R.id.about:
			new AlertDialog.Builder(this).setTitle(R.string.about)
					.setMessage(Html.fromHtml(getString(R.string.about_msg)))
					.show();
			break;
		case R.id.licenses:
			new AlertDialog.Builder(this)
					.setTitle(R.string.licenses)
					.setMessage(
							Html.fromHtml(getString(R.string.apache_license)))
					.show();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);

	}

	@Override
	public boolean onNavigationItemSelected(final int itemPosition,
			final long itemId) {

		mIndexDiaSemana = itemPosition + 1;
		mMisterioSelected = 0;
		mPaginaActual = 0;

		if (V.DEBUG) {
			Log.d(TAG, "onNavigationItemSelected - itemPosition:"
					+ itemPosition + "; mIndexDiaSemana:" + mIndexDiaSemana);
		}

		this.escreveTitulo();

		mEventBus.post(new Estado(mIndexDiaSemana, mMisterioSelected,
				mPaginaActual));

		return true;
	}

}
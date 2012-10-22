package pt.carlos.alex.rosario;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.activity_main)
public class MainActivity extends SherlockFragmentActivity {

	final static String TAG = "Rosário";
	final static boolean DEBUG = true;
	final static String POSICAO = "Posicao";
	final static String DIA = "Dia";
	final static String PAGINA = "Pagina";
	final static String[] DIA_SEMANA = { "Que dia é este?", "Domingo",
			"2ª Feira", "3ª Feira", "4ª Feira", "5ª Feira", "6ª Feira",
			"Sábado" };

	@ViewById(R.id.dia_semana)
	protected TextView dia_semana;

	private EventBus eventBus;
	private GregorianCalendar calendario;
	protected int index_dia_semana = -1;
	protected int misterio_selected = 0;
	protected int pagina_actual = 0;

	@AfterInject
	void startup() {
		calendario = (GregorianCalendar) GregorianCalendar.getInstance();

		index_dia_semana = calendario.get(Calendar.DAY_OF_WEEK);

		eventBus = EventBus.getDefault();

		if (DEBUG) {
			Log.d(TAG, "index_dia_semana: " + index_dia_semana);
		}
	}

	@AfterViews
	void init() {

		try {
			eventBus.register(this);

			// oracao = Misterios.Oracoes_do_Misterio(index_dia_semana,
			// misterio_selected);
			//
			// pager.setAdapter(new OracoesPageAdapter(this, oracao));

			dia_semana.setText(DIA_SEMANA[index_dia_semana]);

		} catch (Exception e) {
			Log.e(TAG, "Erro no init() @AfterViews:", e);
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt(POSICAO, this.misterio_selected);
		outState.putInt(DIA, this.index_dia_semana);
		outState.putInt(PAGINA, this.pagina_actual);

		if (DEBUG) {
			Log.d(TAG, "onSaveInstanceState [POSICAO]=" + misterio_selected
					+ "; [DIA]=" + index_dia_semana+ "; [PAGINA]=" + pagina_actual);
		}

	}

	@Override
	protected void onRestoreInstanceState(Bundle inState) {
		super.onRestoreInstanceState(inState);

		int d = inState.getInt(DIA);
		int p = inState.getInt(POSICAO);
		int g = inState.getInt(PAGINA);
		boolean alterado = false;

		if (DEBUG) {
			Log.d(TAG, "onRestoreInstanceState [POSICAO]=" + p + "; [DIA]=" + d+ "; [PAGINA]=" + g);
			Log.d(TAG, "MainActivityState [POSICAO]=" + misterio_selected
					+ "; [DIA]=" + index_dia_semana+ "; [PAGINA]=" + pagina_actual);
		}

		if (d > this.index_dia_semana) {
			this.index_dia_semana = d;
			alterado = true;
		}

		if (p != this.misterio_selected) {
			this.misterio_selected = p;
			alterado = true;
		}

		if (g != this.pagina_actual) {
			this.pagina_actual = g;
			alterado = true;
		}

		if (alterado) {

			if (DEBUG) {
				Log.d(TAG, "MainActivityState changed [POSICAO]="
						+ misterio_selected + "; [DIA]=" + index_dia_semana
						+ "; [PAGINA]=" + pagina_actual);
			}
			
			eventBus.post(new Rezar(index_dia_semana, misterio_selected, pagina_actual));
		}

	}

	public void onEvent(Integer event) {

		if (DEBUG) {
			Log.d(TAG, "Evento  página recebido:" + event);
		}

		this.pagina_actual = event.intValue();

	}
	
	
}
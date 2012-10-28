package pt.carlos.alex.rosario;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

	final static String TAG = "Rosário.MainActivity";
//	final static boolean DEBUG = true;
//	final static String MISTERIO = "Misterio";
//	final static String DIA = "Dia";
//	final static String PAGINA = "Pagina";
//	final static String[] DIA_SEMANA = { "Que dia é este?", "Domingo",
//			"2ª Feira", "3ª Feira", "4ª Feira", "5ª Feira", "6ª Feira",
//			"Sábado" };

	@ViewById(R.id.dia_semana)
	protected TextView dia_semana;

	@ViewById(R.id.oracoes)
	protected View oracoes;

	private EventBus eventBus;
	private GregorianCalendar calendario;
	protected int index_dia_semana = -1;
	protected int misterio_selected = 0;
	protected int pagina_actual = 0;
	protected boolean mDualPage = false;

	@AfterInject
	void startup() {
		calendario = (GregorianCalendar) GregorianCalendar.getInstance();

		index_dia_semana = calendario.get(Calendar.DAY_OF_WEEK);

		eventBus = EventBus.getDefault();

		if (V.DEBUG) {
			Log.d(TAG, "index_dia_semana: " + index_dia_semana);
		}
	}

	@AfterViews
	void init() {

		try {
			eventBus.register(this);

			dia_semana.setText(V.DIA_SEMANA[index_dia_semana] + " - " + Misterios.designacaoMisterio(index_dia_semana));
			
			this.mDualPage = this.oracoes != null && this.oracoes.getVisibility() == View.VISIBLE;
			
			Log.i(TAG, "Dual Mode:"+this.mDualPage);

		} catch (Exception e) {
			Log.e(TAG, "Erro no init() @AfterViews:", e);
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt(V.MISTERIO, this.misterio_selected);
		outState.putInt(V.DIA, this.index_dia_semana);
		outState.putInt(V.PAGINA, this.pagina_actual);

		if (V.DEBUG) {
			Log.d(TAG, "onSaveInstanceState [MISTERIO]=" + misterio_selected
					+ "; [DIA]=" + index_dia_semana + "; [PAGINA]="
					+ pagina_actual);
		}

	}

	@Override
	protected void onRestoreInstanceState(Bundle inState) {
		super.onRestoreInstanceState(inState);

		int d = inState.getInt(V.DIA);
		int p = inState.getInt(V.MISTERIO);
		int g = inState.getInt(V.PAGINA);
		boolean alterado = false;

		if (V.DEBUG) {
			Log.d(TAG, "onRestoreInstanceState [MISTERIO]=" + p + "; [DIA]=" + d
					+ "; [PAGINA]=" + g);
			Log.d(TAG, "MainActivityState [MISTERIO]=" + misterio_selected
					+ "; [DIA]=" + index_dia_semana + "; [PAGINA]="
					+ pagina_actual);
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

			if (V.DEBUG) {
				Log.d(TAG, "MainActivityState changed [MISTERIO]="
						+ misterio_selected + "; [DIA]=" + index_dia_semana
						+ "; [PAGINA]=" + pagina_actual);
				Log.d(TAG, " Rezar EventBus Generated");
			}

			eventBus.post(new Rezar(index_dia_semana, misterio_selected,
					pagina_actual));
		}

	}

	public void onEvent(Integer event) {

		if (V.DEBUG) {
			Log.d(TAG, "Evento (Integer) página recebido:" + event);
		}

		this.pagina_actual = event.intValue();

	}

	public void onEvent(Rezar event) {

		if (V.DEBUG) {
			Log.d(TAG, "Evento Rezar recebido:" + event);
		}

		index_dia_semana = event.dia_semana;
		misterio_selected = event.misterio;

	}
}
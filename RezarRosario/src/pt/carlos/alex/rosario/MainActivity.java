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
	// final static boolean DEBUG = true;
	// final static String MISTERIO = "Misterio";
	// final static String DIA = "Dia";
	// final static String PAGINA = "Pagina";
	// final static String[] DIA_SEMANA = { "Que dia é este?", "Domingo",
	// "2ª Feira", "3ª Feira", "4ª Feira", "5ª Feira", "6ª Feira",
	// "Sábado" };

	@ViewById(R.id.dia_semana)
	protected TextView mDiaSemana;

	@ViewById(R.id.oracoes)
	protected View mOracoes;

	private EventBus mEventBus;
	private GregorianCalendar mCalendario;
	protected int mIndexDiaSemana = -1;
	protected int mMisterioSelected = 0;
	protected int mPaginaActual = 0;
	protected boolean mDualPage = false;

	@AfterInject
	void startup() {
		mCalendario = (GregorianCalendar) GregorianCalendar.getInstance();

		mIndexDiaSemana = mCalendario.get(Calendar.DAY_OF_WEEK);

		mEventBus = EventBus.getDefault();

		if (V.DEBUG) {
			Log.d(TAG, "mIndexDiaSemana: " + mIndexDiaSemana);
		}
	}

	@AfterViews
	void init() {

		// try {
		mEventBus.register(this);

		mDiaSemana.setText(V.DIA_SEMANA[mIndexDiaSemana] + " - "
				+ Misterios.designacaoMisterio(mIndexDiaSemana));

		this.mDualPage = this.mOracoes != null
				&& this.mOracoes.getVisibility() == View.VISIBLE;

		mEventBus.post(new Estado(mIndexDiaSemana, mMisterioSelected,
				mPaginaActual, mDualPage));

		if (V.DEBUG) {
			Log.d(TAG, "Triggered event Estado. Dia:" + mIndexDiaSemana
					+ "; Mistério:" + mMisterioSelected + "; Página:"
					+ mPaginaActual + "; DualPage:" + mDualPage);
		}

//		Log.i(TAG, "Dual Mode:" + this.mDualPage);

		// } catch (Exception e) {
		// Log.e(TAG, "Erro no init() @AfterViews:", e);
		// }

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
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

	@Override
	protected void onRestoreInstanceState(Bundle inState) {
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

	public void onEvent(Pagina event) {

		if (V.DEBUG) {
			Log.d(TAG, "Evento (Integer) página recebido:" + event);
		}

		this.mPaginaActual = event.getPagina();

	}

	public void onEvent(Rezar event) {

		if (V.DEBUG) {
			Log.d(TAG, "Evento Rezar recebido:" + event);
		}

		mIndexDiaSemana = event.dia_semana;
		mMisterioSelected = event.misterio;

	}
}
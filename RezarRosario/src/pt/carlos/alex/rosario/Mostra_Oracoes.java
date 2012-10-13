/**
 * Fragment que mostra as orações associadas a um mistério
 */
package pt.carlos.alex.rosario;

import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * @author alexandre
 * 
 */
@EFragment(R.layout.oracoes_layout)
public class Mostra_Oracoes extends SherlockFragment {

	private static final String TAG = "Mostra Orações";
	private static final boolean DEBUG = true;
	private EventBus eventBus;
	private boolean registado = false;
	
	@ViewById
	protected TextView oracoesText;

	@AfterInject
	void inicializa() {
		eventBus = EventBus.getDefault();
		registaBus();
		
		if (DEBUG) {
			Log.d(TAG, "inicializa");
		}
	}

	private void registaBus() {
		if (!registado) {
			eventBus.register(this);
			registado = true;
		}
	}
	
	private void desregistaBus() {
		if (registado) {
			eventBus.unregister(this);
			registado = false;
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


	void escreveOracoes(int dia_semana, int misterio) {

		if (misterio < 0) {
			return;
		}

		oracoesText.setText(Misterios.Obter_Misterio_do_Dia(dia_semana, misterio));
		
	}

	public void onEvent(Rezar event) {

		if (DEBUG) {
			Log.d(TAG, "Evento recebido:" + event);
		}

		escreveOracoes(event.dia_semana, event.misterio);
	}

}

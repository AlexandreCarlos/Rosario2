/**
 * Fragment que mostra as orações associadas a um mistério
 */
package pt.carlos.alex.rosario;

import java.util.List;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * @author alexandre
 * 
 */
@EFragment(R.layout.oracoes_page_layout)
public class Mostra_Oracoes extends SherlockFragment {

	private static final String TAG = "Mostra Orações";
	private static final boolean DEBUG = true;
	private EventBus eventBus;
	private boolean registado = false;
	
	@ViewById(R.id.pager)
	protected ViewPager pager;

	protected int index_dia_semana = -1;
	protected int misterio_selected = 0;
	protected List<String> oracao;
	
	@AfterInject
	void startUp() {
		eventBus = EventBus.getDefault();
		registaBus();
		
		MainActivity ma = (MainActivity)getActivity();
		index_dia_semana = ma.index_dia_semana;
		misterio_selected = ma.misterio_selected;
		ma = null;
		
		if (DEBUG) {
			Log.d(TAG, "inicializa-index_dia_semana:"+index_dia_semana+"; misterio_selected:"+misterio_selected);
		}
	}

	@AfterViews
	void init() {

		try {
			oracao = Misterios.Oracoes_do_Misterio(index_dia_semana, misterio_selected);

			pager.setAdapter(new OracoesPageAdapter(this, oracao));

		} catch (Exception e) {
			Log.e(TAG, "Erro no init() @AfterViews:", e);
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


//	void escreveOracoes(int dia_semana, int misterio) {
//
//		if (misterio < 0) {
//			return;
//		}
//
//		oracoesText.setText(Misterios.Obter_Misterio_do_Dia(dia_semana, misterio));
//		
//	}

	public void onEvent(Rezar event) {

		if (DEBUG) {
			Log.d(TAG, "Evento recebido:" + event);
		}

		index_dia_semana = event.dia_semana;
		misterio_selected = event.misterio;
		oracao.clear();
		oracao.addAll(Misterios.Oracoes_do_Misterio(index_dia_semana, misterio_selected))
		;
		pager.getAdapter().notifyDataSetChanged();

		this.pager.setCurrentItem(0);
	}

}

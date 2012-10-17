/**
 * 
 */
package pt.carlos.alex.rosario;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * @author alexandre
 *
 */
public class OracoesPageAdapter extends FragmentPagerAdapter {

	final static String TAG = "OracoesPageAdapter";
	final static boolean DEBUG = true;

	private String [] oracao;
//	private EventBus eventBus;
	
	public OracoesPageAdapter(FragmentManager mgr, int index_dia_da_semana, int misterio) {
	    super(mgr);
	    
//	    this.index_dia_da_semana = index_dia_da_semana;
	    if (DEBUG) {
	    	Log.d(TAG, "Constructor-index_dia_da_semana:"+index_dia_da_semana+"; misterio:"+misterio);
	    }
	    
//	    eventBus = EventBus.getDefault();
//	    eventBus.register(this);
	    
	    this.oracao= Misterios.Oracoes_do_Misterio(index_dia_da_semana, misterio);
	  }
	
	@Override
	public int getCount() {
		
		return this.oracao.length;
	}

	@Override
	public Fragment getItem(int position) {
		return(OracoesFragment.newInstance(oracao[position]));
	}

//	public void onEvent(Rezar event) {
//
//		if (DEBUG) {
//			Log.d(TAG, "Evento recebido:" + event);
//		}
//
//		this.oracao= Misterios.Oracoes_do_Misterio(event.dia_semana, event.misterio);
//		
//		super.notifyDataSetChanged();
//		
//		eventBus.post(TAG);
//	}
}

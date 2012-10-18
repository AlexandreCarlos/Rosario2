/**
 * 
 */
package pt.carlos.alex.rosario;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * @author alexandre
 *
 */
public class OracoesPageAdapter extends PagerAdapter {

	final static String TAG = "OracoesPageAdapter";
	final static boolean DEBUG = true;

	private List <String> oracao;
	private MainActivity ctx;

	
	public OracoesPageAdapter(MainActivity ctx, List <String> oracao) {
		
	    if (DEBUG) {
	    	Log.d(TAG, "Constructor-oracao:"+oracao.get(0));
	    }
	    
	    this.oracao = oracao;
	    this.ctx =ctx;
	    
	  }
	
	@Override
	public int getCount() {
		
		return this.oracao.size();
	}

    @Override
    public Object instantiateItem(View collection, int position) {
    	if (DEBUG){
			Log.d(TAG, "Object instantiateItem-position:"+position+"; "+oracao.get(position));
		}
    	
//    	  TextView view = new TextView(ctx);
//          view.setText(Html.fromHtml(oracao.get(position)));
//          ((ViewPager)collection).addView(view);
        LayoutInflater l = ctx.getLayoutInflater();  
    	View result = l.inflate(R.layout.oracoes_layout, (ViewPager)collection, false);
    	
		TextView oracoesText = (TextView) result.findViewById(R.id.oracoesText);
		oracoesText.setText(Html.fromHtml(oracao.get(position)));
		
		TextView contasDezena = (TextView) result.findViewById(R.id.textView1);
		contasDezena.setText("Oração "+(position+1));
		
		((ViewPager)collection).addView(result);  
		
        return result;

    }

    @Override
    public void destroyItem(View collection, int position, Object view) {
         ((ViewPager) collection).removeView((View) view);
    }

	@Override
	public int getItemPosition(Object object) {
	    
		if (DEBUG){
			Log.d(TAG, "getItemPosition-object:"+object.getClass());
		}
		
		// Para obrigar a refrescar as páginas da view sempre que se faz notifyDataSetChanged();
		return POSITION_NONE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;

	}

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public void startUpdate(View arg0) {
    }

    @Override
    public void finishUpdate(View arg0) {
    }

}

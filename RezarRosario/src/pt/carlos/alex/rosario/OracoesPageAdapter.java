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
 * Pager Adapter gerador das páginas com as orações associadas ao mistério selecionado. 
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

	static final String TAG = "Rosário.OracoesPageAdapter";
//	final static boolean DEBUG = true;

	private List <String> oracao;
	private MostraOracoes ctx;

	
	public OracoesPageAdapter(final MostraOracoes cntx, final List <String> oracoes) {
		
	    if (V.DEBUG) {
	    	Log.d(TAG, "Constructor");
	    }
	    
	    this.oracao = oracoes;
	    this.ctx =cntx;
	    
	  }
	
	@Override
	public int getCount() {
		
		return this.oracao.size();
	}

    @Override
    public Object instantiateItem(final View collection, final int position) {
    	if (V.DEBUG){
			Log.d(TAG, "Object instantiateItem-position:"+position);
		}
    	
        LayoutInflater l = ctx.getLayoutInflater(null);  
    	View result = l.inflate(R.layout.oracoes_layout, (ViewPager)collection, false);
    	
		TextView oracoesText = (TextView) result.findViewById(R.id.oracoesText);
		oracoesText.setText(Html.fromHtml(oracao.get(position)));
		
		
		((ViewPager)collection).addView(result);  
		
        return result;

    }

    @Override
    public void destroyItem(final View collection, final int position, final Object view) {
         ((ViewPager) collection).removeView((View) view);
    }

	@Override
	public int getItemPosition(final Object object) {
	    
		if (V.DEBUG){
			Log.d(TAG, "getItemPosition-object:"+object.getClass());
		}
		
		// Para obrigar a refrescar as páginas da view sempre que se faz notifyDataSetChanged();
		return POSITION_NONE;
	}

	@Override
	public boolean isViewFromObject(final View view, final Object object) {
		return view == object;

	}

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(final Parcelable arg0, final ClassLoader arg1) {
    }

    @Override
    public void startUpdate(final View arg0) {
    }

    @Override
    public void finishUpdate(final View arg0) {
    }

 
    
}

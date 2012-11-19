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

import android.app.Activity;
import android.content.res.Resources;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.api.Scope;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alexandre
 * Date: 18-11-2012
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
@EBean (scope = Scope.Singleton)
public class RosarioFatima implements Rosario{
    private List<String> oracao;
    private List<Integer> coresContas;
    private Resources resources;

    @RootContext
    Activity activity;


    public RosarioFatima() {
    }

    @AfterInject
    protected void afterCreate() {
        this.resources = this.activity.getResources();
    }
    @Override
    public List<String> obterDezena(int pDiaSemana, int pMisterio) {
        return Misterios.oracoesDoMisterio(pDiaSemana, pMisterio);
    }

    @Override
    public List<Integer> obterContas(int pMisterio) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    private String paiNosso() {
        return this.resources.getString(R.string.pai_nosso);
    }
}

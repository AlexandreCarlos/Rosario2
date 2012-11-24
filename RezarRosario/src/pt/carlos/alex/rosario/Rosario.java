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

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alexandre
 * Date: 18-11-2012
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public interface Rosario {
    public List<String> obterDezena  (final int pDiaSemana, final int pMisterio);

    public List<Integer> obterContas (final int pMisterio);

}

/*******************************************************************************
 * Copyright (c) 2015 pf-miles.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     pf-miles - initial API and implementation
 ******************************************************************************/
package com.github.pfmiles.kanjava.impl.hooks

import com.github.pfmiles.kanjava.Feature
import com.github.pfmiles.kanjava.impl.Cuttable
import com.github.pfmiles.kanjava.impl.ErrMsg
import com.github.pfmiles.kanjava.impl.GlobalContext
import com.sun.source.tree.DoWhileLoopTree

/**
 * 禁止使用do-while循环语句的hook实现
 * @author <a href="mailto:miles.wy.1@gmail.com">pf_miles</a>
 *
 */
class CutDoWhileLoopFeatureVisitDoWhileLoopHook implements VisitDoWhileLoopHook {

    Cuttable getCuttable() {
        Feature.doWhileLoop
    }

    void beforeVisitStatement(DoWhileLoopTree node, List<ErrMsg> errMsgs, GlobalContext globalCtx,
            Closure<List<Map<String, Long>>> resolveRowAndCol, Closure<Void> setError) {
        setError()
        def rowAndCol = resolveRowAndCol(node)
        errMsgs.add(new ErrMsg(rowAndCol.row, rowAndCol.col, "Do-while-loops are not allowed."))
    }

    void afterVisitStatementAndBeforeCondition(DoWhileLoopTree node, List<ErrMsg> errMsgs, GlobalContext globalCtx,
            Closure<List<Map<String, Long>>> resolveRowAndCol, Closure<Void> setError) {
    }

    void afterVisitCondition(DoWhileLoopTree node, List<ErrMsg> errMsgs, GlobalContext globalCtx,
            Closure<List<Map<String, Long>>> resolveRowAndCol, Closure<Void> setError) {
    }
}

/*
 * $Id$
 *
 * Copyright (c) 2001, 2009, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.sun.javatest.exec;

import java.util.Iterator;
import com.sun.javatest.tool.jthelp.ContextHelpManager;
import com.sun.javatest.TestDescription;
import com.sun.javatest.TestResult;
import com.sun.javatest.tool.UIFactory;

/**
 * A subpanel of TestPanel that displays the test description.
 */

class TP_DescSubpanel extends TP_PropertySubpanel {
    TP_DescSubpanel(UIFactory uif) {
        super(uif, "desc");
        ContextHelpManager.setHelpIDString(this, "browse.testDescriptionTab.csh");
    }

    @Override
    protected void updateSubpanel(TestResult currTest) {
        super.updateSubpanel(currTest);

        TestDescription td;

        try {
            td = subpanelTest.getDescription();
        }
        catch (TestResult.Fault f) {
            return;
        }

        for (Iterator<String> iter = td.getParameterKeys(); iter.hasNext(); ) {
            String key = iter.next();
            String val = td.getParameter(key);
            updateEntry(key, val);
        }
    }

}

/*
 * $Id$
 *
 * Copyright (c) 2001, 2020, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javatest.functional;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class InitUrl5_filteringOutFailingWithAKeyword extends TestSuiteRunningTestBase {

    @Test
    public void test() {
        runJavaTest();
    }

    @Override
    protected int[] getExpectedTestRunFinalStats() {
        return new int[]{3, 0, 0, 0};
    }

    @Override
    protected int getExpectedNumberOfTestsSkipped() {
        return 3;
    }

    @Override
    protected String[] getExpectedLinesInTestrunSummary() {
        return new String[]{
                "comp/foo/set1.html#CompSucc       Passed. exit code 0",
                "comp/foo/set2.html#CompSuccMulti  Passed. exit code 0",
                "comp/index.html#CompFailExp       Passed. compilation failed as expected [exit code 1]"
        };
    }

    @Override
    protected List<String> getTailArgs() {
        // using 'ShouldPass' keyword and dispite the fact one test fails
        return Arrays.asList("-keywords", "ShouldPass", "comp");
    }

    @Override
    protected String getEnvName() {
        return "initurl";
    }

    @Override
    protected String getEnvfileName() {
        return "initurl.jte";
    }

    @Override
    protected String getTestsuiteName() {
        return "initurl";
    }


}

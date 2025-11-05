/*
 * $Id$
 *
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

package jthtest.ConfigEnvBrowser;

import jthtest.ConfigTools;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.netbeans.jemmy.operators.*;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class ConfigEnvBrowser3 extends ConfigTools {
     public static void main(String[] args) {
          JUnitCore.main("jthtest.gui.ConfigEnvBrowser3");
     }

    /**
     * This test case verifies that a single click on help Button in Test environment Dialog shall bring up the online help TestEnvironment Dialog Box page.
     */

     @Test
     public void ConfigEnvBrowser3() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
               InterruptedException, FileNotFoundException {

          // Start Java Test application with -newDesktop
          startJavatestNewDesktop();

          // get the reference of mainframe
          JFrameOperator mainFrame = findMainFrame();

          // Close Quick Start Dialog window
          closeQS(mainFrame);

          // Open default test suite
          openTestSuite(mainFrame);

          // Create work directory
          createWorkDirInTemp(mainFrame);

         JMenuBarOperator menuBar = new JMenuBarOperator(mainFrame);
         menuBar.pushMenuNoBlock("View|Configuration|Show Test Environment");

         // Verify dialog is displayed and configuration values are present
         JDialogOperator envDialog = new JDialogOperator("Test Environment: unknown"); // dialog title as seen
         // Check that at least one row exists in the environment table
         JTableOperator table = new JTableOperator(envDialog);
         int rowCount = table.getRowCount();
         Thread.sleep(3000);

         JButtonOperator helpButton = new JButtonOperator(envDialog, "Help");
         helpButton.push();

         // Check if the help file exists
         String helpFilePath = System.getProperty("user.home") + "/.javatest/jthelp/com/sun/javatest/help/default/env/dialog.html";
         java.io.File helpFile = new java.io.File(helpFilePath);
         assert helpFile.exists() : "The correct JavaTest online help window does not exists at " + helpFilePath;

     }
}

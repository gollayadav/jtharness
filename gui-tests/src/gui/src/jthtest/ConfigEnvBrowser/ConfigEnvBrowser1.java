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
import jthtest.Config_Edit.Config_Edit;
import jthtest.menu.Menu;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.*;
import org.netbeans.jemmy.util.NameComponentChooser;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class ConfigEnvBrowser1 extends ConfigTools {
     public static void main(String[] args) {
          JUnitCore.main("jthtest.gui.ConfigEnvBrowser1");
     }

    /**
     * This test case verifies that Show Test Environment browser will display the current configuration if config file is open.
     */

     @Test
     public void ConfigEnvBrowser1() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
               InterruptedException, FileNotFoundException {

          /**
           * This test case verifies that Question Mode radio is always selected.
           */

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

          // Load existing configuration file
          openConfigFile(openLoadConfigDialogByMenu(mainFrame), CONFIG_NAME);
          Config_Edit.waitForConfigurationLoading(mainFrame, CONFIG_NAME);

         JMenuBarOperator menuBar = new JMenuBarOperator(mainFrame);
         menuBar.pushMenuNoBlock("View|Configuration|Show Test Environment");

         // Verify dialog is displayed and configuration values are present
         JDialogOperator envDialog = new JDialogOperator("Test Environment: democonfig"); // dialog title as seen
         // Check that at least one row exists in the environment table
         JTableOperator table = new JTableOperator(envDialog);
         int rowCount = table.getRowCount();
         Thread.sleep(3000);

         Assert.assertTrue("Test Failed: The Test Environment dialog should be displayed with all the values in the configuration.", rowCount > 0);

         // Close the dialog
         new JButtonOperator(envDialog, "Close").push();

     }
}

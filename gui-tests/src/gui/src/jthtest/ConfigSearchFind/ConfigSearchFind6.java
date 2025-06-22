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

package jthtest.ConfigSearchFind;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.DialogOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JCheckBoxOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;

import jthtest.ConfigTools;
import jthtest.Config_Edit.Config_Edit;

public class ConfigSearchFind6 extends ConfigTools {

     public static void main(String[] args) {
          JUnitCore.main("jthtest.gui.ConfigSearchFind.ConfigSearchFind6");
     }

     @Test
     public void ConfigSearchFind6() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException {

          /**
           * This test case verifies that a string as whole word in the interview could be
           * displayed when Whole words checkbox is check on.
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

          // Bring up Edit Configuration editor by doing Ctrl-E
          mainFrame.pushKey(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK);

          // Get the Edit Configuration dialog
          JDialogOperator editConfigDialog = new JDialogOperator(mainFrame);

          // Bring up Find from Search menu
          JMenuBarOperator jmbo1 = new JMenuBarOperator(editConfigDialog);
          jmbo1.pushMenu("Search", "/");
          jmbo1.pushMenu("Search/Find...", "/");

          // Get the Find dialog
          DialogOperator findDialog = new DialogOperator("Find Question");

          // Enter "elco" in the edit line
          JTextFieldOperator textFieldOperator = new JTextFieldOperator(findDialog);
          textFieldOperator.clearText();
          textFieldOperator.enterText("elco");

          // Check Whole words checkbox on
          JCheckBoxOperator wholeWordsCheckBox = new JCheckBoxOperator(findDialog, "Whole words");
          wholeWordsCheckBox.doClick();

          // Click on Find
          JButtonOperator findButton = new JButtonOperator(findDialog, "Find");
          findButton.push();

          // Verify that only questions with whole word "elco" will be found
          // Assuming the results are displayed in a JList
          JDialogOperator configEditorDialog = new JDialogOperator(getExecResource("ce.name"));
          ComponentChooser qu = new NameComponentChooser("qu.title");
          JTextFieldOperator testConfig1 = new JTextFieldOperator(configEditorDialog, qu);

          Assert.assertFalse("Only the questions with whole word elcom will be found and not Welcome",
                    testConfig1.getText().contains("welcome"));
     }
}

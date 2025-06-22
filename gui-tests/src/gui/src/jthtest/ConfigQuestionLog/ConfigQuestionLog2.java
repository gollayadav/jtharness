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

package jthtest.ConfigQuestionLog;

import java.awt.Component;
import java.io.File;

/**
 * This test case verifies that a string in the interview could be displayed when search option is "In titles".
 */

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.ComponentOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JMenuOperator;
import org.netbeans.jemmy.operators.JTextComponentOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator.JTextFieldFinder;
import org.netbeans.jemmy.util.Dumper;
import org.netbeans.jemmy.util.NameComponentChooser;

import jthtest.ConfigTools;
import jthtest.Tools;

public class ConfigQuestionLog2 extends ConfigTools {
     public static void main(String[] args) {
          JUnitCore.main("jthtest.gui.ConfigQuestionLog2");
     }

     @Test
     public void ConfigQuestionLog1() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
               InterruptedException, FileNotFoundException {

          /**
           * Verify that the content of question log will be saved in a specified
           * directory.
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

          // Open the "Show Question Log..." menu
          new JMenuOperator(mainFrame, "View").pushMenu("View|Configuration|Show Question Log...", "|");
          JDialogOperator cql = new JDialogOperator(mainFrame, "Configuration Question Log");

          // Save the "Save Configuration Question Log"
          new JMenuOperator(cql, "File").pushMenuNoBlock("File|Save As", "|");
          JDialogOperator scql = new JDialogOperator("Save Configuration Question Log");
          Dumper.dumpAll("temp.txt");

          // enter the file name
          JTextFieldOperator scqltxt = new JTextFieldOperator(scql);
          scqltxt.setText("currconfig");

          // save the file
          JButtonOperator scqljb = new JButtonOperator(scql, "Save");
          scqljb.push();

          // Assert the validation
          File temp = new File("//" + Tools.DEFAULT_PATH + "currconfig.html");
          Assert.assertTrue("Verify that the current configuration should be saved in html file.", temp.exists());
     }
}

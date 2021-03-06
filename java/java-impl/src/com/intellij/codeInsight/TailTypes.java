/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.codeInsight;

import com.intellij.codeInsight.completion.simple.BracesTailType;
import com.intellij.codeInsight.completion.simple.ParenthesesTailType;
import com.intellij.codeInsight.completion.simple.RParenthTailType;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.codeStyle.CodeStyleSettings;

public class TailTypes {
  public static final TailType CALL_RPARENTH = new RParenthTailType(){
    protected boolean isSpaceWithinParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES && editor.getDocument().getCharsSequence().charAt(tailOffset - 1) != '(';
    }
  };
  public static final TailType IF_RPARENTH = new RParenthTailType(){
    protected boolean isSpaceWithinParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_WITHIN_IF_PARENTHESES;
    }
  };
  public static final TailType WHILE_RPARENTH = new RParenthTailType(){
    protected boolean isSpaceWithinParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_WITHIN_WHILE_PARENTHESES;
    }
  };
  public static final TailType CALL_RPARENTH_SEMICOLON = new RParenthTailType(){
    protected boolean isSpaceWithinParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES;
    }

    public int processTail(final Editor editor, int tailOffset) {
      return insertChar(editor, super.processTail(editor, tailOffset), ';');
    }
  };

  public static final TailType SYNCHRONIZED_LPARENTH = new ParenthesesTailType() {
    protected boolean isSpaceBeforeParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_BEFORE_SYNCHRONIZED_PARENTHESES;
    }

    protected boolean isSpaceWithinParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_WITHIN_SYNCHRONIZED_PARENTHESES;
    }
  };
  public static final TailType CATCH_LPARENTH = new ParenthesesTailType() {
    protected boolean isSpaceBeforeParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_BEFORE_CATCH_PARENTHESES;
    }

    protected boolean isSpaceWithinParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_WITHIN_CATCH_PARENTHESES;
    }
  };
  public static final TailType SWITCH_LPARENTH = new ParenthesesTailType() {
    protected boolean isSpaceBeforeParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_BEFORE_SWITCH_PARENTHESES;
    }

    protected boolean isSpaceWithinParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_WITHIN_SWITCH_PARENTHESES;
    }
  };
  public static final TailType WHILE_LPARENTH = new ParenthesesTailType() {
    protected boolean isSpaceBeforeParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_BEFORE_WHILE_PARENTHESES;
    }

    protected boolean isSpaceWithinParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_WITHIN_WHILE_PARENTHESES;
    }
  };
  public static final TailType FOR_LPARENTH = new ParenthesesTailType() {
    protected boolean isSpaceBeforeParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_BEFORE_FOR_PARENTHESES;
    }

    protected boolean isSpaceWithinParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_WITHIN_FOR_PARENTHESES;
    }
  };
  public static final TailType IF_LPARENTH = new ParenthesesTailType() {
    protected boolean isSpaceBeforeParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_BEFORE_IF_PARENTHESES;
    }

    protected boolean isSpaceWithinParentheses(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_WITHIN_IF_PARENTHESES;
    }
  };
  public static final TailType FINALLY_LBRACE = new BracesTailType() {
    protected boolean isSpaceBeforeLBrace(final CodeStyleSettings styleSettings, final Editor editor, final int tailOffset) {
      return styleSettings.SPACE_BEFORE_FINALLY_LBRACE;
    }
  };

  private TailTypes() {}
}

/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.tools.lint.checks;

import com.android.tools.lint.detector.api.Detector;

@SuppressWarnings("javadoc")
public class TextViewDetectorTest extends AbstractCheckTest {
    @Override
    protected Detector getDetector() {
        return new TextViewDetector();
    }

    public void test() throws Exception {
        assertEquals(
            "edit_textview.xml:13: Warning: Attribute android:autoText should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:14: Warning: Attribute android:bufferType should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:15: Warning: Attribute android:capitalize should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:16: Warning: Attribute android:cursorVisible should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:17: Warning: Attribute android:digits should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:18: Warning: Attribute android:editable should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:19: Warning: Attribute android:editorExtras should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:22: Warning: Attribute android:imeActionId should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:23: Warning: Attribute android:imeActionLabel should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:24: Warning: Attribute android:imeOptions should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:25: Warning: Attribute android:inputMethod should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:26: Warning: Attribute android:inputType should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:27: Warning: Attribute android:numeric should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:28: Warning: Attribute android:password should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:29: Warning: Attribute android:phoneNumber should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:30: Warning: Attribute android:privateImeOptions should not be used with <TextView>: Change element type to <EditText> ?\n" +
            "edit_textview.xml:38: Warning: Attribute android:cursorVisible should not be used with <Button>: intended for editable text widgets\n" +
            "edit_textview.xml:44: Warning: Attribute android:cursorVisible should not be used with <CheckedTextView>: intended for editable text widgets\n" +
            "edit_textview.xml:50: Warning: Attribute android:cursorVisible should not be used with <CheckBox>: intended for editable text widgets\n" +
            "edit_textview.xml:56: Warning: Attribute android:cursorVisible should not be used with <RadioButton>: intended for editable text widgets\n" +
            "edit_textview.xml:62: Warning: Attribute android:cursorVisible should not be used with <ToggleButton>: intended for editable text widgets",

            lintFiles("res/layout/edit_textview.xml"));
    }
}

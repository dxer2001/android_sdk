/*
 ** Copyright 2011, The Android Open Source Project
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 **
 **     http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 */

package com.android.glesv2debugger;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class MessageData {
    public DebuggerMessage.Message.Function function;
    public Image image; // texture
    public String shader; // shader source
    public String[] columns;
    public float[] data; // vertex attributes

    public MessageData(final Device device, final DebuggerMessage.Message msg) {
        image = null;
        shader = null;
        data = null;
        StringBuilder builder = new StringBuilder();
        function = msg.getFunction();
        ImageData imageData = null;
        if (function != DebuggerMessage.Message.Function.ACK)
            assert msg.hasTime();
        columns = new String [4];
        columns[0] = function.toString();
        columns[1] = "";
        if (msg.hasTime())
            columns[1] += Float.toString(msg.getTime());
        if (msg.hasClock())
            columns[1] += ":" + Float.toString(msg.getClock());
        columns[2] = Integer.toHexString(msg.getContextId());
        columns[3] = MessageFormatter.Format(msg);
        switch (function) {
            case glBufferData:
                data = MessageProcessor.ReceiveData(msg.getArg0(), msg.getData());
                break;
            case glBufferSubData:
                data = MessageProcessor.ReceiveData(msg.getArg0(), msg.getData());
                break;
            case glShaderSource:
                shader = msg.getData().toStringUtf8();
                int index = shader.indexOf('\n');
                columns[3] += " source: " + shader.substring(0, index >= 0 ? index : shader.length()) + "...";
                break;
            case glTexImage2D:
                if (!msg.hasData())
                    break;
                imageData = MessageProcessor.ReceiveImage(msg.getArg3(), msg
                        .getArg4(), msg.getArg6(), msg.getArg7(), msg.getData()
                        .toByteArray());
                if (null == imageData)
                    break;
                image = new Image(device, imageData);
                break;
            case glTexSubImage2D:
                assert msg.hasData();
                imageData = MessageProcessor.ReceiveImage(msg.getArg4(), msg
                        .getArg5(), msg.getArg6(), msg.getArg7(), msg.getData()
                        .toByteArray());
                if (null == imageData)
                    break;
                image = new Image(device, imageData);
                break;
            case glReadPixels:
                if (!msg.hasData())
                    break;
                imageData = MessageProcessor.ReceiveImage(msg.getArg2(), msg.getArg3(),
                        msg.getArg4(), msg.getArg5(), msg.getData().toByteArray());
                imageData = imageData.scaledTo(imageData.width, -imageData.height);
                image = new Image(device, imageData);
                break;
        }
    }
}